package com.example.administrator.giftelves.activitys;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.OpenGameManagerFormation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.DownLoadUtils;
import com.example.administrator.giftelves.utils.ImageLoader;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.example.administrator.giftelves.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/4.
 */

public class OpenGameManagerActivity extends AppCompatActivity {
    private TextView giftName;
    private ImageView imageView;
    private TextView gName;
    private TextView gType;
    private TextView gSize;
    private TextView gExplains;
    private List<OpenGameManagerFormation.ImgBean> list;
    private LayoutInflater inflater;
    private ViewPager viewPager;
    private List<View> viewList;
    String id;
    private DownLoadUtils utils;
    private DownloadManager managering;
    private Button download;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        id=intent.getStringExtra("managerId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_manager_formation);
        managering = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        final Gson gson = new Gson();
        JsonLoader jsonloader = new JsonLoader();
        jsonloader.parseJson2String(Constants.OPEN_MANAGER_INFORMATION + id, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                final OpenGameManagerFormation manager = gson.fromJson(json, OpenGameManagerFormation.class);
                list = manager.getImg();
                initViewPager(list);
                viewPager.setAdapter(new OpenViewPagerAdapter());
                final OpenGameManagerFormation.AppBean app = manager.getApp();
                giftName.setText(app.getName());
                gName.setText(app.getName());
                gType.setText(app.getTypename());
                gSize.setText(app.getAppsize());
                if(TextUtils.isEmpty(gSize.getText().toString())){
                    gSize.setText("大小未知");
                }
                gExplains.setText(app.getDescription());
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
                download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        utils = new DownLoadUtils(OpenGameManagerActivity.this,managering);
                        DownloadManager.Request request= new DownloadManager.Request(Uri.parse(app.getDownload_addr()));
                        request.setTitle(app.getName()+".APK");
                        request.setDestinationInExternalPublicDir("/download/",app.getName());
                        request.setVisibleInDownloadsUi(true);
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);
                        request.setAllowedOverRoaming(false);
                        MimeTypeMap typeMap = MimeTypeMap.getSingleton();
                        String extension = typeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(app.getDownload_addr()));
                        request.setMimeType(extension);
                        long id = managering.enqueue(request);
                        SharedPreferenceUtils.saveLong(OpenGameManagerActivity.this,"id",id);
                        utils.queryDownLoadStatus();
                    }
                });
            }
        });
    }
    private void initView() {
        inflater = LayoutInflater.from(this);
        giftName = (TextView) findViewById(R.id.open_information_name);
        imageView = (ImageView) findViewById(R.id.open_information_image);
        gName = (TextView) findViewById(R.id.open_information_gameName);
        gType = (TextView) findViewById(R.id.open_information_type);
        gSize = (TextView) findViewById(R.id.open_information_size);
        gExplains = (TextView)findViewById(R.id.open_information_explain);
        download = (Button)findViewById(R.id.open_information_btn);
        viewPager = (ViewPager) findViewById(R.id.viewPager_open);
        viewPager.setPageMargin(3);
}
    private void initViewPager(List<OpenGameManagerFormation.ImgBean> list){
        viewList = new ArrayList<>();
        for(OpenGameManagerFormation.ImgBean imgBean:list){
            View view = inflater.inflate(R.layout.open_manager_image,null);
            final ImageView listImage = (ImageView)view.findViewById(R.id.open_information_imagePagering);
            listImage.setImageResource(R.mipmap.def_loading
             );
            String urling = Constants.COMMON_URL+imgBean.getAddress();
            listImage.setTag(urling);
            ImageLoader.loadImage(urling, new ImageLoader.ImageListener() {
                @Override
                public void ImageComplete(Bitmap bitMap, String Url) {
                    if(Url.equals(listImage.getTag())){
                        listImage.setImageBitmap(bitMap);
                    }
                }
            });
           viewList.add(view);
        }
    }
    //点击返回按钮结束当前界面
    public void onClickBack(View view) {
        finish();
    }
    class OpenViewPagerAdapter extends PagerAdapter{
        @Override
        public float getPageWidth(int position) {
            return (float)0.5;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public int getCount() {
            return null==viewList?0:viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
