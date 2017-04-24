package com.example.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by zhangying on 2017/4/24.
 */

public class LoginActivity extends BaseActivity {
    private EditText edit_account;
    private EditText edit_password;
    private Button btn_longin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        edit_account=(EditText) findViewById(R.id.account);
        edit_password=(EditText)findViewById(R.id.password);
        btn_longin=(Button)findViewById(R.id.login);
        btn_longin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAccount=edit_account.getText().toString();
                String strPassword=edit_password.getText().toString();
                //如果账号是admin且密码是123456，则认为登录成功
                if ("admin".equals(strAccount)&&"123456".equals(strPassword)){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
