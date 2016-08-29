package com.demo.panju.androidapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.panju.androidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : PZY
 * Date : 2016.8.29
 */
public class InstalledAppInfoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.icon)
    public ImageView mIcon;
    @BindView(R.id.app_name)
    public TextView mAppName;
    @BindView(R.id.version_name)
    public TextView mVersionName;
    @BindView(R.id.version_code)
    public TextView mVersionCode;

    public InstalledAppInfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
