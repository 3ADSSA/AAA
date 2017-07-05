package com.baway.shoppingbwiedemo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.classs.ClassBean;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ClassLeftRvAdapter extends RecyclerView.Adapter<ClassLeftRvAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<ClassBean.DatasBean.ClassListBean> data = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public ClassLeftRvAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.class_left_recyclerview_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getGc_name());
        x.image().bind(holder.ivIcon,data.get(position).getImage());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ClassBean.DatasBean.ClassListBean> data) {
        if (data != null){
            this.data.addAll(data);
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView ivIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.classLeftRvTvTitle);
            ivIcon = (ImageView) itemView.findViewById(R.id.classLeftRvIvIcon);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

}
