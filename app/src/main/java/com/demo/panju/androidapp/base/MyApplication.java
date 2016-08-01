package com.demo.panju.androidapp.base;

import android.app.Application;
import android.content.Context;

import com.demo.panju.androidapp.inject.component.AppComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Init operation
 * Author : PZY
 * Date : 2016.7.20
 */
public class MyApplication extends Application{
    private RefWatcher mRefWatcher;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        mRefWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getInstance(Context context) {
        return ((MyApplication)context.getApplicationContext()).mRefWatcher;
    }

    private void initAppComponent() {
//        this.mAppComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .build();

//        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return this.mAppComponent;
    }
}
