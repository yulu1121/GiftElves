package com.example.administrator.giftelves.activitys;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.HotGameInformation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.DownLoadUtils;
import com.example.administrator.giftelves.utils.ImageLoader;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.example.administrator.giftelves.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/6.
 */

public class HotGameActivity extends AppCompatActivity {
    private TextView giftName;
    private TextView gName;
    private ImageView imageView;
    private TextView gType;
    private TextView gSize;
    private TextView gExplain;
    private Button btn;
    private String id;
    private TextView downLoadUrl;
    private LinearLayout linearLayout;
    private DownLoadUtils utils;
    private DownloadManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getStringExtra("hotId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_game_information);
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        initView();
        initData();
    }

    private void initData() {
        JsonLoader jsonLoader = new JsonLoader();
        final Gson gson = new Gson();
        jsonLoader.parseJson2String(Constants.OPEN_MANAGER_INFORMATION + id,new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                HotGameInformation information = gson.fromJson(json, HotGameInformation.class);
                final HotGameInformation.AppBean app = information.getApp();
                initViewPager(information.getImg());
                giftName.setText(app.getName());
                gName.setText(app.getName());
                gType.setText(app.getTypename());
                gSize.setText(app.getAppsize());
                if(TextUtils.isEmpty(gSize.getText())){
                    gSize.setText("大小未知");
                }
                gExplain.setText(app.getDescription());
                imageView.setImageResource(R.mipmap.gift);
                downLoadUrl.setText(app.getDownload_addr());
                if(TextUtils.isEmpty(downLoadUrl.getText())){
                    btn.setText("暂无下载");
                    btn.setEnabled(false);
                    btn.setBackgroundColor(Color.GRAY);
                }
                String url = Constants.COMMON_URL+app.getLogo();
                imageView.setTag(url);
                ImageLoader.loadImage(url, new ImageLoader.ImageListener() {
                    @Override
                    public void ImageComplete(Bitmap bitMap, String Url) {
                        if(Url.equals(imageView.getTag())){
                            imageView.setImageBitmap(bitMap);
                        }
                    }
                });
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        utils = new DownLoadUtils(HotGameActivity.this,manager);
                        DownloadManager.Request request= new DownloadManager.Request(Uri.parse(app.getDownload_addr()));
                        request.setTitle(app.getName()+".apk");
                        request.setDestinationInExternalPublicDir("/download/",app.getName());
                        request.setVisibleInDownloadsUi(true);
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);
                        request.setAllowedOverRoaming(false);
                        MimeTypeMap typeMap = MimeTypeMap.getSingleton();
                        String extension = typeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(app.getDownload_addr()));
                        request.setMimeType(extension);
                        long id = manager.enqueue(request);
                        SharedPreferenceUtils.saveLong(HotGameActivity.this,"id",id);
                        utils.queryDownLoadStatus();
                    }
                });
            }

        });
    }

    private void initView() {
        giftName = (TextView) findViewById(R.id.open_hot_name);
        gName = (TextView)findViewById(R.id.open_hot_gameName);
        gType = (TextView)findViewById(R.id.open_hot_type);
        gSize = (TextView)findViewById(R.id.open_hot_size);
        gExplain=(TextView)findViewById(R.id.open_test_explain);
        imageView = (ImageView)findViewById(R.id.open_hot_image);
        btn = (Button)findViewById(R.id.open_hot_btn);
        linearLayout = (LinearLayout)findViewById(R.id.linerview);
        downLoadUrl = (TextView) findViewById(R.id.downLoadUrl);
    }
    private void initViewPager(List<HotGameInformation.ImgBean> list){
        int width = getWindowManager().getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (width/2, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(2,0,2,0);
        for(HotGameInformation.ImgBean image:list){
           final ImageView imageItem = new ImageView(this);
            imageItem.setScaleType(ImageView.ScaleType.FIT_XY);
            imageItem.setImageResource(R.mipmap.def_loading);
            String urling = Constants.COMMON_URL+image.getAddress();
            imageItem.setTag(urling);
            ImageLoader.loadImage(urling,new ImageLoader.ImageListener() {
                @Override
                public void ImageComplete(Bitmap bitMap, String Url) {
                    if(Url.equals(imageItem.getTag())){
                        imageItem.setImageBitmap(bitMap);
                    }
                }
            });
            linearLayout.addView(imageItem,params);
        }
    }

    public void onClickBack(View view) {
        finish();
    }
}
