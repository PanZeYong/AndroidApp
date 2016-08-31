package com.demo.panju.androidapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.adapter.GalleryAdapter;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.listener.RecyclerViewOnScrollListener;
import com.demo.panju.androidapp.mvp.presenter.impl.GalleryPresenterImpl;
import com.demo.panju.androidapp.mvp.presenter.impl.ViewAnimationPresenterImpl;
import com.demo.panju.androidapp.mvp.view.GalleryView;
import com.demo.panju.androidapp.ui.view.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Author : PZY
 * Date : 2016.8.16
 */
public class GalleryFragment extends BaseFragment implements GalleryView {
    private final static String TAG = GalleryFragment.class.getSimpleName();

    @Inject
    GalleryPresenterImpl mPresenter;

    @BindView(R.id.recycler_view)
    CustomRecyclerView mRecyclerView;

    private List<String> mDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    @Override
    protected void init() {
        getComponent(MainComponent.class).inject(this);
        mPresenter.attachView(this);
    }

    private void initRecyclerView() {
        final View footerView = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_footer, null);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        final GalleryAdapter adapter = new GalleryAdapter(mContext);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setOnScrollListener(new RecyclerViewOnScrollListener() {
            @Override
            public void onLoadMore() {
                mRecyclerView.addFooterView(footerView);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.refresh(initData());
    }

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    private List<String> initData() {
        for (int i = 0; i < 10; i++) {
            mDatas.add(i+"");
        }

        return mDatas;
    }
}
