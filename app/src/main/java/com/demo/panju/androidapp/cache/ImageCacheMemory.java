package com.demo.panju.androidapp.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Author : PZY
 * Date : 2016.8.18
 */
public class ImageCacheMemory {
    /**
     *  source : http://blog.csdn.net/singwhatiwanna/article/details/17566439
     */

    private final static String TAG = ImageCacheMemory.class.getSimpleName();

    //强引用缓存
    private LruCache<String, Bitmap> mLruCache;

    private int LRU_CACHE_SIZE;

    public ImageCacheMemory() {
        this.LRU_CACHE_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);

        this.mLruCache = new LruCache<String, Bitmap>(LRU_CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                if (null != value) {
                    return value.getHeight() * value.getRowBytes();
                } else {
                    return 0;
                }
            }
        };
    }

    public Bitmap getBitmapFromMemory(String url) {
        Bitmap bitmap = mLruCache.get(url);

        if (null != bitmap) {
            mLruCache.remove(url);
            mLruCache.put(url, bitmap);
        }

        return bitmap;
    }

    public void addBitmapToMemory(String url, Bitmap bitmap) {
        if (null != bitmap) {
            mLruCache.put(url, bitmap);
        }
    }
}
