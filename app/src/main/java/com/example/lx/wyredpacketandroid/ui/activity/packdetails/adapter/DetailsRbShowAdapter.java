package com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.PackDetailsActivity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.adapter.AddImgAdapter;

import java.util.List;

public class DetailsRbShowAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String>list;

    public DetailsRbShowAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.send_add_img_item, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder ho = (MyViewHolder) holder;

        Glide.with(context).load(list.get(position)).into(ho.img_item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_item;

        public MyViewHolder(View itemView) {
            super(itemView);

            img_item = itemView.findViewById(R.id.img_item);
        }
    }
}
