package com.demo.panju.androidapp.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.demo.panju.androidapp.adapter.CustomRecycleViewAdapter;

import java.util.ArrayList;


public class CustomRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>() ;

    private ArrayList<View> mFootViews = new ArrayList<>() ;

    private Adapter mAdapter ;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view){
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if (mAdapter != null){
            if (!(mAdapter instanceof CustomRecycleViewAdapter)){
                mAdapter = new CustomRecycleViewAdapter(mHeaderViews,mFootViews,mAdapter) ;
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addFooterView(View view){
        mFootViews.clear();
        mFootViews.add(view);
        if (mAdapter != null){
            if (!(mAdapter instanceof CustomRecycleViewAdapter)){
                mAdapter = new CustomRecycleViewAdapter(mHeaderViews,mFootViews,mAdapter) ;
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {

        if (mHeaderViews.isEmpty()&&mFootViews.isEmpty()){
            super.setAdapter(adapter);
        }else {
            adapter = new CustomRecycleViewAdapter(mHeaderViews,mFootViews,adapter) ;
            super.setAdapter(adapter);
        }
        mAdapter = adapter ;
    }

}
