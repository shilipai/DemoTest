package com.lxs.demotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestRegisterProgressViewActivity extends AppCompatActivity {

    private RegisterProgressView mProgressView;
//    private int progress = 3;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_register_progress_view);
        mProgressView = findViewById(R.id.register_progress_view);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressView.setStepByAnimator(++progress);
                int f = 1;
                float ff = (float) f;
                float f1 = f / 3;
                float f2 = ff / 3;

                Log.e("lxs", f + "");
                Log.e("lxs", ff + "");
                Log.e("lxs", f1 + "");
                Log.e("lxs", f2 + "");
            }
        });
    }
}
