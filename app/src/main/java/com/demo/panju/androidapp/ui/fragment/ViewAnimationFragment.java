package com.demo.panju.androidapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.adapter.AnimationAdapter;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.data.Data;
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.mvp.presenter.impl.ViewAnimationPresenterImpl;
import com.demo.panju.androidapp.mvp.view.ViewAnimationView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * /**
 * Author : PZY
 * Date : 2016.7.27
 */

public class ViewAnimationFragment extends BaseFragment implements ViewAnimationView{
    @BindView(R.id.iv_ball)
    ImageView mBall;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fl)
    FrameLayout mFrameLayout;

    @Inject ViewAnimationPresenterImpl mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animation, container, false);
        ButterKnife.bind(this, view);
        initRecycleView();
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
    }

    @Override
    public void startAnimation(Animation animation) {
        mBall.startAnimation(animation);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        AnimationAdapter adapter = new AnimationAdapter(mContext);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);

        adapter.refresh(Data.getViewAnimationOperationType());
        adapter.setOnItemListener(onItemListener);
    }

    public static ViewAnimationFragment newInstance() {
        return new ViewAnimationFragment();
    }

    private AnimationAdapter.OnItemListener onItemListener = new AnimationAdapter.OnItemListener() {
        @Override
        public void onItemClick(View view, int position) {
            mPresenter.click(position);
        }
    };

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
