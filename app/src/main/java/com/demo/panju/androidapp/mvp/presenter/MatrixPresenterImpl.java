package com.demo.panju.androidapp.mvp.presenter;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.panju.androidapp.mvp.view.MatrixView;
import com.demo.panju.androidapp.ui.view.CustomImageView;

import javax.inject.Inject;

/**
 * Author : PZY
 * Date : 2016.8.5
 */
public class MatrixPresenterImpl implements MatrixPresenter {
    private MatrixView matrixView;

    private Matrix matrix;

    private float mWidth;
    private float mHeight;

    @Inject
    public MatrixPresenterImpl() {
        this.matrix = new Matrix();
    }

    @Override
    public void click(int position) {
        switch (position) {
            case 0:
                translate();
                break;

            case 1:
                rotate();
                break;

            default:
                break;
        }
    }

    @Override
    public void attachView(@NonNull MatrixView view) {
        this.matrixView = view;

        this.mWidth = matrixView.getImageView().getBitmap().getWidth();
        this.mHeight = matrixView.getImageView().getBitmap().getHeight();

    }

    @Override
    public void detachView() {
        this.matrixView = null;
    }

    private void translate() {
        matrix.preTranslate(mWidth, mHeight);
        matrixView.getImageView().setImageMatrix(matrix);
        matrixView.getImageView().invalidate();
        outputMatrix();
    }

    private void rotate() {
        matrix.reset();
        //相对于前一个矩阵右乘
        matrix.preRotate(45f);

        //左乘
//        matrix.postRotate(45f);

        //功能与preRotate一样
//        matrix.setRotate(45f);
        matrix.setRotate(45f, mWidth / 2, mHeight / 2);
        //功能与setRotate一样
//        matrix.setTranslate(-mWidth / 2, -mHeight / 2);
//        matrix.postRotate(45f);
//        matrix.postTranslate(mWidth / 2, mHeight / 2);

        matrixView.getImageView().setImageMatrix(matrix);
        matrixView.getImageView().invalidate();
        outputMatrix();
    }

    private void scale() {

    }

    private void outputMatrix() {
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for(int i = 0; i < 3; ++i)
        {
            String temp = new String();
            for(int j = 0; j < 3; ++j)
            {
                temp += matrixValues[3 * i + j ] + "\t";
            }
            Log.e("TAG", temp);
        }
    }
}
