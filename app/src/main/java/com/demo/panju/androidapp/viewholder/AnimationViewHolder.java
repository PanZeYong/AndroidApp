package com.demo.panju.androidapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.adapter.AnimationAdapter;

import butterknife.OnClick;

/**
 * Author : PZY
 * Date : 2016.7.28
 */
public class AnimationViewHolder extends RecyclerView.ViewHolder{
    public Button mAnimate;

    private AnimationAdapter.OnItemListener mItemListener;

    public AnimationViewHolder(final View itemView, AnimationAdapter.OnItemListener itemListener) {
        super(itemView);
        this.mItemListener = itemListener;

        mAnimate = (Button)itemView.findViewById(R.id.btn_animate);
        mAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mItemListener) {
                    mItemListener.onItemClick(itemView, getPosition());
                }
            }
        });
    }

    @OnClick(R.id.btn_animate)
    public void click() {
        if (null != mItemListener) {
            mItemListener.onItemClick(itemView, getPosition());
        }
    }
}
