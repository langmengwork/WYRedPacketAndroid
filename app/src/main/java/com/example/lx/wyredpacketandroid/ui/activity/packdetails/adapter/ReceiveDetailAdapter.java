package com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.ReceiveDetailEntity;

import java.util.List;

public class ReceiveDetailAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<ReceiveDetailEntity.DataBean.UserListBean> list;

    public ReceiveDetailAdapter(Context context, List<ReceiveDetailEntity.DataBean.UserListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.receive_detail_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder ho = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getHeadimgurl()).into(ho.receive_detail_icon_item);
        ho.receive_detail_name_item.setText(list.get(position).getName());
        ho.receive_detail_money_item.setText(list.get(position).getMoney() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView receive_detail_icon_item;
        private TextView receive_detail_name_item;
        private TextView receive_detail_money_item;

        public MyViewHolder(View itemView) {
            super(itemView);

            receive_detail_icon_item = itemView.findViewById(R.id.receive_detail_icon_item);
            receive_detail_name_item = itemView.findViewById(R.id.receive_detail_name_item);
            receive_detail_money_item = itemView.findViewById(R.id.receive_detail_money_item);
        }
    }
}
