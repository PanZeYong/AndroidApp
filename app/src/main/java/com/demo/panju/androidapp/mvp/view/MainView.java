package com.demo.panju.androidapp.mvp.view;

import android.support.v4.app.Fragment;

import com.demo.panju.androidapp.base.BaseView;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface MainView extends BaseView{
    void showFragment(Fragment fragment, int container);

    void setTitle(String title);

    void closeDrawer();

    void openDrawer();
}
