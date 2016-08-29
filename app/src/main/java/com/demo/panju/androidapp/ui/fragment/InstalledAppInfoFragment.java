package com.demo.panju.androidapp.ui.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseFragment;

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

    private void getAppInfo() {
        PackageManager packageManager = mContext.getPackageManager();
        List<PackageInfo> list = packageManager.getInstalledPackages(0);
        Log.e("TAG", "PackageInfo");
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
        }

    }

    public static InstalledAppInfoFragment newInstance() {
        return new InstalledAppInfoFragment();
    }
}
