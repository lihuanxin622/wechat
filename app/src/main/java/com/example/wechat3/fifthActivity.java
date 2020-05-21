package com.example.wechat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class fifthActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private String code;
    EditText name;
    EditText yzm;
    ImageView img_yzm;
    Button back;
    Button next;
    EditText password1;
    EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        name = findViewById(R.id.ed_uname);
        yzm = findViewById(R.id.ed_yzm);
        img_yzm = findViewById(R.id.img_yzm);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);

        bitmap = CodeUtils.getInstance().createBitmap();
        //获取当前图片验证码的对应内容用于校验
        code = CodeUtils.getInstance().getCode();

        img_yzm.setImageBitmap(bitmap);
        img_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = CodeUtils.getInstance().createBitmap();
                code = CodeUtils.getInstance().getCode();
                img_yzm.setImageBitmap(bitmap);
                Toast.makeText(fifthActivity.this, code, Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(fifthActivity.this, secondActivity.class);
                startActivity(it);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString().trim();
                String yzm1 = yzm.getText().toString().trim();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(fifthActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(yzm1)) {
                    Toast.makeText(fifthActivity.this, "请输验证码", Toast.LENGTH_SHORT).show();
                    return;
                    // md5Psw.equals(); 判断，输入的密码加密后，是否与保存在SharedPreferences中一致
                } else if (!yzm1.equals(code)) {
                    Toast.makeText(fifthActivity.this, "输入验证码不一样", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //一致登录成功
                    Toast.makeText(fifthActivity.this, "输入正确", Toast.LENGTH_SHORT).show();
                    //修改登录成功后保存在SharedPreferences中的密码
                    Intent intent = new Intent(fifthActivity.this, passwordchangeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
