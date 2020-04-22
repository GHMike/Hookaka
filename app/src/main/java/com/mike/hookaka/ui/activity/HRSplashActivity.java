package com.mike.hookaka.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;


import static java.lang.Thread.sleep;


public class HRSplashActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_h_r_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里可以睡几秒钟，如果要放广告的话
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(HRSplashActivity.this, LoginActivity.class));
                        finish();
                    }
                });
            }
        }).start();
    }
}
