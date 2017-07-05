package com.baway.shoppingbwiedemo.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.classs.ClassRightChildBean;
import com.baway.shoppingbwiedemo.model.goodslist.GoodsListBeanc;
import com.baway.shoppingbwiedemo.presenter.GoodsListPresenter;
import com.baway.shoppingbwiedemo.view.adapter.GoodsListAdapter;
import com.baway.shoppingbwiedemo.view.iview.IGoodsListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsListActivity extends AppCompatActivity implements IGoodsListView {

    private RecyclerView goodsListRecyclerView;
    private GoodsListAdapter goodsListAdapter;
    private TextView taxis;
    private TextView volume;
    private TextView screen;
    private ImageView ivIcon;
    private int currentClickCountIvicon = 0;
    private int currentClickCountScreen = 0;
    private GoodsListBeanc goodsListBeanc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
        setListener();
    }

    private void setListener() {

        goodsListAdapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(GoodsListActivity.this,GoodsDetailsActivity.class);
                intent.putExtra("goods_id",goodsListBeanc.getDatas().getGoods_list().get(position).getGoods_id());
                startActivity(intent);
            }
        });

        taxis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder b=new AlertDialog.Builder(GoodsListActivity.this);
                final AlertDialog dialog = b.create();
                View view = View.inflate(GoodsListActivity.this, R.layout.taxis_item, null);
                //设置对话框的位置
                Window window = dialog.getWindow();
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params.x = -400;//设置x坐标
                params.y = -400;//设置y坐标
                window.setAttributes(params);
                dialog.setView(view);
                dialog.show();
                //设置对话框的大小
                window.setLayout(500,530);

                final TextView composite = (TextView) view.findViewById(R.id.tvComposite);
                final TextView moods = (TextView) view.findViewById(R.id.tvMoods);
                final TextView hight = (TextView) view.findViewById(R.id.tvHight);
                final TextView low = (TextView) view.findViewById(R.id.tvLow);

                composite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        taxis.setText(composite.getText());
                        dialog.dismiss();
                    }
                });

                moods.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        taxis.setText(moods.getText());
                        dialog.dismiss();
                    }
                });

                hight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        taxis.setText(hight.getText());
                        dialog.dismiss();
                    }
                });

                low.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        taxis.setText(low.getText());
                        dialog.dismiss();
                    }
                });

            }
        });

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsListActivity.this,"已经是销量优先了",Toast.LENGTH_SHORT).show();
            }
        });

        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentClickCountScreen++;
                if (currentClickCountScreen % 2 == 1){
                    screen.setTextColor(Color.BLACK);
                }else{
                    screen.setTextColor(Color.RED);
                }
            }
        });

        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentClickCountIvicon++;
                if (currentClickCountIvicon % 2 == 1){
                    ivIcon.setImageResource(R.mipmap.ic_goods_list_hor);
                }else{
                    ivIcon.setImageResource(R.mipmap.ic_goods_list_ver);
                }
            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        String gc_id = intent.getStringExtra("gc_id");
        GoodsListPresenter goodsListPresenter = new GoodsListPresenter();
        goodsListPresenter.attachView(this);
        Map<String,String> map = new HashMap<>();
        map.put("act","goods");
        map.put("op","goods_list");
        map.put("page","10");
        map.put("gc_id",gc_id);
        goodsListPresenter.getDataFromServer(map);
        goodsListAdapter = new GoodsListAdapter(this);
        goodsListRecyclerView.setAdapter(goodsListAdapter);
    }

    private void initView() {
        goodsListRecyclerView = (RecyclerView) findViewById(R.id.goodsListRecyclerView);
        goodsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taxis = (TextView) findViewById(R.id.goodsListTvTaxis);
        volume = (TextView) findViewById(R.id.goodsListTvVolume);
        screen = (TextView) findViewById(R.id.goodsListTvScreen);
        ivIcon = (ImageView) findViewById(R.id.goodsListIvicon);
    }

    @Override
    public void callBackSuccess(String str) {
        Gson gson = new Gson();
        goodsListBeanc = gson.fromJson(str, GoodsListBeanc.class);
        goodsListAdapter.setData(goodsListBeanc.getDatas().getGoods_list());
        goodsListAdapter.notifyDataSetChanged();
    }
}
