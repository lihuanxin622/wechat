package com.example.wechat3;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class passwordchangeActivity extends AppCompatActivity {

    Button back;
    Button finish;
    EditText password;
    EditText password1;
    EditText  password2;
    private String originalPsw;
    private String newPsw;
    private String newPswAgain;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);
        init();
    }
    private void init() {
        back=findViewById(R.id.back);
        finish=findViewById(R.id.finish);
        password=findViewById(R.id.psw);
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

                 originalPsw =password.getText().toString().trim();
                 newPsw=password1.getText().toString().trim();
                 newPswAgain=password2.getText().toString().trim();

                getEditString();
                if (TextUtils.isEmpty(originalPsw)){
                    Toast.makeText(passwordchangeActivity.this,"请输入原始密码",Toast.LENGTH_SHORT).show();
                }else if (!MD5UtilsActivity.md5(originalPsw).equals(readPsw())){
                    Toast.makeText(passwordchangeActivity.this,"输入的密码与原始密码不一致",Toast.LENGTH_SHORT).show();
                }else if (MD5UtilsActivity.md5(newPsw).equals(readPsw())){
                    Toast.makeText(passwordchangeActivity.this,"输入的新密码与原始密码不能一致",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(newPsw)){
                    Toast.makeText(passwordchangeActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(newPswAgain)){
                    Toast.makeText(passwordchangeActivity.this,"请再次输入新密码",Toast.LENGTH_SHORT).show();
                }else if (!newPsw.equals(newPswAgain)){
                    Toast.makeText(passwordchangeActivity.this,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(passwordchangeActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
                    //修改登录成功后保存在SharedPreferences中的密码
                    modifyPsw(newPsw);
                    Intent intent = new Intent(passwordchangeActivity.this,secondActivity.class);
                    startActivity(intent);
                    passwordchangeActivity.this.finish();//关闭当前界面
                }
            }
        });

    }
    //修改登录成功后保存在SharedPreferences中的密码
    private void modifyPsw(String newPsw) {
        String md5Psw = MD5UtilsActivity.md5(newPsw);//对新密码进行加密
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putString(userName,md5Psw);
        editor.commit();

    }
    //从SharedPreferences中读取原始密码
    private String readPsw() {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        return spPsw;

    }

    //h获取控件上的字符串
    private void getEditString() {
        originalPsw = password.getText().toString().trim();
        newPsw = password1.getText().toString().trim();
        newPswAgain = password2.getText().toString().trim();
    }
}



