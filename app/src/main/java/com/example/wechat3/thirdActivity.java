package com.example.wechat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class thirdActivity extends AppCompatActivity {

    Button back;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        back=findViewById(R.id.back);
        next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(thirdActivity.this,secondActivity.class);
                startActivity(it);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(thirdActivity.this,passwordActivity.class);
                startActivity(it);
            }
        });
    }
}
