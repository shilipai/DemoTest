package com.lxs.demotest;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

public class TestViewTreeActivity extends AppCompatActivity {

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_tree);
        Log.e("lxs", Thread.currentThread().getId() + "");
        mBtn = findViewById(R.id.btn);
        mBtn.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (mBtn.getViewTreeObserver().isAlive()) {
                    mBtn.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                Log.e("lxs", mBtn.getWidth() + "onPreDraw 获取宽度");
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 200, 0 , 0);
                mBtn.setLayoutParams(params);
                Log.d("===PreDraw", "PreDrawListener..myImageView " +
                        "height:" + mBtn.getMeasuredHeight() + "  ,width:" + mBtn.getMeasuredWidth());
                return false;
            }
        });
        Log.d("===MainActivity", "onCreate执行完毕..mBtn " +
                "height:" + mBtn.getMeasuredHeight() + "  ,width:" + mBtn.getMeasuredWidth());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lxs", mBtn.getWidth() + "onResume 获取宽度");
    }
}
