package com.demo.panju.androidapp.network;

import com.demo.panju.androidapp.bean.Category;
import com.demo.panju.androidapp.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Author : PZY
 * Date : 2016.8.16
 */
public class GalleryApi {
    public GalleryApiService galleryApiService;

    public GalleryApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
//                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.galleryApiService = retrofit.create(GalleryApiService.class);
    }

    public Observable<Category> category() {
        return galleryApiService.category();
    }
}
