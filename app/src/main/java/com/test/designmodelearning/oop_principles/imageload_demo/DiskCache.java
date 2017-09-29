package com.test.designmodelearning.oop_principles.imageload_demo;

import android.graphics.Bitmap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: duguang
 * Date 2017/9/29 0029.
 */

public class DiskCache implements ImageCache {
    public static final String mCacheDir = "sdcard/cache/";

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = ImageManager.ratio(mCacheDir + url);
        return bitmap;

    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mCacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);//质量压缩,改变文件大小
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
