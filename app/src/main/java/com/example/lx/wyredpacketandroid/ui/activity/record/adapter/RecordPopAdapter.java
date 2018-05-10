package com.example.lx.wyredpacketandroid.ui.activity.record.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordPopEntity;

import java.util.ArrayList;

public class RecordPopAdapter extends RecyclerView.Adapter{

    private Context context;
    private ArrayList<RecordPopEntity> list;
    private TitleListner listner;

    public RecordPopAdapter(Context context, ArrayList<RecordPopEntity> list) {

        this.context = context;
        this.list = list;
    }

    public void setListner(TitleListner listner){
        this.listner = listner;
    }

    public interface TitleListner {

        void onCheck(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.record_pop_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        MyViewHolder ho = (MyViewHolder) holder;
        ho.record_pop_tv_item.setText(list.get(position).getTitle());

        if (list.get(position).isState()) {

            ho.record_pop_tv_item.setChecked(true);
        } else {

            ho.record_pop_tv_item.setChecked(false);
        }

        ho.record_pop_tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listner != null) {
                    listner.onCheck(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private CheckBox record_pop_tv_item;
        private LinearLayout record_pop_layout_item;
        public MyViewHolder(View itemView) {
            super(itemView);

            record_pop_tv_item = itemView.findViewById(R.id.record_pop_tv_item);

            record_pop_layout_item = itemView.findViewById(R.id.record_pop_layout_item);

        }
    }
}
