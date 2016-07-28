package com.demo.panju.androidapp.animation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.animation.adapter.ViewAnimationAdapter;
import com.demo.panju.androidapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * /**
 * Author : PZY
 * Date : 2016.7.27
 */

public class ViewAnimationFragment extends BaseFragment {
    @BindView(R.id.iv_ball)
    ImageView mBall;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fl)
    FrameLayout mFrameLayout;

    private int mWidth;
    private int mHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_animation, container, false);
        ButterKnife.bind(this, view);
        initRecycleView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mWidth = windowManager.getDefaultDisplay().getWidth();
        mHeight = windowManager.getDefaultDisplay().getHeight();

    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        ViewAnimationAdapter adapter = new ViewAnimationAdapter(mContext);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemListener(onItemListener);
    }

    private void alpha() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(1000);
        alpha.willChangeBounds();
        mBall.startAnimation(alpha);
    }

    private void rotate() {
        RotateAnimation rotate = new RotateAnimation(0, 360, 100, 100);
        rotate.setDuration(1000);
        mBall.startAnimation(rotate);

    }

    private void translate() {
        TranslateAnimation translate = new TranslateAnimation(0, mWidth/2, 0, mHeight/2);
        translate.setDuration(1000);
        mBall.startAnimation(translate);
    }

    private void scale() {
        ScaleAnimation scale = new ScaleAnimation(0, 2, 0, 2);
        scale.setDuration(1000);
        mBall.startAnimation(scale);
    }

    private void freeFall() {
        TranslateAnimation freeFall = new TranslateAnimation(0, 0, 0, mHeight);
        freeFall.setInterpolator(new AccelerateInterpolator());
        freeFall.setDuration(1500);
        mBall.startAnimation(freeFall);
    }

    private void animationSet() {
        AnimationSet set = new AnimationSet(false);
        set.setDuration(2000);

        TranslateAnimation translateX = new TranslateAnimation(0, mWidth/4, 0, 0);
        translateX.setInterpolator(new AccelerateInterpolator());
        translateX.setDuration(2000);
        set.addAnimation(translateX);

        TranslateAnimation translateY = new TranslateAnimation(0, 0, 0, mHeight/4);
        translateY.setInterpolator(new LinearInterpolator());
        translateY.setDuration(2000);
        set.addAnimation(translateY);

        ScaleAnimation scale = new ScaleAnimation(1, 1.5f, 1, 1.5f);
        scale.setInterpolator(new AccelerateDecelerateInterpolator());
        scale.setDuration(2000);
        set.addAnimation(scale);

        mBall.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                alpha();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void getViewSelfCoordinate() {
        int top = mBall.getTop();
        int left = mBall.getLeft();
        int right = mBall.getRight();
        int bottom = mBall.getBottom();
        int[] location = new int[2];
        mBall.getLocationOnScreen(location);



        Log.e("TAG", "Top : " + top);
        Log.e("TAG", "left : " + left);
        Log.e("TAG", "right : " + right);
        Log.e("TAG", "bottom : " + bottom);
        Log.e("TAG", "x : " + location[0]);
        Log.e("TAG", "y : " + location[1]);
    }

    public static ViewAnimationFragment newInstance() {
        return new ViewAnimationFragment();
    }

    private ViewAnimationAdapter.OnItemListener onItemListener = new ViewAnimationAdapter.OnItemListener() {
        @Override
        public void onItemClick(View view, int position) {
            switch (position) {
                case 0:
                    alpha();
                    break;

                case 1:
                    translate();
                    break;

                case 2:
                    rotate();
                    break;

                case 3:
                    scale();
                    break;

                case 4:
                    animationSet();
                    break;

                case 5:
                    freeFall();
                    break;

                default:
                    break;
            }
        }
    };

}
