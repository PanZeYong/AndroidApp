package com.demo.panju.androidapp.mvp.presenter;

import android.view.MenuItem;
import android.view.View;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.MainView;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface MainPresenter extends BasePresenter<MainView>{
    void clickMenuItem(MenuItem item);

    void clickAnimation(View view);

    void closeDrawer();

    void openDrawer();
}