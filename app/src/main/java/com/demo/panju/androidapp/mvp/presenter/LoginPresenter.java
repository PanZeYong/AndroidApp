package com.demo.panju.androidapp.mvp.presenter;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.LoginView;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface LoginPresenter extends BasePresenter<LoginView>{
    void login(int id, String username, String password);
}
