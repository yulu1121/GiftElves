package com.example.administrator.giftelves.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 实现缓存和转换字节数组的方法
 * Created by Administrator on 2017/1/3.
 */

public class ImageUtils {
    public static Bitmap zipImage(byte[] buf,int desWidth,int desHeight){
        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(buf,0,buf.length,options);
        //获得原始宽和高
        int width = options.outWidth;
        int height = options.outHeight;
        //计算比列
        int sampleSize = 1;
        while((width/sampleSize>=desWidth)||(height/sampleSize>=desHeight)){
            sampleSize*=2;
        }
        //实际加载图片
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        //设置图片格式
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitMap=BitmapFactory.decodeByteArray(buf,0,buf.length,options);
        return  bitMap;
    }
    public static byte[] stream2ByteArray(InputStream in){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = 0;
        byte[] buf = new byte[1024];
        try {
            while((length=in.read(buf))!=-1){
                baos.write(buf,0,length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return  null;
    }
    //建立二级缓存和一级缓存
    //设置内存缓存的大小
    public static final int MEMORY_CACHE_SIZE=1024*1024*5;
    //设置磁盘缓存的大小
    public static final int DISK_CACHE_SIZE=MEMORY_CACHE_SIZE*4;
    //一级缓存
    private  static LruCache<String,Bitmap> sMemoryCache;
    //磁盘缓存
    private static DiskLruCache diskLruCache;
    //初始化缓存
    public static void initCache(Context context){
        if(sMemoryCache==null){
            sMemoryCache = new LruCache<>(MEMORY_CACHE_SIZE);
        }
        if(diskLruCache==null){
            try {
                diskLruCache = DiskLruCache.open(getCacheFile(context),
                        getVersionCode(context),
                        1,DISK_CACHE_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //获取缓存目录
    public static File getCacheFile(Context context){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return context.getExternalCacheDir();
        }
        return context.getCacheDir();
    }
    //获取版本号
    public static int getVersionCode(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //对图片进行加密
    public static String md5(String url){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //对字符串进行加密
            md5.update(url.getBytes());
            //获得加密后的结果
            byte[] buf = md5.digest();
            StringBuilder sb = new StringBuilder();
            //将加密后的字节数组转换为16进制的字节码
            for (int i = 0; i <buf.length ; i++) {
                sb.append(Integer.toHexString(Math.abs(buf[i])));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  null;
    }
    //将数据放入一级缓存中
    public static void save2Memory(String url,Bitmap bitMap){
        sMemoryCache.put(md5(url),bitMap);
    }
    //从一级缓存中读取图片
    public static Bitmap readFromMemory(String url){
        return sMemoryCache.get(md5(url));
    }
    //将图片保存到磁盘缓存中
    public static void save2Disk(String url,Bitmap bitMap){
        try {
            DiskLruCache.Editor edit = diskLruCache.edit(md5(url));
            if(edit!=null){
                OutputStream out=edit.newOutputStream(0);
               boolean compress=bitMap.compress(Bitmap.CompressFormat.PNG,100,out);
                if(compress){
                    //如果解压成功的话，就提交
                    edit.commit();
                }else {
                    //否则就取消
                    edit.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //从磁盘中读取
    public static Bitmap readFromDisk(String url){
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(md5(url));
            if(snapshot!=null){
                InputStream in = snapshot.getInputStream(0);
                Bitmap bitMap = BitmapFactory.decodeStream(in);
                in.close();
                return bitMap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
