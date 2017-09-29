package com.test.designmodelearning.oop_principles.imageload_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import static android.R.attr.path;

/**
 * Author: duguang
 * Date 2017/9/29 0029.
 */

public class ImageManager {
    public static final int DEFAULT_PX = 300;

    public static Bitmap ratio(String path) {
        return ratio(path, DEFAULT_PX);
    }

    public static Bitmap ratio(String path, int target) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int w = options.outWidth;
        int h = options.outHeight;
        int be = 1;//默认不缩放
        //计算缩放比例
        int minLen = Math.min(w, h);
        if (minLen > target) {
            be = minLen / target;
        }
        if (be <= 0) {
            be = 1;
        }
        //设置缩放比例
        options.inSampleSize = be;

        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap ratio(InputStream is) {
        return ratio(is, DEFAULT_PX);
    }

    public static Bitmap ratio(InputStream is, int target) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
        int w = options.outWidth;
        int h = options.outHeight;
        int be = 1;//默认不缩放
        //计算缩放比例
        int minLen = Math.min(w, h);
        if (minLen > target) {
            be = minLen / target;
        }
        if (be <= 0) {
            be = 1;
        }
        //设置缩放比例
        options.inSampleSize = be;

        return BitmapFactory.decodeStream(is, null, options);
    }
}
