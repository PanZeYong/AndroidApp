package com.demo.panju.androidapp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.panju.androidapp.adapter.WrapperRecyclerViewAdapter;

import java.util.ArrayList;


/**
 * Author : PZY
 * Date : 2016.8.17
 */
public class WrapperRecyclerView extends RecyclerView{
    private final static String TAG = "TAG : " + WrapperRecyclerView.class.getSimpleName();
    public final static int BASE_HEADER_VIEW_TYPE = 1;
    public final static int BASE_FOOTER_VIEW_TYPE = 2;

    private Adapter mAdapter;

    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<>();
    private ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<>();

    private boolean isShouldSpan = false;

    public WrapperRecyclerView(Context context) {
        super(context);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) {
        Log.e(TAG, "addHeaderView()");
        FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_HEADER_VIEW_TYPE;
        mHeaderViewInfos.add(info);

        if (null != mAdapter) {
            if (!(mAdapter instanceof WrapperRecyclerViewAdapter)) {
                mAdapter = new WrapperRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
//            mAdapter.notifyDataSetChanged();
        }
    }

    public void addFooterView(View view) {
        Log.e(TAG, "addFooterView()");
        FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_FOOTER_VIEW_TYPE;
        mFooterViewInfos.add(info);

        if (null != mAdapter) {
            if (!(mAdapter instanceof WrapperRecyclerViewAdapter)) {
                mAdapter = new WrapperRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        Log.e(TAG, "addFooterView()");
        if (mHeaderViewInfos.isEmpty() && mFooterViewInfos.isEmpty()) {
            super.setAdapter(adapter);
        } else {
            adapter = new WrapperRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
            super.setAdapter(adapter);
        }

        this.mAdapter = adapter;

        if (isShouldSpan) {
            ((WrapperRecyclerViewAdapter)mAdapter).adjustSpanCount(this);
        }

    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        Log.e(TAG, "setLayoutManager()");
        if (layout instanceof GridLayoutManager || layout instanceof StaggeredGridLayoutManager) {
            this.isShouldSpan = true;
        }
        super.setLayoutManager(layout);
    }

    public class FixedViewInfo {
        public View view;
        public int viewType;
    }
}
