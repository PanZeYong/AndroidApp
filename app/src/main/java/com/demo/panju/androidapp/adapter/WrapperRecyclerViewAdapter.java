package com.demo.panju.androidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.ui.view.WrapperRecyclerView;

import java.util.ArrayList;

/**
 * Author : PZY
 * Date : 2016.8.17
 */
public class WrapperRecyclerViewAdapter extends RecyclerView.Adapter{
    private final static ArrayList<WrapperRecyclerView.FixedViewInfo> EMPTY_LIST_INFO = new ArrayList<>();
    private ArrayList<WrapperRecyclerView.FixedViewInfo> mHeaderViewInfos;
    private ArrayList<WrapperRecyclerView.FixedViewInfo> mFooterViewInfos;

    private RecyclerView.Adapter mAdapter;

    public WrapperRecyclerViewAdapter(ArrayList<WrapperRecyclerView.FixedViewInfo> headerViewInfos,
                                      ArrayList<WrapperRecyclerView.FixedViewInfo> footerViewInfos,
                                      RecyclerView.Adapter adapter) {
        if (null == headerViewInfos) {
            this.mHeaderViewInfos = EMPTY_LIST_INFO;
        } else {
            this.mHeaderViewInfos = headerViewInfos;
        }

        if (null == footerViewInfos) {
            this.mFooterViewInfos = EMPTY_LIST_INFO;
        } else {
            this.mFooterViewInfos = footerViewInfos;
        }

        this.mAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType >= WrapperRecyclerView.BASE_HEADER_VIEW_TYPE &&
                viewType < WrapperRecyclerView.BASE_HEADER_VIEW_TYPE + getHeadersCount()) {
            return new WrapperViewHolder(mHeaderViewInfos.get(viewType - WrapperRecyclerView.BASE_HEADER_VIEW_TYPE).view);
        } else if (viewType >= WrapperRecyclerView.BASE_FOOTER_VIEW_TYPE &&
                viewType < WrapperRecyclerView.BASE_FOOTER_VIEW_TYPE + getFootersCount()) {
            return new WrapperViewHolder(mFooterViewInfos.get(viewType - WrapperRecyclerView.BASE_FOOTER_VIEW_TYPE).view);
        } else {
            return onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (null != mAdapter) {
            return getHeadersCount() + mAdapter.getItemCount() + getFootersCount();
        } else {
            return getHeadersCount() + getFootersCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return mHeaderViewInfos.get(position).viewType;
        }
        int adjPosition = position - numHeaders;
        int adapterPosition = 0;
        if (null != mAdapter) {
            adapterPosition = mAdapter.getItemCount();
            if (adjPosition < adapterPosition) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }

        return mFooterViewInfos.get(position - adapterPosition - getHeadersCount()).viewType;
    }

    private int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    private int getFootersCount() {
        return mFooterViewInfos.size();
    }

    private static class WrapperViewHolder extends RecyclerView.ViewHolder {

        public WrapperViewHolder(View itemView) {
            super(itemView);
        }
    }
}
