package com.demo.panju.androidapp.bean;

import android.graphics.drawable.Drawable;

/**
 * Author : PZY
 * Date : 2016.8.29
 */
public class AppInfo {

    String appName;
    String versionName;
    String versionCode;
    String packageName;
    String date;
    int size;
    Drawable icon;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "AppName = " + this.appName + "\n" +
               "VersionName = " + this.versionName + "\n" +
               "VersionCode = " + this.versionCode + "\n" +
               "PackageName = " + this.packageName + "\n";
    }
}
