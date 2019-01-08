package com.lxs.demotest;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PopUpWindowTestActivity extends AppCompatActivity {
    private Button mOpenPopWindowBtn;
    private PopupWindow mPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window_test);
        EventBus.getDefault().register(this);
        mOpenPopWindowBtn = findViewById(R.id.btn_open_pop_window);
        mOpenPopWindowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();

            }
        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //外部是否可以点击
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);

        contentView.findViewById(R.id.pop_computer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopUpWindowTestActivity.this, "计算机", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TestEvent event) {
        Log.e("lxs", "PopWindowAct");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
