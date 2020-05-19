package com.example.wechat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.SaveInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.TimerTask;

public class thirdActivity extends AppCompatActivity {

    Button back;
    Button user2;
    EditText et_user_name;
    EditText et_user_password1;
    EditText et_user_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        init();
    }
    private void init() {
        user2 = findViewById(R.id.user2);
        back = findViewById(R.id.back);
        et_user_name=findViewById(R.id.et_user_name);
        et_user_password1=findViewById(R.id.et_user_password1);
        et_user_password2=findViewById(R.id.et_user_password2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(thirdActivity.this, secondActivity.class);
                startActivity(it);
            }
        });

        user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =et_user_name.getText().toString().trim();
                String password1=et_user_password1.getText().toString().trim();
                String password2=et_user_password2.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(thirdActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(password1)){
                    Toast.makeText(thirdActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(password2)){
                    Toast.makeText(thirdActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!password1.equals(password2)){
                    Toast.makeText(thirdActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                    /**
                     *从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
                     */
                }else if(isExistUserName(username)){
                    Toast.makeText(thirdActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(thirdActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    saveRegisterInfo(username, password1);
                    //注册成功后把账号传递到LoginActivity.java中
                    // 返回值到loginActivity显示
                    Intent data = new Intent();
                    data.putExtra("username", username);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    thirdActivity.this.finish();
                }
            }
        });
    }
    /**
     * 获取控件中的字符串
     */
    private void getEditString(){
        String username =et_user_name.getText().toString().trim();
        String password1=et_user_password1.getText().toString().trim();
        String password2=et_user_password2.getText().toString().trim();
    }
    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    /**
     * 保存账号和密码到SharedPreferences中SharedPreferences
     */
    private void saveRegisterInfo(String username,String psw){
        String md5Psw = MD5UtilsActivity.md5(psw);//把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(username, md5Psw);
        //提交修改 editor.commit();
        editor.commit();
    }
}


