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
import com.example.lx.wyredpacketandroid.utils.glideutils.GlideCircleTransform;

import java.util.List;

public class DetailsImgsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> list;

    public DetailsImgsAdapter(Context context, List<String> list) {

        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.details_imgs_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder ho = (MyViewHolder) holder;

        Glide.with(context).load(list.get(position)).transform(new GlideCircleTransform(context)).into(ho.details_imgs_icon_item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView details_imgs_icon_item;
        public MyViewHolder(View itemView) {
            super(itemView);

            details_imgs_icon_item = itemView.findViewById(R.id.details_imgs_icon_item);
        }
    }
}
