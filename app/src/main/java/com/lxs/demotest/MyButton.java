package com.lxs.demotest;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * 实现功能：
 * Created by lvxinsheng on 2018/12/13 下午12:00
 */
public class MyButton extends Button {
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("lxsbtn", Thread.currentThread().getId() + "");
        Log.d("===MyButton","onMeasure 我被调用了"+System.currentTimeMillis());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("===MyButton","onLayout 我被调用了"+System.currentTimeMillis());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("===MyButton", "onDraw 我被调用了"+System.currentTimeMillis());
    }
}
