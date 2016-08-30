package com.demo.panju.androidapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.adapter.InstalledAppInfoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.code_size)
    public TextView mCodeSize;
    @BindView(R.id.data_size)
    public TextView mDataSize;
    @BindView(R.id.cache_size)
    public TextView mCacheSize;
    @BindView(R.id.install_time)
    public TextView mInstallTime;
    @BindView(R.id.update_time)
    public TextView mUpdateTime;
    @BindView(R.id.install_location)
    public TextView mInstallLocation;

    private InstalledAppInfoAdapter.OnItemClickListener mListener;

    public InstalledAppInfoViewHolder(View itemView, InstalledAppInfoAdapter.OnItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mListener = listener;
    }

    @OnClick(R.id.uninstall)
    public void click(View view) {
        switch (view.getId()) {
            case R.id.uninstall:
                if (null != mListener) {
                    mListener.uninstall(getPosition());
                }
                break;

            default:
                break;
        }
    }
}
