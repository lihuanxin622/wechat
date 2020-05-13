package com.example.wechat3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applypermission();
        img = findViewById(R.id.img);

        final Intent intent = new Intent(MainActivity.this, secondActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        timer.schedule(task, 100 * 10);
    }


    public void applypermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //检查是否已经给了权限
            int checkpermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.CAMERA);
            if (checkpermission != PackageManager.PERMISSION_GRANTED) {//没有给权限
                Log.e("permission", "动态申请");
                //参数分别是当前活动，权限字符串数组，requestcode
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //grantResults数组与权限字符串数组对应，里面存放权限申请结果
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "已授权", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "拒绝授权", Toast.LENGTH_SHORT).show();
        }
    }
}
