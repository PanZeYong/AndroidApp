package com.demo.panju.androidapp.inject.module;

import com.demo.panju.androidapp.network.GalleryApi;
import com.demo.panju.androidapp.network.HeaderInterceptor;
import com.demo.panju.androidapp.ui.activity.MainActivity;

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
public class MainModule {
    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @Singleton
    public MainActivity provideMainActivity() {
        return this.mainActivity;
    }

    @Provides
    @Singleton
    public GalleryApi provideGalleryApi(OkHttpClient okHttpClient) {
        return new GalleryApi(okHttpClient);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder =
                new OkHttpClient.Builder()
                        .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                        .readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
        HeaderInterceptor logging = new HeaderInterceptor();
        builder.addInterceptor(logging);
        return builder.build();
    }
}
