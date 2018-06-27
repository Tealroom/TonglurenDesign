package com.wanlichangmeng.tonglurendesign.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wanlichangmeng.tonglurendesign.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegiserActivity extends AppCompatActivity {
    //手机号
    EditText et_mobileNums;
    //验证码
    EditText et_vertificationCode;
    //是否同意注册协议
    CheckBox checkbox_isAgree;
    //下一步按钮
    Button   btn_nextStep;
    //获取验证码按钮
    Button   btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_mobileNums=(EditText)findViewById(R.id.et_mobile);
        et_vertificationCode=(EditText)findViewById(R.id.et_vertification_code);
        btn_nextStep=(Button)findViewById(R.id.btn_next_step);
        btn_send=(Button)findViewById(R.id.btn_send);
        checkbox_isAgree=(CheckBox)findViewById(R.id.is_agree);

    }

    //判断手机号码格式
    /*
     * 判断字符串是否符合手机号码格式
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
     * 电信号段: 133,149,153,170,173,177,180,181,189
     *
     * @return 待检测的字符串
     */
    public  static  boolean isMobile(String mobiles){
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        Pattern p=Pattern.compile(telRegex);
        Matcher m=p.matcher(mobiles);
        return m.matches();
    }

    //点击获取验证码事件
    public void GetVerificationCodeClick(View view){
        //首先判断手机号是否正确
        boolean flag=isMobile(et_mobileNums.getText().toString());
        if(flag){
            //发送验证码
        }

    }
    //判断验证码输入是否正确
    public boolean IsCorrectVerificationCode(){
        //et_vertificationCode   存放验证码
        return true;
    }

    //下一步按钮事件
    public void NextStepOnClick(View view){
        //判断是否勾选了同意协议
        if(!checkbox_isAgree.isChecked()){
            Toast.makeText(RegiserActivity.this, "您未同意注册协议，并不能注册哦~", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断验证码是否正确
        if(!IsCorrectVerificationCode()){
            return;
        }

        Intent intent = new Intent(RegiserActivity.this,RegiserDetailActivity.class);
        startActivity(intent);


    }


}
