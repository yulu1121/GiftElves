package com.example.administrator.giftelves.activitys;

import android.app.Application;

import com.example.administrator.giftelves.utils.ImageUtils;

/**
 *
 * Created by Administrator on 2017/1/3.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageUtils.initCache(getApplicationContext());
    }
}
