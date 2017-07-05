package com.baway.shoppingbwiedemo.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.classs.ClassBean;
import com.baway.shoppingbwiedemo.model.classs.ClassRightBean;
import com.baway.shoppingbwiedemo.presenter.ClassPresenter;
import com.baway.shoppingbwiedemo.presenter.ClassRightPresenter;
import com.baway.shoppingbwiedemo.view.adapter.ClassLeftRvAdapter;
import com.baway.shoppingbwiedemo.view.adapter.ClassRightChildRvAdapter;
import com.baway.shoppingbwiedemo.view.adapter.ClassRightRvAdapter;
import com.baway.shoppingbwiedemo.view.iview.IClassRightChildView;
import com.baway.shoppingbwiedemo.view.iview.IClassRightView;
import com.baway.shoppingbwiedemo.view.iview.IClassView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ClassFragment extends Fragment implements IClassView,IClassRightView {

    private ClassLeftRvAdapter classLeftRvAdapter;
    private RecyclerView leftRecyclerView;
    private ClassPresenter classPresenter;
    private ClassBean classBean;
    private RecyclerView rightRecyclerView;
    private ClassRightRvAdapter classRightRvAdapter;
    private ClassRightPresenter classRightPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        setDefaultRightData();
        setListener();
    }

    private void setDefaultRightData() {
        classRightPresenter = new ClassRightPresenter();
        classRightPresenter.attachView(this);
        classRightRvAdapter = new ClassRightRvAdapter(getActivity());
        rightRecyclerView.setAdapter(classRightRvAdapter);
        Map<String,String> map = new HashMap<>();
        map.put("act","goods_class");
        map.put("gc_id","1");
        classRightPresenter.getDataFromServer(map);
    }

    private void setListener() {
        classLeftRvAdapter.setOnItemClickListener(new ClassLeftRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Map<String,String> map = new HashMap<>();
                map.put("act","goods_class");
                map.put("gc_id",classBean.getDatas().getClass_list().get(position).getGc_id());
                classRightPresenter.getDataFromServer(map);
            }
        });

    }

    private void initView() {
        leftRecyclerView = (RecyclerView) getView().findViewById(R.id.classLeftRecyclerView);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rightRecyclerView = (RecyclerView) getView().findViewById(R.id.classRightRecyclerView);
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initData() {
        classPresenter = new ClassPresenter();
        classPresenter.attachView(this);
        classLeftRvAdapter = new ClassLeftRvAdapter(getActivity());
        leftRecyclerView.setAdapter(classLeftRvAdapter);
        Map<String,String> map = new HashMap<>();
        map.put("act","goods_class");
        classPresenter.getDataFromServer(map);
    }

    @Override
    public void callBackSuccess(String str) {
        Gson gson = new Gson();
        classBean = gson.fromJson(str, ClassBean.class);
        classLeftRvAdapter.setData(classBean.getDatas().getClass_list());
        classLeftRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void callBackErr(String errMsg, String errCode) {

    }

    @Override
    public void callBackSuc(String str) {
        Gson gson = new Gson();
        ClassRightBean classRightBean = gson.fromJson(str, ClassRightBean.class);
        classRightRvAdapter.setData(classRightBean.getDatas().getClass_list());
        classRightRvAdapter.notifyDataSetChanged();
    }

}
