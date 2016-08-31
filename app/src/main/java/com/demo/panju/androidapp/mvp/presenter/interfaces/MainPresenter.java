package com.demo.panju.androidapp.mvp.presenter.interfaces;

import android.view.MenuItem;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.MainView;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface MainPresenter extends BasePresenter<MainView>{
    void clickMenuItem(MenuItem item);
}
