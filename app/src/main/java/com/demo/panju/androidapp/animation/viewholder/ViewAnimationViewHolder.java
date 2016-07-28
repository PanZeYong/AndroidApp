package com.demo.panju.androidapp.animation.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.animation.adapter.ViewAnimationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author : PZY
 * Date : 2016.7.28
 */
public class ViewAnimationViewHolder extends RecyclerView.ViewHolder{
    public Button mAnimate;

    private ViewAnimationAdapter.OnItemListener mItemListener;

    public ViewAnimationViewHolder(final View itemView, ViewAnimationAdapter.OnItemListener itemListener) {
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
