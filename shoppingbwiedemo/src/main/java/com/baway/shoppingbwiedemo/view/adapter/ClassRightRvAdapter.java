package com.baway.shoppingbwiedemo.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.classs.ClassRightBean;
import com.baway.shoppingbwiedemo.model.classs.ClassRightChildBean;
import com.baway.shoppingbwiedemo.presenter.ClassRightChildPresenter;
import com.baway.shoppingbwiedemo.view.activity.GoodsListActivity;
import com.baway.shoppingbwiedemo.view.iview.IClassRightChildView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作用：
 * 作者：贾涛
 * 时间：2017/6/16
 * 思路：
 */

public class ClassRightRvAdapter extends RecyclerView.Adapter<ClassRightRvAdapter.MyViewHolder> implements IClassRightChildView {

    private Context mContext;
    private List<ClassRightBean.DatasBean.ClassListBean> data = new ArrayList<>();
    private ClassRightChildRvAdapter classRightChildRvAdapter;

    public ClassRightRvAdapter(Context context){
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.class_right_recyclerview_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getGc_name());
        initChildData(holder, position);
    }

    private void initChildData(MyViewHolder holder, int position) {
        ClassRightChildPresenter classRightChildPresenter = new ClassRightChildPresenter();
        classRightChildPresenter.attachView(this);
        final Map<String,String> map = new HashMap<>();
        map.put("act","goods_class");
        map.put("gc_id",data.get(position).getGc_id());
        classRightChildPresenter.getDataFromServer(map);
        classRightChildRvAdapter = new ClassRightChildRvAdapter(mContext);
        holder.recyclerView.setAdapter(classRightChildRvAdapter);

        classRightChildRvAdapter.setOnItemClickListener(new ClassRightChildRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position , List<ClassRightChildBean.DatasBean.ClassListBean> list) {
                Intent intent = new Intent(mContext, GoodsListActivity.class);
                intent.putExtra("gc_id",list.get(position).getGc_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ClassRightBean.DatasBean.ClassListBean> data) {
        if (data != null){
            this.data.clear();
            this.data.addAll(data);
        }
    }

    @Override
    public void callBackSucc(String str) {
        Gson gson = new Gson();
        ClassRightChildBean classRightChildBean = gson.fromJson(str, ClassRightChildBean.class);
        classRightChildRvAdapter.setData(classRightChildBean.getDatas().getClass_list());
        classRightChildRvAdapter.notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        RecyclerView recyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.classRightRvTvTitle);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.classRightChildRecyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        }
    }

}
