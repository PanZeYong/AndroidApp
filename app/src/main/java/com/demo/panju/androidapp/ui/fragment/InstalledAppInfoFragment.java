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
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.mvp.presenter.impl.InstalledAppInfoPresenterImpl;
import com.demo.panju.androidapp.mvp.view.InstalledAppInfoView;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : PZY
 * Date : 2016.8.29
 */
public class InstalledAppInfoFragment extends BaseFragment implements
        InstalledAppInfoAdapter.OnItemClickListener, InstalledAppInfoView{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private List<AppInfo> mAppInfoList = new ArrayList<>();

    private boolean isUninstall = false;

    private AppInfo mUninstalledObject;

    private InstalledAppInfoAdapter mAdapter;

    @Inject
    InstalledAppInfoPresenterImpl mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_installed_app_info, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        load();
        return view;
    }

    @Override
    protected void init() {
        getComponent(MainComponent.class).inject(this);
        mPresenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isUninstall) {
            mAppInfoList.remove(mUninstalledObject);
            mAdapter.refresh(mAppInfoList);
            this.isUninstall = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void getAppInfo(List<AppInfo> appInfoList) {
        this.mAppInfoList = appInfoList;
        mAdapter.refresh(mAppInfoList);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void uninstall(int position) {
        this.isUninstall = true;
        this.mUninstalledObject = mAppInfoList.get(position);

        Uri uri = Uri.parse("package:" + mAppInfoList.get(position).getPackageName());
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        startActivity(intent);
    }

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mAdapter = new InstalledAppInfoAdapter(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void load() {
        if (mAppInfoList.size() != 0) {
            mAppInfoList.clear();
        }

        mPresenter.load();
    }

    public static InstalledAppInfoFragment newInstance() {
        return new InstalledAppInfoFragment();
    }
}
