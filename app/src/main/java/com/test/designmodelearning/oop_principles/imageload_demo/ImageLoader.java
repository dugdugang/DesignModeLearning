package com.test.designmodelearning.oop_principles.imageload_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: duguang
 * Date 2017/9/29 0029.
 */

public class ImageLoader {
    ImageCache mCache;
    ExecutorService mExecutorService;
    ImageView imageView;
    Bitmap b;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (b != null) {
                imageView.setImageBitmap(b);

            }
        }
    };

    public ImageLoader() {
        mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void setmCache(ImageCache mCache) {
        this.mCache = mCache;
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public void displayImage(final String url, final ImageView imageView) {
        this.imageView = imageView;
        Bitmap bitmap = mCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        Log.i("M-TAG", "111111");
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Log.i("M-TAG", "downstart");
                Bitmap bitmap = loadImage(url);
                if (bitmap == null) {
                    return;
                }
                Log.i("M-TAG", "22222");
                if (imageView.getTag().equals(url)) {
                    b = bitmap;
                    handler.sendEmptyMessage(0);
                    // imageView.setImageBitmap(bitmap);
                    Log.i("M-TAG", "3333333");

                }
                mCache.put(url, bitmap);
                Log.i("M-TAG", "downover");
            }
        });
    }


    /**
     * 下载图片
     *
     * @param imageUrl
     * @return
     */
    private Bitmap loadImage(String imageUrl) {
        Bitmap bitmap = null;
        Log.i("M-TAG", "loadImage0");
        try {
            URL url = new URL(imageUrl);
            Log.i("M-TAG", "loadImage1");


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.i("M-TAG", "loadImage2");

            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            Log.i("M-TAG", "loadImage3");
//            bitmap = ImageManager.ratio(connection.getInputStream());
            //ImageManager.ratio(connection.getInputStream());
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("M-TAG", "" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("M-TAG", "" + e.toString());
        }
        Log.i("M-TAG", "" + (bitmap == null));
        return bitmap;
    }

}
