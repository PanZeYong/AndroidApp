package com.demo.panju.androidapp.event;

import com.demo.panju.androidapp.bean.AppInfo;

import java.util.List;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public class AppInfoEvent extends BaseEvent{
    private List<AppInfo> mAppInfoList;

    public AppInfoEvent(List<AppInfo> appInfoList) {
        this.mAppInfoList = appInfoList;
    }

    public List<AppInfo> getAppInfoList() {
        return mAppInfoList;
    }

    public void setAppInfoList(List<AppInfo> mAppInfoList) {
        this.mAppInfoList = mAppInfoList;
    }
}
