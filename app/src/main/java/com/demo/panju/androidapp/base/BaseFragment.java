package com.demo.panju.androidapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Author : PZY
 * Date : 2016.7.27
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance(mContext).watch(this);
    }

    protected abstract void init();
}
