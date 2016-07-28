package com.demo.panju.androidapp.animation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.animation.viewholder.ViewAnimationViewHolder;

/**
 * Author : PZY
 * Date : 2016.7.28
 */
public class ViewAnimationAdapter extends RecyclerView.Adapter<ViewAnimationViewHolder>{
    private String [] mAnimationType = {"透明", "平移", "旋转", "缩放", "抛物线", "自由落体"};

    private Context mContext;

    private LayoutInflater mInflater;

    private OnItemListener mListener;

    public ViewAnimationAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewAnimationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_view_animation, parent, false);
        ViewAnimationViewHolder viewHolder = new ViewAnimationViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewAnimationViewHolder holder, int position) {
        holder.mAnimate.setText(mAnimationType[position]);
    }

    @Override
    public int getItemCount() {
        return mAnimationType == null ? 0 : mAnimationType.length;
    }

    public void setOnItemListener(OnItemListener itemListener) {
        this.mListener = itemListener;
    }

    public interface OnItemListener {
        void onItemClick(View view, int position);
    }
}
