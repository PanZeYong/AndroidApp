package com.demo.panju.androidapp.network;

import com.demo.panju.androidapp.bean.Category;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Author : PZY
 * Date : 2016.8.16
 *
 */
public interface GalleryApiService {
    @GET("classify")
    Observable<Category> category();
}
