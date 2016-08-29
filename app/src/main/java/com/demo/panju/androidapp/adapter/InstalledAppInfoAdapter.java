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
        InstalledAppInfoViewHolder viewHolder = new InstalledAppInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InstalledAppInfoViewHolder holder, int position) {
        holder.mIcon.setImageDrawable(mAppInfoList.get(position).getIcon());
        holder.mAppName.setText(mAppInfoList.get(position).getAppName());
        holder.mVersionName.setText(mAppInfoList.get(position).getVersionName());
        holder.mVersionCode.setText(mAppInfoList.get(position).getVersionCode());
    }

    @Override
    public int getItemCount() {
        return mAppInfoList == null ? 0 : mAppInfoList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*class InstalledAppInfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.app_name)
        TextView mAppName;
        @BindView(R.id.version_name)
        TextView mVersionName;
        @BindView(R.id.version_code)
        TextView mVersionCode;

        public InstalledAppInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }*/
}
