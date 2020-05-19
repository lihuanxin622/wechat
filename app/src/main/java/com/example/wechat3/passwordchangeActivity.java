package com.example.wechat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class passwordchangeActivity extends AppCompatActivity {

    Button back;
    Button finish;
    EditText password1;
    EditText  password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);

        back=findViewById(R.id.back);
        finish=findViewById(R.id.finish);
        password1=findViewById(R.id.psw1);
        password2=findViewById(R.id.psw2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(passwordchangeActivity.this,fifthActivity.class);
                startActivity(it);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
