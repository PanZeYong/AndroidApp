package com.demo.panju.androidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.viewholder.GalleryViewHolder;

import java.util.List;

/**
 * Author : panju
 * Date : 16-8-18
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
    private final static String TAG = "TAG : " + GalleryAdapter.class.getSimpleName();
    private List<String> mDatas;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public GalleryAdapter(Context context) {
        Log.e(TAG, "GalleryAdapter()");
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void refresh(List<String> datas) {
        Log.e(TAG, "refresh()");
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder()");
        View view = mLayoutInflater.inflate(R.layout.adapter_galllery, parent, false);
        GalleryViewHolder viewHolder = new GalleryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder()");
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount()");
        return mDatas == null ? 0 : mDatas.size();
    }
}
