package com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;

import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<MessageListEntity.DataBean>  list;

    public ReplyAdapter(Context context, List<MessageListEntity.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.reply_show_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder ho = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getHeadimgurl()).into(ho.reply_icon_item);
        ho.reply_name_item.setText(list.get(position).getName());
        ho.reply_content_item.setText(list.get(position).getContent());
        ho.reply_time_item.setText(list.get(position).getCreated_at());

        MessageListEntity.DataBean.ReturnMessageBean returnMessage = list.get(position).getReturnMessage();
        if (returnMessage != null) {

            ho.reply_sl_layout.setVisibility(View.VISIBLE);

            if (returnMessage.getReturnMessageCount() > 1) {

                ho.reply_sl_count_item.setVisibility(View.VISIBLE);
                ho.reply_sl_content_item.setText("等人");
                ho.reply_sl_count_item.setText("共" + returnMessage.getReturnMessageCount() + "条回复>");
            } else {
                ho.reply_sl_content_item.setText("："+returnMessage.getContent());
            }
            ho.reply_sl_name_item.setText(returnMessage.getName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView reply_icon_item;
        private TextView reply_name_item;
        private TextView reply_content_item;
        private TextView reply_sl_name_item;
        private TextView reply_sl_content_item;
        private TextView reply_sl_count_item,reply_time_item;
        private LinearLayout reply_sl_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            reply_icon_item = itemView.findViewById(R.id.reply_icon_item);
            reply_name_item = itemView.findViewById(R.id.reply_name_item);
            reply_content_item = itemView.findViewById(R.id.reply_content_item);
            reply_sl_name_item = itemView.findViewById(R.id.reply_sl_name_item);
            reply_sl_content_item = itemView.findViewById(R.id.reply_sl_content_item);
            reply_sl_count_item = itemView.findViewById(R.id.reply_sl_count_item);
            reply_time_item = itemView.findViewById(R.id.reply_time_item);
            reply_sl_layout = itemView.findViewById(R.id.reply_sl_layout);
        }
    }
}
