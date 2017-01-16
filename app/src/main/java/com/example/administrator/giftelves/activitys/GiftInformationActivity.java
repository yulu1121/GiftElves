package com.example.administrator.giftelves.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.GiftInformation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;

/**
 *
 * Created by Administrator on 2017/1/3.
 */

public class GiftInformationActivity extends AppCompatActivity{
    private TextView giftName;
    private ImageView imageView;
    private TextView overTime;
    private TextView giftNumber;
    private TextView giftInformations;
    private TextView giftMethod;
    private Button getGift;
    private String id;
    private JsonLoader jsonLoader;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        id=intent.getStringExtra("giftId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_information);
        initView();
        initData();
        initControll();
    }

    private void initData() {
        final Gson gson = new Gson();
        jsonLoader  = new JsonLoader();
        jsonLoader.parseJson2String(Constants.GIFT_INFORMATION+id, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                GiftInformation.InfoBean info = gson.fromJson(json, GiftInformation.class).getInfo();
                giftName.setText(info.getGiftname());
                overTime.setText("有效期:"+info.getOvertime());
                SpannableString ss = new SpannableString("礼包剩余:"+info.getNumber());
                ss.setSpan(new ForegroundColorSpan(Color.GREEN),5,ss.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                giftNumber.setText(ss);
                giftInformations.setText(info.getExplains());
                giftMethod.setText(info.getDescs());
                imageView.setImageResource(R.mipmap.def_loading);
                String url = Constants.COMMON_URL + info.getIconurl();
                imageView.setTag(url);
                ImageLoader.loadImage(url, new ImageLoader.ImageListener() {
                    @Override
                    public void ImageComplete(Bitmap bitMap, String Url) {
                        if(Url.equals(imageView.getTag())){
                            imageView.setImageBitmap(bitMap);
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        giftName = (TextView) findViewById(R.id.giftName);
        imageView = (ImageView)findViewById(R.id.image_giftInformation);
        overTime = (TextView) findViewById(R.id.overTime);
        giftNumber = (TextView) findViewById(R.id.giftNumber);
        giftInformations = (TextView) findViewById(R.id.giftInformation);
        giftMethod = (TextView)findViewById(R.id.giftMethod);
        getGift = (Button)findViewById(R.id.giftButton);
    }
    private void initControll(){
        getGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GiftInformationActivity.this, "领取成功", Toast.LENGTH_SHORT).show();
                getGift.setEnabled(false);
                getGift.setText("已领取");
                getGift.setBackgroundColor(Color.GRAY);
            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }
}
