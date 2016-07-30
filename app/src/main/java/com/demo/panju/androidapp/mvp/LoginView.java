package com.demo.panju.androidapp.mvp;

import com.demo.panju.androidapp.base.BaseView;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface LoginView extends BaseView{
    int getUserId();

    String getUsername();

    String getPassword();

    void showProgress();

    void dismissProgress();
}
