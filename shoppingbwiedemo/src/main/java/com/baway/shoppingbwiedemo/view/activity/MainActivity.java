package com.baway.shoppingbwiedemo.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.view.fragment.CartFragment;
import com.baway.shoppingbwiedemo.view.fragment.ClassFragment;
import com.baway.shoppingbwiedemo.view.fragment.HomeFragment;
import com.baway.shoppingbwiedemo.view.fragment.UserFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mainRadioGroup;
    private RadioButton homeRadioButton;
    private RadioButton classRadioButton;
    private RadioButton cartRadioButton;
    private RadioButton userRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setState();
        initView();
        setRadioButtonSize();
        setDefaultFragment();
    }

    private void setState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明头部
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    private void setRadioButtonSize() {
        Drawable home=getResources().getDrawable(R.mipmap.ic_nav_home);
        home.setBounds(0,0,80,80);
        homeRadioButton.setCompoundDrawables(null,home,null,null);
        Drawable video=getResources().getDrawable(R.mipmap.ic_nav_class);
        video.setBounds(0,0,80,80);
        classRadioButton.setCompoundDrawables(null,video,null,null);
        Drawable headline=getResources().getDrawable(R.mipmap.ic_nav_cart);
        headline.setBounds(0,0,80,80);
        cartRadioButton.setCompoundDrawables(null,headline,null,null);
        Drawable enter=getResources().getDrawable(R.mipmap.ic_nav_user);
        enter.setBounds(0,0,80,80);
        userRadioButton.setCompoundDrawables(null,enter,null,null);
    }

    private void initView() {
        mainRadioGroup = (RadioGroup) findViewById(R.id.mainRadioGroup);
        homeRadioButton = (RadioButton) findViewById(R.id.homeRadioButton);
        classRadioButton = (RadioButton) findViewById(R.id.classRadioButton);
        cartRadioButton = (RadioButton) findViewById(R.id.cartRadioButton);
        userRadioButton = (RadioButton) findViewById(R.id.userRadioButton);
        mainRadioGroup.setOnCheckedChangeListener(this);
    }

    private void setDefaultFragment() {
        Drawable home=getResources().getDrawable(R.mipmap.ic_nav_home_press);
        home.setBounds(0,0,80,80);
        homeRadioButton.setCompoundDrawables(null,home,null,null);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        transaction.replace(R.id.frameContent,homeFragment);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.homeRadioButton :
            {
                setRadioButtonSize();
                Drawable home=getResources().getDrawable(R.mipmap.ic_nav_home_press);
                home.setBounds(0,0,80,80);
                homeRadioButton.setCompoundDrawables(null,home,null,null);
                homeRadioButton.setChecked(true);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HomeFragment homeFragment=new HomeFragment();
                transaction.replace(R.id.frameContent,homeFragment);
                transaction.commit();
            }
            break;
            case R.id.classRadioButton :
            {
                setRadioButtonSize();
                Drawable home=getResources().getDrawable(R.mipmap.ic_nav_class_press);
                home.setBounds(0,0,80,80);
                classRadioButton.setCompoundDrawables(null,home,null,null);
                classRadioButton.setChecked(true);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ClassFragment classFragment=new ClassFragment();
                transaction.replace(R.id.frameContent,classFragment);
                transaction.commit();
            }
            break;
            case R.id.cartRadioButton :
            {
                setRadioButtonSize();
                Drawable home=getResources().getDrawable(R.mipmap.ic_nav_cart_press);
                home.setBounds(0,0,80,80);
                cartRadioButton.setCompoundDrawables(null,home,null,null);
                cartRadioButton.setChecked(true);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                CartFragment cartFragment=new CartFragment();
                transaction.replace(R.id.frameContent,cartFragment);
                transaction.commit();
            }
            break;
            case R.id.userRadioButton :
            {
                setRadioButtonSize();
                Drawable home=getResources().getDrawable(R.mipmap.ic_nav_user_press);
                home.setBounds(0,0,80,80);
                userRadioButton.setCompoundDrawables(null,home,null,null);
                userRadioButton.setChecked(true);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                UserFragment userFragment=new UserFragment();
                transaction.replace(R.id.frameContent,userFragment);
                transaction.commit();
            }
            break;
        }
    }
}
