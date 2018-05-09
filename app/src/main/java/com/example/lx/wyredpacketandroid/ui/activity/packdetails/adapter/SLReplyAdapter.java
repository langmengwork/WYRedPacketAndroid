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
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;

import java.util.List;

public class SLReplyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<SLReplyEntity.DataBean.ListBean> list;

    public SLReplyAdapter(Context context, List<SLReplyEntity.DataBean.ListBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.sl_reply_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder ho = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getHeadimgurl()).into(ho.sl_reply_icon_item);
        ho.sl_reply_name_item.setText(list.get(position).getName());
        ho.sl_reply_content_item.setText(list.get(position).getContent());
        ho.sl_reply_time_item.setText(list.get(position).getCreated_at());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView sl_reply_icon_item;
        private TextView sl_reply_name_item;
        private TextView sl_reply_time_item;
        private TextView sl_reply_content_item;
        public MyViewHolder(View itemView) {
            super(itemView);

            sl_reply_icon_item = itemView.findViewById(R.id.sl_reply_icon_item);
            sl_reply_name_item = itemView.findViewById(R.id.sl_reply_name_item);
            sl_reply_time_item = itemView.findViewById(R.id.sl_reply_time_item);
            sl_reply_icon_item = itemView.findViewById(R.id.sl_reply_icon_item);
            sl_reply_content_item = itemView.findViewById(R.id.sl_reply_content_item);
        }
    }
}
