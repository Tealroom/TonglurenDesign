package com.wanlichangmeng.tonglurendesign.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wanlichangmeng.tonglurendesign.R;
/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：二级activity，在特殊条件（登陆信息过期）下进入的activity
 */


public class LoginActivity extends AppCompatActivity {
    Button btn_register;
    Button btn_login;
    EditText username;
    EditText userpassword;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        btn_register=(Button)findViewById(R.id.btn_register);
        btn_login=(Button)findViewById(R.id.btn_login);
        username=(EditText)findViewById(R.id.et_user_name);
        userpassword=(EditText)findViewById(R.id.et_psw);




    }


    //点击注册按钮进入注册页面  在xml文件中添加了响应事件
    public void RegitserClicked(View view){
        Intent intent = new Intent(LoginActivity.this,RegiserActivity.class);
        startActivity(intent);
    }


    //点击登录按钮
    public void loginClicked(View view) {

        String userName=username.getText().toString();
        String passWord=userpassword.getText().toString();
        if (login(userName,passWord)) {
            Toast.makeText(LoginActivity.this, "登陆成功（userName，passWord）", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);//这里有错
            startActivity(intent);
        }
        else {
            Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
        }
    }

    //验证登录
    public boolean login(String username,String password) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = "select * from userData where name=? and password=?";
//        Cursor cursor = db.rawQuery(sql, new String[] {username, password});
//        if (cursor.moveToFirst()) {
//            cursor.close();
//            return true;
//        }
//        return false;
        //从数据库中查看是否是正确的用户名和密码
        return true;

    }
}
