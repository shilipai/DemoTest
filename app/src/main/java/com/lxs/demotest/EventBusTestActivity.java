package com.lxs.demotest;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * 实现功能：
 * Created by lvxinsheng on 2018/12/12 上午11:35
 */
public class EventBusTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_test);
        findViewById(R.id.eventbus_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new TestEvent());

                startActivity(new Intent(EventBusTestActivity.this, PopUpWindowTestActivity.class));

                finish();

            }
        });
    }

}
