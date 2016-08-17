package com.demo.panju.androidapp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.demo.panju.androidapp.adapter.WrapperRecyclerViewAdapter;

import java.util.ArrayList;


/**
 * Author : PZY
 * Date : 2016.8.17
 */
public class WrapperRecyclerView extends RecyclerView{
    public final static int BASE_HEADER_VIEW_TYPE = 0;
    public final static int BASE_FOOTER_VIEW_TYPE = 1;

    private Adapter mAdapter;

    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<>();
    private ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<>();

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
        FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_HEADER_VIEW_TYPE + mHeaderViewInfos.size();
        mHeaderViewInfos.add(info);

        if (null != mAdapter) {
            if (!(mAdapter instanceof WrapperRecyclerViewAdapter)) {
                mAdapter = new WrapperRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    public void addFooterView(View view) {
        FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_FOOTER_VIEW_TYPE + mFooterViewInfos.size();
        mHeaderViewInfos.add(info);

        if (null != mAdapter) {
            if (!(mAdapter instanceof WrapperRecyclerViewAdapter)) {
                mAdapter = new WrapperRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.isEmpty() && mFooterViewInfos.isEmpty()) {
            super.setAdapter(adapter);
        } else {
            mAdapter = new WrapperRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            super.setAdapter(adapter);
        }
    }

    public class FixedViewInfo {
        public View view;
        public int viewType;

        public FixedViewInfo() {
            throw new RuntimeException("Stub!");
        }
    }
}
