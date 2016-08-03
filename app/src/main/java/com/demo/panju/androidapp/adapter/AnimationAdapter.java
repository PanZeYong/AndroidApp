package com.demo.panju.androidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.viewholder.AnimationViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : PZY
 * Date : 2016.7.28
 */
public class AnimationAdapter extends RecyclerView.Adapter<AnimationViewHolder>{
    private Context mContext;

    private LayoutInflater mInflater;

    private OnItemListener mListener;

    private List<String> mDatas;

    public AnimationAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = new ArrayList<>();
    }

    @Override
    public AnimationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_view_animation, parent, false);
        AnimationViewHolder viewHolder = new AnimationViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnimationViewHolder holder, int position) {
        holder.mAnimate.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setOnItemListener(OnItemListener itemListener) {
        this.mListener = itemListener;
    }

    public void refresh(List<String> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public interface OnItemListener {
        void onItemClick(View view, int position);
    }
}
