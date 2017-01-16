package com.example.administrator.giftelves.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * Created by Administrator on 2017/1/10.
 */

public class DownLoadUtils {
    private DownloadManager manager;
    private Context context;
    public DownLoadUtils(Context context,DownloadManager manager){
        this.context = context;
        this.manager = manager;
    }
//    private SharedPreferences shared;
//    public static final String DOWN_LAOD = "download";
//    private Bundle urlBd;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        urlBd = intent.getBundleExtra("urling");
//        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        shared=PreferenceManager.getDefaultSharedPreferences(this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(!shared.contains(DOWN_LAOD)){
//            Uri uri = Uri.parse(urlBd.getString("url"));
//            DownloadManager.Request request = new DownloadManager.Request(uri);
//            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
//            request.setAllowedOverRoaming(false);
//            //设置文件类型
//            MimeTypeMap typeMap = MimeTypeMap.getSingleton();
//            String fromExtension = typeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(urlBd.getString("url")));
//            request.setMimeType(fromExtension);
//            //在通知栏中显示
//            request.setShowRunningNotification(true);
//            request.setVisibleInDownloadsUi(true);
//            request.setDestinationInExternalPublicDir("/download/",urlBd.getString("name"));
//            request.setTitle(urlBd.getString("name"));
//            long id = manager.enqueue(request);
//            shared.edit().putLong(DOWN_LAOD,id).commit();
//        }else {
//                queryDownLoadStatus();
//        }
//        registerReceiver(receiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
//    }
//
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.e("xx",""+intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,0));
//            queryDownLoadStatus();
//        }
//    };
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(receiver);
//    }

    //解码
    public String encodeGB(String urling) {
        String split[] = urling.split("/");
        for (int i = 0; i < split.length; i++) {
            try {
                split[i] = URLEncoder.encode(split[i], "GB2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            split[0]=split[0]+"/"+split[i];
        }
        split[0]= split[0].replaceAll("\\+","%20");//处理空格
        return split[0];
    }
    //检查下载状态
    public void queryDownLoadStatus(){
        long id = SharedPreferenceUtils.getLong(context,"id");
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = manager.query(query);
        if(cursor.moveToFirst()){
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status){
                case DownloadManager.STATUS_SUCCESSFUL:
                    Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                    break;
                case DownloadManager.STATUS_FAILED:
                    manager.remove(id);
                    break;
                case DownloadManager.STATUS_RUNNING:
                    break;
            }

        }
    }
}

