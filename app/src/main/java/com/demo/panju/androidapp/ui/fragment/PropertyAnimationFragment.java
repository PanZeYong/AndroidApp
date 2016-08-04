package com.demo.panju.androidapp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.adapter.AnimationAdapter;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.data.AnimationOperationTypes;
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.mvp.presenter.PropertyAnimationPresenterImpl;
import com.demo.panju.androidapp.mvp.view.PropertyAnimationView;
import com.demo.panju.androidapp.ui.activity.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : PZY
 * Date : 2016.8.3
 */
public class PropertyAnimationFragment extends BaseFragment implements PropertyAnimationView{

    @BindView(R.id.iv_ball)
    ImageView mBall;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject PropertyAnimationPresenterImpl mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        AnimationAdapter adapter = new AnimationAdapter(mContext);

        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        this.mRecyclerView.setAdapter(adapter);

        adapter.refresh(AnimationOperationTypes.getPropertyAnimationOperationType());
        adapter.setOnItemListener(onItemListener);
    }

    @Override
    public ImageView getImageView() {
        return this.mBall;
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }


    private AnimationAdapter.OnItemListener onItemListener = new AnimationAdapter.OnItemListener() {
        @Override
        public void onItemClick(View view, int position) {
            mPresenter.click(position);
        }
    };

    public static PropertyAnimationFragment newInstance() {
        return new PropertyAnimationFragment();
    }

}
