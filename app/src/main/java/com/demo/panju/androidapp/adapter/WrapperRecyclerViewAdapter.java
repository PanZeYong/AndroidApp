package com.demo.panju.androidapp.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.ui.view.WrapperRecyclerView;

import java.util.ArrayList;

/**
 * Author : PZY
 * Date : 2016.8.17
 */
public class WrapperRecyclerViewAdapter extends RecyclerView.Adapter{
    private final static String TAG = "TAG : " + WrapperRecyclerViewAdapter.class.getSimpleName();

    private final static ArrayList<WrapperRecyclerView.FixedViewInfo> EMPTY_LIST_INFO = new ArrayList<>();
    private ArrayList<WrapperRecyclerView.FixedViewInfo> mHeaderViewInfos;
    private ArrayList<WrapperRecyclerView.FixedViewInfo> mFooterViewInfos;

    private RecyclerView.Adapter mAdapter;

    private boolean isStaggered = false;

    public WrapperRecyclerViewAdapter(ArrayList<WrapperRecyclerView.FixedViewInfo> headerViewInfos,
                                      ArrayList<WrapperRecyclerView.FixedViewInfo> footerViewInfos,
                                      RecyclerView.Adapter adapter) {
        Log.e(TAG, "WrapperRecyclerViewAdapter()");
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
        Log.e(TAG, "onCreateViewHolder()");
        if (viewType == mHeaderViewInfos.get(0).viewType) {
            return viewHolder(mHeaderViewInfos.get(0).view);
        } else if (viewType == mFooterViewInfos.get(0).viewType) {
            return viewHolder(mFooterViewInfos.get(0).view);
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder()");
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (null != mAdapter) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
                return;
            }
        }
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount()");
        if (null != mAdapter) {
            return getHeadersCount() + mAdapter.getItemCount() + getFootersCount();
        } else {
            return getHeadersCount() + getFootersCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType()");
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return mHeaderViewInfos.get(0).viewType;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (null != mAdapter) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }

        return mFooterViewInfos.get(0).viewType;
    }

    @Override
    public long getItemId(int position) {
        Log.e(TAG, "getItemId()");
        int numHeaders = getHeadersCount();
        if (mAdapter != null && position >= numHeaders) {
            int adjPosition = position - numHeaders;
            int adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemId(adjPosition);
            }
        }
        return -1;
    }

    public void adjustSpanCount(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int numHeaders = getHeadersCount();
                    int adjPosition = position - numHeaders;
                    if (position < numHeaders || adjPosition >= mAdapter.getItemCount()) {
                        return manager.getSpanCount();
                    }
                    return 1;
                }
            });
        }

        if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            isStaggered = true;
        }
    }

    private int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    private int getFootersCount() {
        return mFooterViewInfos.size();
    }

    private RecyclerView.ViewHolder viewHolder(View itemView) {
        if (isStaggered) {
            StaggeredGridLayoutManager.LayoutParams params =
                    new StaggeredGridLayoutManager.LayoutParams(
                            StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT,
                            StaggeredGridLayoutManager.LayoutParams.WRAP_CONTENT);
            params.setFullSpan(true);
            itemView.setLayoutParams(params);
        }

        return new RecyclerView.ViewHolder(itemView) {};
    }

}
