package com.mike.hookaka.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.mike.hookaka.utils.LogUtils;

public class HRBaseActivityMonitor {//extends HRBaseMonitor

    private Listener mListener;

    public HRBaseActivityMonitor(Context context) {
//        super(context);
    }

//    @Override
//    public IntentFilter register() {
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ConstantUtils.Action.ACTION_QUIT);
//        filter.addAction(ConstantUtils.Action.ACTION_SHUTDOWN);
//        filter.addAction(ConstantUtils.Action.ACTION_PHONE_STATE);
//        filter.addAction(ConstantUtils.Action.ACTION_NEW_OUTGOING_CALL);
//        return filter;
//    }
//
//    @Override
//    public void handleReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//        LogUtils.d("onReceive, action = " + action);
//
//        switch (action) {
//            case ConstantUtils.Action.ACTION_QUIT:
//            case ConstantUtils.Action.ACTION_PHONE_STATE:
//            case ConstantUtils.Action.ACTION_NEW_OUTGOING_CALL:
//            case ConstantUtils.Action.ACTION_SHUTDOWN:
//                if (mListener != null) {
//                    mListener.finish();
//                }
//                break;
//
//            default:
//                break;
//        }
//    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void destroy() {
//        unRegister();
        mListener = null;
    }

    /*********************************************Interface*************************************************************/

    public interface Listener {
        void finish();
    }


}


