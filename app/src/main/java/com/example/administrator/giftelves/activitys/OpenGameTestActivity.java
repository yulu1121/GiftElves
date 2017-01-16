package com.example.administrator.giftelves.activitys;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.OpenGameTestFormation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.DownLoadUtils;
import com.example.administrator.giftelves.utils.ImageLoader;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.example.administrator.giftelves.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 开测的Activity
 * Created by Administrator on 2017/1/6.
 */

public class OpenGameTestActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LayoutInflater inflater;
    private TextView giftName;
    private ImageView imageView;
    private TextView gName;
    private TextView gType;
    private TextView gSize;
    private TextView gExplain;
    private Button btn;
    private TextView downLoadeUrl;
    private String id;
    private List<View> mlist;
    private DownloadManager manager;
    private DownLoadUtils utils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getStringExtra("testId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_test_information);
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        initView();
        initData();
    }

    private void initData() {
        JsonLoader jsonLoader = new JsonLoader();
        final Gson gson = new Gson();
        jsonLoader.parseJson2String(Constants.OPEN_MANAGER_INFORMATION + id, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                OpenGameTestFormation formation = gson.fromJson(json, OpenGameTestFormation.class);
                final OpenGameTestFormation.AppBean app = formation.getApp();
                initViewPager(formation.getImg());
                viewPager.setAdapter(new ViewPagerTestAdapter());
                giftName.setText(app.getName());
                gName.setText(app.getName());
                gType.setText(app.getTypename());
                gSize.setText(app.getAppsize());
                if(TextUtils.isEmpty(gSize.getText())){
                    gSize.setText("大小未知");
                }
                gExplain.setText(app.getDescription());
                downLoadeUrl.setText(app.getDownload_addr());
                if(TextUtils.isEmpty(downLoadeUrl.getText())){
                    btn.setText("暂无下载");
                    btn.setEnabled(false);
                    btn.setBackgroundColor(Color.GRAY);
            }
                imageView.setImageResource(R.mipmap.gift);
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
                        utils = new DownLoadUtils(OpenGameTestActivity.this,manager);
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
                        SharedPreferenceUtils.saveLong(OpenGameTestActivity.this,"id",id);
                        utils.queryDownLoadStatus();
                    }
                });
            }
        });
    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager_open_test);
        viewPager.setPageMargin(3);
        giftName = (TextView) findViewById(R.id.open_test_name);
        gName = (TextView) findViewById(R.id.open_test_gameName);
        imageView = (ImageView) findViewById(R.id.open_test_image);
        gType = (TextView) findViewById(R.id.open_test_type);
        gSize = (TextView) findViewById(R.id.open_test_size);
        gExplain = (TextView) findViewById(R.id.open_test_explain);
        btn = (Button) findViewById(R.id.open_test_btn);
        downLoadeUrl = (TextView) findViewById(R.id.downLoadUrl);
    }
    private void initViewPager(List<OpenGameTestFormation.ImgBean> list){
        mlist = new ArrayList<>();
        for(OpenGameTestFormation.ImgBean image:list){
            View view  = inflater.inflate(R.layout.open_test_image,null);
            final ImageView imageItem = (ImageView) view.findViewById(R.id.image_test);
            imageItem.setImageResource(R.mipmap.def_loading);
            String urling = Constants.COMMON_URL+image.getAddress();
            imageItem.setTag(urling);
            ImageLoader.loadImage(urling, new ImageLoader.ImageListener() {
                @Override
                public void ImageComplete(Bitmap bitMap, String Url) {
                    if(Url.equals(imageItem.getTag())){
                        imageItem.setImageBitmap(bitMap);
                    }
                }
            });
            mlist.add(view);
        }
    }
    class ViewPagerTestAdapter extends PagerAdapter{
        @Override
        public float getPageWidth(int position) {
            return 0.5f;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mlist.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mlist.get(position));
            return mlist.get(position);
        }

        @Override
        public int getCount() {
            return null==mlist?0:mlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
    public void onClickBack(View view) {
        finish();
    }
}
