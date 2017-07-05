package com.baway.shoppingbwiedemo.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.login.LoginBean;
import com.baway.shoppingbwiedemo.presenter.LoginPresenter;
import com.baway.shoppingbwiedemo.view.iview.ILoginView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private EditText namaEditText;
    private EditText passWordEditText;
    private Button loginBtn;
    private TextView backTv;
    private TextView regTv;
    private TextView backPassWordTv;
    private LoginPresenter loginPresenter;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
        setListener();
    }

    private void initData() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        SharedPreferences mySp = getSharedPreferences("MySp", MODE_PRIVATE);
        edit = mySp.edit();
    }

    private void setListener() {
        regTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });
        //找回密码的监听
        backPassWordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setLogin() {
        String username = namaEditText.getText().toString();
        String password = passWordEditText.getText().toString();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("act","login");
        map.put("username",username);
        map.put("password",password);
        map.put("client","android");
        loginPresenter.getDataFromServer(map);
    }

    private void initView() {
        namaEditText = (EditText) findViewById(R.id.loginNamaEditText);
        passWordEditText = (EditText) findViewById(R.id.loginPassWordEditText);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        backTv = (TextView) findViewById(R.id.loginBackTv);
        regTv = (TextView) findViewById(R.id.loginRegTv);
        backPassWordTv = (TextView) findViewById(R.id.loginBackPassWordTv);
    }

    @Override
    public void callBackSuccess(String str) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(str, LoginBean.class);
        if (loginBean.getCode() == 200){
            edit.putString("userName",loginBean.getDatas().getUsername());
            edit.putString("userKey",loginBean.getDatas().getKey());
            edit.commit();
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"账户或密码错误，请从新输入",Toast.LENGTH_SHORT).show();
        }
    }
}
