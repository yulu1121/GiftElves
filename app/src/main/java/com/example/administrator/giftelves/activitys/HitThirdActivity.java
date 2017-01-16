package com.example.administrator.giftelves.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.adapters.HitThreeGridAdapter;
import com.example.administrator.giftelves.enties.HitThirdInformation;
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

public class HitThirdActivity extends AppCompatActivity {
    private TextView textName;
    private ImageView backImage;
    private TextView addTime;
    private GridView gridView;
    private TextView desc;
    private HitThreeGridAdapter adapter;
    private Bundle hit;
    private List<HitThirdInformation.ListBean> listing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        hit = intent.getBundleExtra("hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_hit_third_information);
        initView();
        initData();
    }

    private void initData() {
        listing = new ArrayList<>();
        final Gson gson = new Gson();
        JsonLoader jsonloader = new JsonLoader();
        jsonloader.parseJson2String(Constants.HIT_THIRD_INFORMATION +hit.getInt("sid"), new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                HitThirdInformation information = gson.fromJson(json, HitThirdInformation.class);
                listing = information.getList();
                adapter = new HitThreeGridAdapter(HitThirdActivity.this,listing);
                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        textName = (TextView) findViewById(R.id.hit_information_name);
        backImage = (ImageView)findViewById(R.id.back_image);
        addTime = (TextView)findViewById(R.id.addtime);
        desc = (TextView)findViewById(R.id.information_three);
        gridView = (GridView)findViewById(R.id.hit_third_grid);
        textName.setText(hit.getString("game"));
        desc.setText(hit.getString("desc"));
        backImage.setImageResource(R.mipmap.def_loading);
        String url = Constants.COMMON_URL+hit.getString("back");
        backImage.setTag(url);
        ImageLoader.loadImage(url, 400, 250, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(backImage.getTag())){
                    backImage.setImageBitmap(bitMap);
                }
            }
        });
        addTime.setText(hit.getString("addtime"));
    }

    public void onClickBack(View view) {
        finish();
    }

}
