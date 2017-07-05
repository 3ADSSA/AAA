package com.baway.shoppingbwiedemo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.classs.ClassRightChildBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用：
 * 作者：贾涛
 * 时间：2017/6/16
 * 思路：
 */

public class ClassRightChildRvAdapter extends RecyclerView.Adapter<ClassRightChildRvAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<ClassRightChildBean.DatasBean.ClassListBean> data = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public ClassRightChildRvAdapter(Context context){
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.class_right_child_rv_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getGc_name());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ClassRightChildBean.DatasBean.ClassListBean> data) {
        if (data != null){
            this.data.addAll(data);
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (int) v.getTag(), data);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener=onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.classRightChildTVContent);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position, List<ClassRightChildBean.DatasBean.ClassListBean> list);
    }

}
