package com.test.designmodelearning.oop_principles.imageload_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
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
        imageView.setTag(url);
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    b = bitmap;
                    handler.sendEmptyMessage(0);
                    // imageView.setImageBitmap(bitmap);

                }
                mCache.put(url, bitmap);
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
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BitmapFactory.Options options = new BitmapFactory.Options();
            bitmap = ImageManager.ratio(connection.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
