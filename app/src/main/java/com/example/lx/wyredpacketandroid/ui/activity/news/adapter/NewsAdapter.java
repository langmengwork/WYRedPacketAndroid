package com.example.lx.wyredpacketandroid.ui.activity.news.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.ui.activity.news.entity.NewsEntity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<NewsEntity.DataBean> list;
    private onListner listner;


    public void setOnListner(onListner listner){

        this.listner = listner;
    }

    public interface onListner{

        void adapterClick(int position);
    }

    public NewsAdapter(Context context, List<NewsEntity.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        MyViewHolder ho = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getHeadimgurl()).into(ho.news_icon_item);
        Glide.with(context).load(list.get(position).getPack_image()).into(ho.news_img_item);
        ho.news_time_item.setText(list.get(position).getCreated_at());
        ho.news_name_item.setText(list.get(position).getName());
        ho.news_content_item.setText(list.get(position).getContent());

        ho.news_layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listner != null) {
                    listner.adapterClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView news_icon_item;
        private TextView news_name_item;
        private TextView news_time_item;
        private TextView news_content_item;
        private ImageView news_img_item;
        private RelativeLayout news_layout_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            news_icon_item = itemView.findViewById(R.id.news_icon_item);
            news_name_item = itemView.findViewById(R.id.news_name_item);
            news_time_item = itemView.findViewById(R.id.news_time_item);
            news_content_item = itemView.findViewById(R.id.news_content_item);
            news_img_item = itemView.findViewById(R.id.news_img_item);
            news_layout_item = itemView.findViewById(R.id.news_layout_item);
        }
    }
}
