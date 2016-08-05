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

    private CustomImageView imageView;

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

            default:
                break;
        }
    }

    @Override
    public void attachView(@NonNull MatrixView view) {
        this.matrixView = view;
    }

    @Override
    public void detachView() {
        this.matrixView = null;
    }

    public void translate() {
        Log.e("TAG", "Width * Height = " + matrixView.getImageView().getBitmap().getWidth() + "*" + matrixView.getImageView().getBitmap().getHeight());
        matrix.postTranslate(matrixView.getImageView().getBitmap().getWidth(), matrixView.getImageView().getBitmap().getHeight());
        matrixView.getImageView().setImageMatrix(matrix);
        matrixView.getImageView().invalidate();

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
