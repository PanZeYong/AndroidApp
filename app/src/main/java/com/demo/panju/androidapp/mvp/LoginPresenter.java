package com.demo.panju.androidapp.mvp;

import com.demo.panju.androidapp.base.BasePresenter;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface LoginPresenter extends BasePresenter<LoginView>{
    void login(int id, String username, String password);
}
