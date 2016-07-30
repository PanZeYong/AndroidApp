package com.demo.panju.androidapp.base;

import android.support.annotation.NonNull;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface BasePresenter <T extends BaseView>{
    void attachView(@NonNull T view);

    void detachView();
}
