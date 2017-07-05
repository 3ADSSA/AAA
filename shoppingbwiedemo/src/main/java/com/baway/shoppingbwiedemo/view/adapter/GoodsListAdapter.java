package com.baway.shoppingbwiedemo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.shoppingbwiedemo.R;
import com.baway.shoppingbwiedemo.model.goodslist.GoodsListBeanc;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用：
 * 作者：贾涛
 * 时间：2017/6/20
 * 思路：
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<GoodsListBeanc.DatasBean.GoodsListBean> data = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public GoodsListAdapter(Context context){
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.goodslist_recyclerview_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(data.get(position).getGoods_name());
        holder.price.setText(data.get(position).getGoods_price());
        holder.sold.setText("已售："+data.get(position).getIs_presell()+" 件");
        x.image().bind(holder.ivIcon,data.get(position).getGoods_image_url());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<GoodsListBeanc.DatasBean.GoodsListBean> data) {
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener=onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        TextView price;
        TextView sold;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.goodsListRvIvIcon);
            price = (TextView) itemView.findViewById(R.id.goodsListRvTvPrice);
            sold = (TextView) itemView.findViewById(R.id.goodsListRvTvSold);
            name = (TextView) itemView.findViewById(R.id.goodsListRvTvName);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

}
