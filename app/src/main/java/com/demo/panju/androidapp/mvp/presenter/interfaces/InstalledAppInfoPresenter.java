package com.demo.panju.androidapp.mvp.presenter.interfaces;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.InstalledAppInfoView;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public interface InstalledAppInfoPresenter extends BasePresenter<InstalledAppInfoView>{
    void load();
}
