package com.demo.panju.androidapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.demo.panju.androidapp.R;

/**
 * Author : PZY
 * Date : 2016.8.18
 */
public class GalleryViewHolder extends RecyclerView.ViewHolder{
    public TextView tv;

    public GalleryViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}
