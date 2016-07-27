package com.demo.panju.androidapp.animation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * /**
 * Author : PZY
 * Date : 2016.7.27
 */

public class ViewAnimationFragment extends BaseFragment {
    @BindView(R.id.iv_ball)
    ImageView mBall;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_animation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_alpha, R.id.btn_translate, R.id.btn_rotate, R.id.btn_scale})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha:
                alpha();
                break;

            case R.id.btn_translate:
                translate();
                break;

            case R.id.btn_rotate:
                rotate();
                break;

            case R.id.btn_scale:
                scale();
                break;

            default:
                break;
        }
    }

    private void alpha() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(1000);
        mBall.startAnimation(alpha);
    }

    private void rotate() {
        RotateAnimation rotate = new RotateAnimation(0, 360, 100, 100);
        rotate.setDuration(1000);
        mBall.startAnimation(rotate);
    }

    private void translate() {
        TranslateAnimation translate = new TranslateAnimation(0, 200, 0, 300);
        translate.setDuration(1000);
        mBall.startAnimation(translate);
    }

    private void scale() {
        ScaleAnimation scale = new ScaleAnimation(0, 2, 0, 2);
        scale.setDuration(1000);
        mBall.startAnimation(scale);
    }

    public static ViewAnimationFragment newInstance() {
        return new ViewAnimationFragment();
    }

}
