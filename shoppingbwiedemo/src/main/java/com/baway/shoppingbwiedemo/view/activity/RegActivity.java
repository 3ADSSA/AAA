package com.baway.shoppingbwiedemo.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.login.LoginBean;
import com.baway.shoppingbwiedemo.presenter.RegPresenter;
import com.baway.shoppingbwiedemo.view.iview.IRegView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity implements IRegView {

    private EditText nameEditText;
    private EditText passWordEditText;
    private EditText passWordRepeatEditText;
    private EditText emailEditText;
    private Button regBtn;
    private TextView backTv;
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
        setListener();
    }

    private void initData() {
        regPresenter = new RegPresenter();
        regPresenter.attachView(this);

    }

    private void setListener() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReg();
            }
        });
    }

    private void setReg() {
        String userName = nameEditText.getText().toString();
        String passWord = passWordEditText.getText().toString();
        String passWordRepeat = passWordRepeatEditText.getText().toString();
        String userEmail = emailEditText.getText().toString();
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passWord)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passWordRepeat)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!passWord.equals(passWordRepeat)){
            Toast.makeText(this,"俩次输入密码不同",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("act","login");
        map.put("op","register");
        map.put("username",userName);
        map.put("password",passWord);
        map.put("password_confirm",passWordRepeat);
        map.put("email",userEmail);
        map.put("client","android");
        regPresenter.getDataFromServer(map);
    }

    private void initView() {
        nameEditText = (EditText) findViewById(R.id.regNameEditText);
        passWordEditText = (EditText) findViewById(R.id.regPassWordEditText);
        passWordRepeatEditText = (EditText) findViewById(R.id.regPassWordRepeatEditText);
        emailEditText = (EditText) findViewById(R.id.regEmailEditText);
        regBtn = (Button) findViewById(R.id.regBtn);
        backTv = (TextView) findViewById(R.id.regBackTv);
    }

    @Override
    public void callBackSuccess(String str) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(str, LoginBean.class);
        if (loginBean.getCode() == 200){
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
