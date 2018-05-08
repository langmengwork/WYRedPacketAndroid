package com.example.lx.wyredpacketandroid.ui.activity.sendmoney.adapter;

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
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity.SearchAddressEntity;

import java.util.ArrayList;


public class SearchSiteAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<SearchAddressEntity> list;
    public ItemOnclick ItemOnclick;

    public void setItemOnclick(ItemOnclick ItemOnclick){
        this.ItemOnclick = ItemOnclick;
    }

    public SearchSiteAdapter(Context context, ArrayList<SearchAddressEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.search_address_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        MyViewHolder ho = (MyViewHolder) holder;

        ho.search_address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ItemOnclick != null) {
                    ItemOnclick.siteClick(position);
                }
            }
        });

        ho.search_address_address.setText(list.get(position).getAddress());
        ho.search_address_name.setText(list.get(position).getName());

        if (list.get(position).isIsstate()) {
            ho.search_address_state.setImageResource(R.drawable.search_location_icon);
        } else {
            ho.search_address_state.setImageResource(R.drawable.search_location_point);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView search_address_state;
        TextView search_address_name;
        TextView search_address_address;
        RelativeLayout search_address_layout;

        public MyViewHolder(View itemView) {
            super(itemView);

            search_address_state = itemView.findViewById(R.id.search_address_state);
            search_address_name = itemView.findViewById(R.id.search_address_name);
            search_address_address = itemView.findViewById(R.id.search_address_address);
            search_address_layout = itemView.findViewById(R.id.search_address_layout);
        }
    }

    public interface ItemOnclick{

        void siteClick(int position);
    }
}
