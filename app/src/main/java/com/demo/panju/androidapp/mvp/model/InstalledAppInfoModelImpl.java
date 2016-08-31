package com.demo.panju.androidapp.mvp.model;

import android.content.Context;

import com.demo.panju.androidapp.rxmethod.RxAppInfo;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public class InstalledAppInfoModelImpl implements InstalledAppInfoModel {

    private Context mContext;

    public InstalledAppInfoModelImpl (Context context) {
        this.mContext = context;
    }

    @Override
    public void load() {
        RxAppInfo.getAppInfo(mContext);
    }
}
