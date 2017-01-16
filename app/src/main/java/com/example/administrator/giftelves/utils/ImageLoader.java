package com.example.administrator.giftelves.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class ImageLoader {
    public interface ImageListener {
        void ImageComplete(Bitmap bitMap, String Url);
    }

    static class ImageInfo {
        Bitmap bitmap;
        String url;

        public ImageInfo(Bitmap bitmap, String url) {
            this.bitmap = bitmap;
            this.url = url;
        }
    }

    public static void loadImage(String url, ImageListener listener) {
        new ImageAsynTask(listener).execute(url);
    }

    public static void loadImage(String url, int width, int height, ImageListener listener) {
        new ImageAsynTask(width, height, listener).execute(url);
    }

    static class ImageAsynTask extends AsyncTask<String, Void, ImageInfo> {
        private ImageListener imageListener;
        private int desWidth = 0;
        private int desHeight = 0;

        public ImageAsynTask(int desWidth, int desHeight, ImageListener imageListener) {
            this.desWidth = desWidth;
            this.desHeight = desHeight;
            this.imageListener = imageListener;
        }

        public ImageAsynTask(ImageListener imageListener) {
            this.imageListener = imageListener;
        }

        @Override
        protected ImageInfo doInBackground(String... params) {
            Bitmap map = null;
            //首先从一级缓存中读取图片
            map = ImageUtils.readFromMemory(params[0]);
            if (null == map) {
                map = ImageUtils.readFromDisk(params[0]);
                if (map == null) {
                    try {
                        URL url = new URL(params[0]);
                        //��ȡ����
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //��ȡ��������ȡͼƬ
                        InputStream in = conn.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(in);
                        //BitmapFactory
                        if (desWidth == 0 || desHeight == 0) {
                            map = BitmapFactory.decodeStream(bis);
                        } else {
                            //对图片进行压缩
                            byte[] buf = ImageUtils.stream2ByteArray(bis);
                            map = ImageUtils.zipImage(buf, desWidth, desHeight);
                        }
                        in.close();
                        bis.close();
                        if (map != null) {
                            ImageUtils.save2Disk(params[0], map);
                            ImageUtils.save2Memory(params[0], map);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //将图片存入内存中
                    ImageUtils.save2Memory(params[0], map);
                }
            }
            if (map != null) {
                return new ImageInfo(map, params[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ImageInfo result) {
            if (result != null) {
                imageListener.ImageComplete(result.bitmap, result.url);
            }
        }

    }
}
