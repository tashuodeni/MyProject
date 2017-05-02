package com.example.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by zhangying on 2017/4/24.
 */

public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText editAccount;
    private EditText editPassword;
    private Button btn_login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        boolean isRemember=pref.getBoolean("remember_password",false);
        if (isRemember){
            //将账号和密码都设置到文本框中
            String strAccount=pref.getString("account","");
            String strPassword=pref.getString("password","");
            editAccount.setText(strAccount);
            editPassword.setText(strPassword);
            rememberPass.setChecked(true);
        }
    }

    private void init() {
        editAccount = (EditText) findViewById(R.id.account);
        editPassword = (EditText) findViewById(R.id.password);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass=(CheckBox)findViewById(R.id.remember_pass);

        btn_login = (Button) findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAccount = editAccount.getText().toString();
                String strPassword = editPassword.getText().toString();
                //如果账号是admin且密码是123456，则认为登录成功
                if ("admin".equals(strAccount) && "123456".equals(strPassword)) {
                    editor=pref.edit();
                    if (rememberPass.isChecked()){//检查复选框是否被选中
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",strAccount);
                        editor.putString("password",strPassword);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
