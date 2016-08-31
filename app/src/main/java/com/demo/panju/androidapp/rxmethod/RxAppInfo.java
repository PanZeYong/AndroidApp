package com.demo.panju.androidapp.rxmethod;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.demo.panju.androidapp.base.RxBus;
import com.demo.panju.androidapp.bean.AppInfo;
import com.demo.panju.androidapp.constant.Constant;
import com.demo.panju.androidapp.event.AppInfoEvent;
import com.demo.panju.androidapp.utils.FormatUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public class RxAppInfo {

    private static List<AppInfo> sAppInfoList = new ArrayList<>();

    public static Subscription getAppInfo(Context context) {
        Subscription subscription = queryAppInfo(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AppInfo>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "e");
                    }

                    @Override
                    public void onNext(List<AppInfo> appInfoList) {
                        AppInfoEvent event = new AppInfoEvent(appInfoList);
                        event.setResult(Constant.Result.SUCCESS);
                        RxBus.getInstance().post(event);
                    }
                });
        return subscription;
    }

    private static Observable<List<AppInfo>> queryAppInfo(final Context context) {
        if (sAppInfoList.size() > 0) {
            sAppInfoList.clear();
        }

        Observable<List<AppInfo>> observable = Observable.create(new Observable.OnSubscribe<List<AppInfo>>() {
            @Override
            public void call(Subscriber<? super List<AppInfo>> subscriber) {
                PackageManager packageManager = context.getPackageManager();
                List<PackageInfo> list = packageManager.getInstalledPackages(0);
                for (PackageInfo info : list) {
                    if ((info.applicationInfo.flags & info.applicationInfo.FLAG_SYSTEM) <= 0) {
                        AppInfo appInfo = new AppInfo();
                        appInfo.setAppName(packageManager.getApplicationLabel(info.applicationInfo).toString());
                        appInfo.setVersionCode(info.versionCode + "");
                        appInfo.setVersionName(info.versionName);
                        appInfo.setPackageName(info.packageName);
                        appInfo.setInstallTime(FormatUtil.formatFileTime(info.firstInstallTime));
                        appInfo.setUpdateTime(FormatUtil.formatFileTime(info.lastUpdateTime));
                        appInfo.setIcon(packageManager.getApplicationIcon(info.applicationInfo));
                        appInfo.setInstallLocation(info.applicationInfo.sourceDir);
                        getSize(packageManager, appInfo);
                        sAppInfoList.add(appInfo);
                    }
                }

                if (subscriber.isUnsubscribed()) {
                    return;
                }

                subscriber.onNext(sAppInfoList);

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        }).take(3);

        return observable;
    }

    private static void getSize(PackageManager packageManager, final AppInfo appInfo) {
        if (null != appInfo.getPackageName()) {
            try {
                Method getPackageSizeInfo = packageManager.getClass().
                        getDeclaredMethod("getPackageSizeInfo", String.class, int.class, IPackageStatsObserver.class);
                getPackageSizeInfo.invoke(packageManager, appInfo.getPackageName(), Process.myUid() / 100000, new IPackageStatsObserver.Stub() {
                    @Override
                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                        appInfo.setDataSize(FormatUtil.formatFileSize(pStats.dataSize + pStats.externalDataSize));
                        appInfo.setCacheSize(FormatUtil.formatFileSize(pStats.cacheSize + pStats.externalCacheSize));
                        appInfo.setCodeSize(FormatUtil.formatFileSize(pStats.codeSize + pStats.externalCodeSize));
                    }
                });
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
