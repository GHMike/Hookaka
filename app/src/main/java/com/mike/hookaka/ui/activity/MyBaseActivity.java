package com.mike.hookaka.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.badoo.mobile.util.WeakHandler;
import com.mike.hookaka.R;
import com.mike.hookaka.utils.LogUtils;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import java.util.Objects;

public abstract class MyBaseActivity extends RxFragmentActivity {

    protected final String TAG = getClass().getSimpleName();
    protected MyBaseActivity mContext;
    protected WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            handlerMsg(msg);
            return false;
        }
    });


    private AlertDialog mDialogLoading;
    private HRBaseActivityMonitor mHRBaseActivityMonitor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, String.format("%s onCreate", TAG));
        mContext = this;
        initLoading();

        setContentView(initLayout());
        initView();
        initData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d(TAG, String.format("%s onNewIntent", TAG));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG, String.format("%s onResume", TAG));
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideLoading();
        LogUtils.d(TAG, String.format("%s onPause", TAG));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, String.format("%s onDestroy", TAG));
        if (mHRBaseActivityMonitor != null) {
            mHRBaseActivityMonitor.destroy();
        }
        hideLoading();
        mHandler.removeCallbacksAndMessages(null);
    }

//    protected void quitActivity() {
//        sendBroadcast(new Intent(ConstantUtils.Action.ACTION_QUIT));
//    }

    private void initLoading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        mDialogLoading = builder.create();
        mDialogLoading.setCanceledOnTouchOutside(false);
    }

    public void showLoading() {
        if (mDialogLoading != null && !mDialogLoading.isShowing()) {
            mDialogLoading.show();
            @SuppressLint("InflateParams")
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null);
            mDialogLoading.setContentView(view);
            Objects.requireNonNull(mDialogLoading.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            mDialogLoading.getWindow().getDecorView().setBackgroundColor(0x00000000);
            mDialogLoading.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        }
    }

    public void hideLoading() {
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }

    public WeakHandler getHandler() {
        return mHandler;
    }

    public void setMonitorListener(HRBaseActivityMonitor.Listener listener) {
        if (mHRBaseActivityMonitor == null) {
            mHRBaseActivityMonitor = new HRBaseActivityMonitor(this);
        }
        mHRBaseActivityMonitor.setListener(listener);
    }

    /**
     * 设置根布局
     */
    public abstract int initLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    /**
     * 处理handler消息
     */
    public void handlerMsg(Message msg) {
    }

    ;

}


