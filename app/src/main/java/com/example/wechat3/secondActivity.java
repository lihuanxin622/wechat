package com.example.wechat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class secondActivity extends AppCompatActivity {

    Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        user=findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it=new Intent(secondActivity.this,thirdActivity.class);
                startActivity(it);
            }
        });
    }
}
