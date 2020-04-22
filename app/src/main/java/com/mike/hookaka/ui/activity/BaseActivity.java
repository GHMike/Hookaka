package com.mike.hookaka.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.mike.hookaka.R;
import com.mike.hookaka.utils.PermissionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {


    //动态申请权限列表
    private final int PERMISSION_REQUEST_CODE = 0x183;
    private final String[] perms = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CHANGE_NETWORK_STATE
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions();
    }

    //设置需要开启的权限
    private void requestPermissions() {
        PermissionUtils.requestPermissionsIfNeed(this, perms, PERMISSION_REQUEST_CODE);
    }

    /**
     * 某一权限没有被授予，弹出提示框
     */
    private void showPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("请在设置中开启所需权限，以正常使用xxx功能")
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PermissionUtils.launchAppDetailsSettings();
                        finish();
                    }
                });

        final AlertDialog dialog = builder.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSION_REQUEST_CODE) {
            return;
        }

        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                showPermissionDialog();
                return;
            }
        }
    }
}
