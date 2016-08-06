package com.demo.panju.androidapp.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.demo.panju.androidapp.R;

import javax.inject.Inject;

/**
 * Author : PZY
 * Date : 2016-8-5
 */
public class CustomImageView extends ImageView {
    private Matrix matrix;
    private Bitmap bitmap;

    @Inject
    public CustomImageView(Context context) {
        super(context);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("TAG", "onDraw");
        super.onDraw(canvas);
        //变换前
        canvas.drawBitmap(bitmap, 0, 0, null);
        //变化后
        canvas.drawBitmap(bitmap, matrix, null);
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        this.matrix.set(matrix);
        super.setImageMatrix(matrix);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
