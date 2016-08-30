package com.demo.panju.androidapp;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import android.os.RemoteException;

/**
 * Author : PZY
 * Date : 2016.8.30
 */
public class PackageSizeObserver extends IPackageStatsObserver.Stub{
    /*** 回调函数，
     * @param pStats ,返回数据封装在PackageStats对象中
     * @param succeeded  代表回调成功
     */
    @Override
    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
    }
}
