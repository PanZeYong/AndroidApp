package com.demo.panju.androidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.bean.AppInfo;
import com.demo.panju.androidapp.viewholder.InstalledAppInfoViewHolder;

import java.util.List;


/**
 * Author : PZY
 * Date : 2016.8.29
 */
public class InstalledAppInfoAdapter extends RecyclerView.Adapter<InstalledAppInfoViewHolder> {

    private List<AppInfo> mAppInfoList;
    private Context mContext;
    private LayoutInflater mInflater;

    private OnItemClickListener mListener;

    public InstalledAppInfoAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void refresh(List<AppInfo> appInfoList) {
        this.mAppInfoList = appInfoList;
        this.notifyDataSetChanged();
    }

    @Override
    public InstalledAppInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_installed_app_info_item, parent, false);
        InstalledAppInfoViewHolder viewHolder = new InstalledAppInfoViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InstalledAppInfoViewHolder holder, int position) {
        holder.mIcon.setImageDrawable(mAppInfoList.get(position).getIcon());
        holder.mAppName.setText(mContext.getResources().getString(R.string.name) + mAppInfoList.get(position).getAppName());
        holder.mVersionName.setText(mContext.getResources().getString(R.string.version_name) + mAppInfoList.get(position).getVersionName());
        holder.mVersionCode.setText(mContext.getResources().getString(R.string.version_code) + mAppInfoList.get(position).getVersionCode());
        holder.mCacheSize.setText(mContext.getResources().getString(R.string.cache_size) + mAppInfoList.get(position).getCacheSize());
        holder.mCodeSize.setText(mContext.getResources().getString(R.string.code_size) + mAppInfoList.get(position).getCodeSize());
        holder.mDataSize.setText(mContext.getResources().getString(R.string.data_size) + mAppInfoList.get(position).getDataSize());
        holder.mInstallTime.setText(mContext.getResources().getString(R.string.install_time) + mAppInfoList.get(position).getInstallTime());
        holder.mUpdateTime.setText(mContext.getResources().getString(R.string.last_update_time) + mAppInfoList.get(position).getUpdateTime());
        holder.mInstallLocation.setText(mContext.getResources().getString(R.string.install_location) + mAppInfoList.get(position).getInstallLocation());
    }

    @Override
    public int getItemCount() {
        return mAppInfoList == null ? 0 : mAppInfoList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void uninstall(int position);
    }
}
