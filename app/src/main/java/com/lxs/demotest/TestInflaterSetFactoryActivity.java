package com.lxs.demotest;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TestInflaterSetFactoryActivity extends AppCompatActivity {
    private static final String TAG = "SetFactory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        LayoutInflaterCompat.setFactory(getLayoutInflater(), new LayoutInflaterFactory() {
//            @Override
//            public View onCreateView(View view, String s, Context context, AttributeSet attributeSet) {
//                Log.e(TAG, "=========");
//                Log.e(TAG, "name : " + s);
//                int n = attributeSet.getAttributeCount();
//                for (int i = 0; i < n; i++) {
//                    Log.e(TAG, attributeSet.getAttributeName(i) + " , " + attributeSet.getAttributeValue(i));
//                }
//                return null;
//            }
//        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_inflater_set_factory);
        Log.e(TAG, findViewById(R.id.tv).getClass().getName()); // 不设置Factory的时候，默认替换成android.support.v7.widget.AppCompatTextView
    }
}
