package com.demo.panju.androidapp.network;

import android.content.Context;
import android.util.Log;

import com.demo.panju.androidapp.bean.Category;
import com.demo.panju.androidapp.constant.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
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

    public GalleryApi(Context context) {
//        OkHttpClient.Builder builder =
//                new OkHttpClient.Builder().connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
//                        .readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
//        HeaderInterceptor logging = new HeaderInterceptor();
//        builder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
//                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.galleryApiService = retrofit.create(GalleryApiService.class);
    }

    public Observable<Category> category() {
        return galleryApiService.category();
    }

    private class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e("Network",request.toString());
            return chain.proceed(request);
        }
    }


}
