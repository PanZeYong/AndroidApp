package com.demo.panju.androidapp.inject.module;

import android.content.Context;

import com.demo.panju.androidapp.base.MyApplication;
import com.demo.panju.androidapp.network.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Author : PZY
 * Date : 2016.8.1
 */
@Module
public class AppModule {
    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application.getApplicationContext();
    }

}
