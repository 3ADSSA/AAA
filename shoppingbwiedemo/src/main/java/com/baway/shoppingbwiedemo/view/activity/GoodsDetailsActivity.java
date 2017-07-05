package com.baway.shoppingbwiedemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.goodsdetails.GoodsDetailsBean;
import com.baway.shoppingbwiedemo.presenter.GoodsDetailsPresenter;
import com.baway.shoppingbwiedemo.view.adapter.GoodsDetailsRvAdapter;
import com.baway.shoppingbwiedemo.view.iview.IGoodsDetailsView;
import com.google.gson.Gson;

import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作用：
 * 作者：贾涛
 * 时间：2017/6/20
 * 思路：
 */

public class GoodsDetailsActivity extends AppCompatActivity implements IGoodsDetailsView {

    private ImageView ivIcon;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvSold;
    private TextView tvAddress;
    private TextView tvAssess;
    private TextView tvShopp;
    private RecyclerView recyclerView;
    private GoodsDetailsRvAdapter goodsDetailsRvAdapter;
    private TextView tvService;
    private TextView tvShoppingCart;
    private TextView tvAddShoppingCart;
    private TextView tvBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initData();
        setListener();
    }

    private void setListener() {

        goodsDetailsRvAdapter.setOnItemClickListener(new GoodsDetailsRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, List<GoodsDetailsBean.DatasBean.GoodsCommendListBean> list) {
                Toast.makeText(GoodsDetailsActivity.this,"您点击啦"+list.size(),Toast.LENGTH_SHORT).show();
            }
        });

        tvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsDetailsActivity.this,"客服睡着啦",Toast.LENGTH_SHORT).show();
            }
        });

        tvShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsDetailsActivity.this,"您点击了购物车",Toast.LENGTH_SHORT).show();
            }
        });

        tvAddShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsDetailsActivity.this,"您点击了加入购物车",Toast.LENGTH_SHORT).show();
            }
        });

        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsDetailsActivity.this,"您点击了立即购买",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        String goods_id = intent.getStringExtra("goods_id");
        GoodsDetailsPresenter goodsDetailsPresenter = new GoodsDetailsPresenter();
        goodsDetailsPresenter.attachView(this);
        Map<String,String> map = new HashMap<>();
//        http://169.254.65.30/mobile/index.php?act=goods&op=goods_detail&goods_id=100008
        map.put("act","goods");
        map.put("op","goods_detail");
        map.put("goods_id",goods_id);
        goodsDetailsPresenter.getDataFromServer(map);
        goodsDetailsRvAdapter = new GoodsDetailsRvAdapter(GoodsDetailsActivity.this);
        recyclerView.setAdapter(goodsDetailsRvAdapter);
    }

    private void initView() {
        ivIcon = (ImageView) findViewById(R.id.goodsDetailsIvIcon);
        tvName = (TextView) findViewById(R.id.goodsDetailsTvName);
        tvPrice = (TextView) findViewById(R.id.goodsDetailsTvPrice);
        tvSold = (TextView) findViewById(R.id.goodsDetailsTvSold);
        tvAddress = (TextView) findViewById(R.id.goodsDetailsTvAddress);
        tvAssess = (TextView) findViewById(R.id.goodsDetailsTvAssess);
        tvShopp = (TextView) findViewById(R.id.goodsDetailsTvShopp);
        recyclerView = (RecyclerView) findViewById(R.id.goodsDetailsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(GoodsDetailsActivity.this));
        tvService = (TextView) findViewById(R.id.goodsDetailsTvService);
        tvShoppingCart = (TextView) findViewById(R.id.goodsDetailsTvShoppingCart);
        tvAddShoppingCart = (TextView) findViewById(R.id.goodsDetailsTvAddShoppintCart);
        tvBuy = (TextView) findViewById(R.id.goodsDetailsTvBuy);
    }

    @Override
    public void callBackSuccess(String str) {
        Gson gson = new Gson();
        GoodsDetailsBean goodsDetailsBean = gson.fromJson(str, GoodsDetailsBean.class);
        setData(goodsDetailsBean);
        goodsDetailsRvAdapter.setData(goodsDetailsBean.getDatas().getGoods_commend_list());
        goodsDetailsRvAdapter.notifyDataSetChanged();
    }

    private void setData(GoodsDetailsBean goodsDetailsBean) {
        x.image().bind(ivIcon,goodsDetailsBean.getDatas().getGoods_image());
        tvName.setText(goodsDetailsBean.getDatas().getGoods_info().getGoods_name());
        tvPrice.setText(goodsDetailsBean.getDatas().getGoods_info().getGoods_promotion_price());
        tvSold.setText("已售："+goodsDetailsBean.getDatas().getGoods_info().getGoods_collect()+" 件");
        tvAddress.setText("送至 "+goodsDetailsBean.getDatas().getGoods_hair_info().getArea_name()+"" +
                " "+goodsDetailsBean.getDatas().getGoods_hair_info().getIf_store_cn()+"" +
                " "+goodsDetailsBean.getDatas().getGoods_hair_info().getContent());
        tvAssess.setText("评价  好评率："+goodsDetailsBean.getDatas().getGoods_evaluate_info().getGood_percent()+"%  "
                    +goodsDetailsBean.getDatas().getGoods_evaluate_info().getNormal()+"人评价");
        tvShopp.setText(goodsDetailsBean.getDatas().getStore_info().getStore_name());
    }

}
