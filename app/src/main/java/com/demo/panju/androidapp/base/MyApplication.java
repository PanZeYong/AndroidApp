package com.demo.panju.androidapp.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Init operation
 * Author : PZY
 * Date : 2016.7.20
 */
public class MyApplication extends Application{
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getInstance(Context context) {
        return ((MyApplication)context.getApplicationContext()).mRefWatcher;
    }
}
