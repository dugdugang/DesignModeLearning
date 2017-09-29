package com.test.designmodelearning.oop_principles.imageload_demo;

import android.graphics.Bitmap;

/**
 * Author: duguang
 * Date 2017/9/29 0029.
 */

public class DoubleCache implements ImageCache {
    private DiskCache mDiskCache;
    private MemeryCache mMemeryCache;

    public DoubleCache() {
        mDiskCache = new DiskCache();
        mMemeryCache = new MemeryCache();
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemeryCache.get(url);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = mDiskCache.get(url);
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemeryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
