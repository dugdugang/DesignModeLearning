package com.test.designmodelearning.oop_principles.imageload_demo;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Author: duguang
 * Date 2017/9/29 0029.
 */

public class MemeryCache implements ImageCache {

    private LruCache<String, Bitmap> mLruCache;
    private MemeryCache mInstance;

    public MemeryCache() {
        initLruCache();
    }

    /**
     * 初始化lrucache
     */
    private void initLruCache() {
        mInstance = new MemeryCache();
        //计算可用最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取1/4内存作为缓存
        int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }


    @Override
    public Bitmap get(String url) {
        return mLruCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }
}
