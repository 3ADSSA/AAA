package com.baway.shoppingbwiedemo.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.view.activity.LoginActivity;

public class UserFragment extends Fragment {

    private RelativeLayout userRelativeLayout;
    private TextView userNameTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    private void initData() {
        SharedPreferences mySp = getActivity().getSharedPreferences("MySp", getActivity().MODE_PRIVATE);
        String userName = mySp.getString("userName", "点击登录");
        userNameTv.setText(userName);
    }


    private void setListener() {
        userRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        userRelativeLayout = (RelativeLayout) getView().findViewById(R.id.userRelativeLayout);
        userNameTv = (TextView) getView().findViewById(R.id.userNameTv);
    }

}
