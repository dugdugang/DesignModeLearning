package com.test.designmodelearning.oop_principles.imageload_demo;

import android.graphics.Bitmap;

/**
 * Author: duguang
 * Date 2017/9/29 0029.
 */

public interface ImageCache {

    Bitmap get(String url);

    void put(String url, Bitmap bitmap);
}
