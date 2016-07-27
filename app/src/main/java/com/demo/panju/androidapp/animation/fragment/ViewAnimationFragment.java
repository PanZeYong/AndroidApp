package com.demo.panju.androidapp.animation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * /**
 * Author : PZY
 * Date : 2016.7.27
 */

public class ViewAnimationFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_animation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static ViewAnimationFragment newInstance() {
        return new ViewAnimationFragment();
    }
}
