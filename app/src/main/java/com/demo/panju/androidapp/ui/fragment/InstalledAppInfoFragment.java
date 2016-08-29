package com.demo.panju.androidapp.ui.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.adapter.InstalledAppInfoAdapter;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : PZY
 * Date : 2016.8.29
 */
public class InstalledAppInfoFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<AppInfo> mAppInfoList = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
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
    protected void init() {

    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        InstalledAppInfoAdapter adapter = new InstalledAppInfoAdapter(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.refresh(mAppInfoList);
    }

    private void getAppInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PackageManager packageManager = mContext.getPackageManager();
                List<PackageInfo> list = packageManager.getInstalledPackages(0);
        /*Log.e("TAG", "PackageInfo");
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).applicationInfo.flags & list.get(i).applicationInfo.FLAG_SYSTEM) <= 0) {
                Log.e("TAG", "PackageName : " + list.get(i).packageName + " " +
                        "VersionName : " + list.get(i).versionName + " " +
                        "InstallLocation : " + list.get(i).installLocation + " " +
                        "FirstInstallTime : " + list.get(i).firstInstallTime + " " +
                        "VersionCode : " + list.get(i).versionCode + " " +
                        "String : " + list.get(i).toString() + " " );
            }
        }
        Log.e("TAG", "============================================================================");
        Log.e("TAG", "PackageInfo");
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).applicationInfo.flags & list.get(i).applicationInfo.FLAG_SYSTEM) <= 0) {
                Log.e("TAG", "ClassName : " + list.get(i).applicationInfo.className + " " +
                        "String : " + list.get(i).applicationInfo.toString() + " " +
                        "AgentName : " + list.get(i).applicationInfo.backupAgentName + " " +
                        "DataDir : " + list.get(i).applicationInfo.dataDir + " " +
                        "ActivityName : " + list.get(i).applicationInfo.manageSpaceActivityName + " " +
                        "Name : " + packageManager.getApplicationLabel(list.get(i).applicationInfo));
            }
        }*/
                for (int i = 0; i < list.size(); i++) {
                    if ((list.get(i).applicationInfo.flags & list.get(i).applicationInfo.FLAG_SYSTEM) <= 0) {
                        Log.e("TAG", i+"");
                        AppInfo appInfo = new AppInfo();
                        appInfo.setAppName(packageManager.getApplicationLabel(list.get(i).applicationInfo).toString());
                        appInfo.setVersionCode(list.get(i).versionCode + "");
                        appInfo.setVersionName(list.get(i).versionName);
                        appInfo.setPackageName(list.get(i).packageName);
                        appInfo.setIcon(packageManager.getApplicationIcon(list.get(i).applicationInfo));
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

    public static InstalledAppInfoFragment newInstance() {
        return new InstalledAppInfoFragment();
    }
}
