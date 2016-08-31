package com.demo.panju.androidapp.mvp.view;

import com.demo.panju.androidapp.base.BaseView;
import com.demo.panju.androidapp.bean.AppInfo;

import java.util.List;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public interface InstalledAppInfoView extends BaseView{

    void getAppInfo(List<AppInfo> appInfoList);

    void showProgress();

    void dismissProgress();
}
