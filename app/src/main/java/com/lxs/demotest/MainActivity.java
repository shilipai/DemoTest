package com.lxs.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartPopWindowActBtn;
    private Button mTestEventbusBtn;
    private Button mTestViewTreeBtn;
    private Button mTestRegisterProgressViewBtn;
    private Button mTestLayoutInflaterSetFactoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        mStartPopWindowActBtn = findViewById(R.id.btn_start_pop_window_test_act);
        mStartPopWindowActBtn.setOnClickListener(this);
        mTestEventbusBtn = findViewById(R.id.btn_test_eventbus);
        mTestEventbusBtn.setOnClickListener(this);
        mTestViewTreeBtn = findViewById(R.id.btn_test_view_tree);
        mTestViewTreeBtn.setOnClickListener(this);
        mTestRegisterProgressViewBtn = findViewById(R.id.btn_test_register_progress_view);
        mTestRegisterProgressViewBtn.setOnClickListener(this);
        mTestLayoutInflaterSetFactoryBtn = findViewById(R.id.btn_test_layout_inflater_set_factory);
        mTestLayoutInflaterSetFactoryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_pop_window_test_act:
                startActivity(new Intent(this, PopUpWindowTestActivity.class));
                break;
            case R.id.btn_test_eventbus:
                startActivity(new Intent(this, EventBusTestActivity.class));
                break;
            case R.id.btn_test_view_tree:
                startActivity(new Intent(this, TestViewTreeActivity.class));
                break;
            case R.id.btn_test_register_progress_view:
                startActivity(new Intent(this, TestRegisterProgressViewActivity.class));
                break;
            case R.id.btn_test_layout_inflater_set_factory:
                startActivity(new Intent(this, TestInflaterSetFactoryActivity.class));
                break;

        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TestEvent event) {
        Log.e("lxs", "MainAct");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lxs", "MainActivity OnResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
