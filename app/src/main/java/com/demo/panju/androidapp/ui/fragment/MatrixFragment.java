package com.demo.panju.androidapp.ui.fragment;

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
import com.demo.panju.androidapp.data.Data;
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.mvp.presenter.impl.MatrixPresenterImpl;
import com.demo.panju.androidapp.mvp.view.MatrixView;
import com.demo.panju.androidapp.ui.view.CustomImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : PZY
 * Date : 2016.8.5
 */
public class MatrixFragment extends BaseFragment implements MatrixView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fl)
    FrameLayout mFrameLayout;

    @Inject
    CustomImageView imageView;

    @Inject
    MatrixPresenterImpl presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matrix, container, false);
        ButterKnife.bind(this, view);
        initRecycleView();
        mFrameLayout.addView(imageView);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        return view;
    }

    @Override
    protected void init() {
        getComponent(MainComponent.class).inject(this);
        presenter.attachView(this);
    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        AnimationAdapter adapter = new AnimationAdapter(mContext);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);

        adapter.refresh(Data.getTransformType());
        adapter.setOnItemListener(onItemListener);
    }

    public static MatrixFragment newInstance() {
        return new MatrixFragment();
    }

    @Override
    public CustomImageView getImageView() {
        return this.imageView;
    }

    private AnimationAdapter.OnItemListener onItemListener = new AnimationAdapter.OnItemListener() {
        @Override
        public void onItemClick(View view, int position) {
            presenter.click(position);
        }
    };

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
