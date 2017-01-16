package com.example.administrator.giftelves.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.adapters.SpecialNewsAdapter;
import com.example.administrator.giftelves.enties.SpecialNewsFormation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/8.
 */

public class SpecialNewsActivity extends AppCompatActivity {
    private ListView listView;
    private TextView title;
    private ImageView back;
    private TextView desc;
    private ImageView author;
    private SpecialNewsAdapter adapter;
    private Bundle bundle;
    private List<SpecialNewsFormation.ListBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("news");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_news_formation);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        JsonLoader jsonLoader = new JsonLoader();
        final Gson gson = new Gson();
        jsonLoader.parseJson2String(Constants.SPECIAL_NEWS_FORMATION + bundle.getInt("id"), new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                SpecialNewsFormation formation = gson.fromJson(json, SpecialNewsFormation.class);
                list = formation.getList();
                adapter = new SpecialNewsAdapter(SpecialNewsActivity.this,list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        title = (TextView) findViewById(R.id.hit_information_name);
        back = (ImageView)findViewById(R.id.back_image);
        desc = (TextView)findViewById(R.id.news_information);
        author = (ImageView)findViewById(R.id.autor);
        listView = (ListView)findViewById(R.id.newsListView);
        title.setText(bundle.getString("name"));
        desc.setText(bundle.getString("desc"));
        String urling = Constants.COMMON_URL+bundle.getString("back");
        back.setTag(urling);
        ImageLoader.loadImage(urling,new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(back.getTag())){
                    back.setImageBitmap(bitMap);
                }
            }
        });
        String url = Constants.COMMON_URL+bundle.getString("author");
        author.setTag(url);
        ImageLoader.loadImage(url, 400, 250, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(author.getTag())){
                    author.setImageBitmap(bitMap);
                }
            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }
}
