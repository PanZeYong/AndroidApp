package com.demo.panju.androidapp.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author : PZY
 * Date : 2016.8.16
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e("Network",request.toString());
        return chain.proceed(request);
    }
}
