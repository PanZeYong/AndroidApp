package com.demo.panju.androidapp.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.adapter.InstalledAppInfoAdapter;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.bean.AppInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : PZY
 * Date : 2016.8.29
 */
public class InstalledAppInfoFragment extends BaseFragment implements InstalledAppInfoAdapter.OnItemClickListener{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private List<AppInfo> mAppInfoList = new ArrayList<>();

    private Receiver mReceiver;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mProgressBar.setVisibility(View.GONE);
                    mAppInfoList = (List<AppInfo>) msg.obj;
                    initRecyclerView();
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_installed_app_info, container, false);
        ButterKnife.bind(this, view);
        getAppInfo();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mReceiver = new Receiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addDataScheme("package");
        mContext.registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mReceiver) {
            mContext.unregisterReceiver(mReceiver);
        }
    }

    @Override
    protected void init() {

    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        InstalledAppInfoAdapter adapter = new InstalledAppInfoAdapter(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.refresh(mAppInfoList);
        adapter.setOnItemClickListener(this);
    }

    private void getAppInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
                PackageManager packageManager = mContext.getPackageManager();
                List<PackageInfo> list = packageManager.getInstalledPackages(0);
                for (int i = 0; i < list.size(); i++) {
                    if ((list.get(i).applicationInfo.flags & list.get(i).applicationInfo.FLAG_SYSTEM) <= 0) {
                        AppInfo appInfo = new AppInfo();
                        appInfo.setAppName(packageManager.getApplicationLabel(list.get(i).applicationInfo).toString());
                        appInfo.setVersionCode(list.get(i).versionCode + "");
                        appInfo.setVersionName(list.get(i).versionName);
                        appInfo.setPackageName(list.get(i).packageName);
                        appInfo.setInstallTime(formatFileTime(list.get(i).firstInstallTime));
                        appInfo.setUpdateTime(formatFileTime(list.get(i).lastUpdateTime));
                        appInfo.setIcon(packageManager.getApplicationIcon(list.get(i).applicationInfo));
                        appInfo.setInstallLocation(list.get(i).applicationInfo.sourceDir);
                        getSize(packageManager, appInfo);
                        mAppInfoList.add(appInfo);
                    }
                }
                Message message = mHandler.obtainMessage();
                message.what = 0;
                message.obj = mAppInfoList;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    private void getSize(PackageManager packageManager, final AppInfo appInfo) {
        if (null != appInfo.getPackageName()) {
            try {
                Method getPackageSizeInfo = packageManager.getClass().
                        getDeclaredMethod("getPackageSizeInfo", String.class, int.class, IPackageStatsObserver.class);
                getPackageSizeInfo.invoke(packageManager, appInfo.getPackageName(), Process.myUid() / 100000, new IPackageStatsObserver.Stub() {
                    @Override
                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                        Log.e("TAG", formatFileSize(pStats.cacheSize));
                        Log.e("TAG", formatFileSize(pStats.dataSize));
                        Log.e("TAG", formatFileSize(pStats.codeSize));
                        appInfo.setDataSize(formatFileSize(pStats.dataSize + pStats.externalDataSize));
                        appInfo.setCacheSize(formatFileSize(pStats.cacheSize + pStats.externalCacheSize));
                        appInfo.setCodeSize(formatFileSize(pStats.codeSize + pStats.externalCodeSize));
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

    //系统函数，字符串转换 long -String (kb)
    private String formatFileSize(long size){
        DecimalFormat df = new DecimalFormat("#.##");
        if (size / 1024 < 1024) {
            return Double.parseDouble(df.format((double)size / 1024)) + "KB";
        } else if (size / 1024 / 1024 < 1024) {
            return Double.parseDouble(df.format((double)size / 1024 / 1024)) + "MB";
        } else {
            return Double.parseDouble(df.format((double)size / 1024 / 1024 / 1024)) + "G";
        }
    }

    private String formatFileTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        Date date = new Date(time);
        return sdf.format(date);
    }

    private String installLocation(int installLocation) {
        if (PackageInfo.INSTALL_LOCATION_AUTO == installLocation) {
            return "auto";
        } else if (PackageInfo.INSTALL_LOCATION_INTERNAL_ONLY == installLocation) {
            return "internal only";
        } else if (PackageInfo.INSTALL_LOCATION_PREFER_EXTERNAL == installLocation) {
            return "prefer external";
        } else {
            return "";
        }
    }

    public static InstalledAppInfoFragment newInstance() {
        return new InstalledAppInfoFragment();
    }

    @Override
    public void uninstall(int position) {
        Uri uri = Uri.parse("package:" + mAppInfoList.get(position).getPackageName());
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        startActivity(intent);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getDataString().equals("android.intent.action.PACKAGE_REMOVED")) {
                getAppInfo();
            }
        }
    }
}
