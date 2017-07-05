package com.baway.shoppingbwiedemo.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.presenter.CartPresenter;
import com.baway.shoppingbwiedemo.view.iview.ICartView;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class CartFragment extends Fragment implements ICartView {

    private RecyclerView recyclerView;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        SharedPreferences mySp = getActivity().getSharedPreferences("MySp", MODE_PRIVATE);
        String userKey = mySp.getString("userKey", "123456789");
//        http://169.254.65.30/mobile/index.php?act=member_cart&op=cart_list
        CartPresenter cartPresenter = new CartPresenter();
        cartPresenter.attachView(this);
        Map<String,String> map = new HashMap<>();
        map.put("act","member_cart");
        map.put("op","cart_list");
        map.put("key",userKey);
        cartPresenter.getDataFromServer(map);
    }

    private void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tv = (TextView) getView().findViewById(R.id.cartTvContent);
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void callBackSuccess(String str)  {
        Log.e("=====", "callBackSuccess: "+str);
    }
}
