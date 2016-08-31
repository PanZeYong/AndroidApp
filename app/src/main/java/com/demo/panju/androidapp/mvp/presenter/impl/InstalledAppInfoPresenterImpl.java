package com.demo.panju.androidapp.mvp.presenter.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.demo.panju.androidapp.base.RxBus;
import com.demo.panju.androidapp.constant.Constant;
import com.demo.panju.androidapp.event.AppInfoEvent;
import com.demo.panju.androidapp.mvp.model.InstalledAppInfoModel;
import com.demo.panju.androidapp.mvp.model.InstalledAppInfoModelImpl;
import com.demo.panju.androidapp.mvp.presenter.interfaces.InstalledAppInfoPresenter;
import com.demo.panju.androidapp.mvp.view.InstalledAppInfoView;

import javax.inject.Inject;

import rx.Observer;
import rx.functions.Action1;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public class InstalledAppInfoPresenterImpl implements InstalledAppInfoPresenter{
    private InstalledAppInfoView mView;

    private InstalledAppInfoModel mModel;

    private Context mContext;

    @Inject
    public InstalledAppInfoPresenterImpl(Context context) {
        this.mContext = context;
        this.mModel = new InstalledAppInfoModelImpl(mContext);

        RxBus.getInstance().toObservable().subscribe(action);
    }

    @Override
    public void attachView(@NonNull InstalledAppInfoView view) {
        if (null == mView) {
            this.mView = view;
        }
    }

    @Override
    public void detachView() {
        if (null != mView) {
            this.mView = null;
        }
    }

    @Override
    public void load() {
        mView.showProgress();
        mModel.load();
    }

    private Action1<? super Object> action = new Action1<Object>() {
        @Override
        public void call(Object o) {
            if (o instanceof AppInfoEvent) {
                if (Constant.Result.SUCCESS.equals(((AppInfoEvent) o).getResult())) {
                    if (null != mView) {
                        mView.dismissProgress();
                        mView.getAppInfo(((AppInfoEvent) o).getAppInfoList());
                    }
                }
            }
        }
    };
}
