package com.example.wechat3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class talkActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment fragment_first;
    private Fragment fragment_second;
    private Fragment fragment_third;
    private Fragment fragment_four;

    RadioButton chat;
    RadioButton contact;
    RadioButton find;
    RadioButton me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);


    }

    //初始化view视图
    public void initView(){
        chat=findViewById(R.id.bt1);
        contact=findViewById(R.id.bt2);
        find=findViewById(R.id.bt3);
        me=findViewById(R.id.bt4);

        chat.setOnClickListener(this);


    }
}