package com.example.lx.wyredpacketandroid.ui.activity.personal_center.adapter;

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
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.CollectMoneyEntity;
import com.example.lx.wyredpacketandroid.utils.LogUtil;

import java.util.List;

public class CollectMoneyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CollectMoneyEntity.DataBean.ListBean> list;
    private static int PL = 0;
    private static int BL = 1;
    private static int RB = 2;

    public CollectMoneyAdapter(Context context, List<CollectMoneyEntity.DataBean.ListBean> list) {

        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;

        if (viewType == PL) {

            view = LayoutInflater.from(context).inflate(R.layout.dynamic_pl_item, null);
            return new PLViewHolder(view);

        } else if (viewType == BL) {

            view = LayoutInflater.from(context).inflate(R.layout.dynamic_bl_item, null);
            return new BLViewHolder(view);
        } else {

            view = LayoutInflater.from(context).inflate(R.layout.dynamic_rb_item, null);
            return new RBViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (list.get(position).getType() == 1 || list.get(position).getType() == 4) {

            PLViewHolder pl = (PLViewHolder) holder;

            Glide.with(context).load(list.get(position).getPackImg()).into(pl.dynamic_pl_icon);

            pl.dynamic_pl_money.setText("获得"+list.get(position).getMoney()+"红包股");

            pl.dynamic_pl_name.setText(list.get(position).getPackName());

        } else if (list.get(position).getType() == 2) {

            BLViewHolder bl = (BLViewHolder) holder;

            Glide.with(context).load(list.get(position).getPackImg()).into(bl.dynamic_bl_icon);

            if (list.get(position).getImage() != null && list.get(position).getImage().size() > 0) {

                Glide.with(context).load(list.get(position).getImage().get(0)).into(bl.dynamic_bl_img);
            }

            bl.dynamic_bl_name.setText(list.get(position).getPackName());

            bl.dynamic_bl_content.setText(list.get(position).getContent());


        } else {

            RBViewHolder rb = (RBViewHolder) holder;

            Glide.with(context).load(list.get(position).getPackImg()).into(rb.dynamic_rb_icon);

            if (list.get(position).getImage() != null && list.get(position).getImage().size() > 0) {

                Glide.with(context).load(list.get(position).getImage().get(0)).into(rb.dynamic_rb_img);
            }
            rb.dynamic_rb_name.setText(list.get(position).getPackName());

            rb.dynamic_rb_content.setText(list.get(position).getContent());

            if (list.get(position).getStatistics() != null) {
                rb.dynamic_rb_eyes.setText(list.get(position).getStatistics().getViewNum()+"");

                rb.dynamic_rb_news.setText(list.get(position).getStatistics().getMessageNum()+"");

                rb.dynamic_rb_likes.setText(list.get(position).getStatistics().getPraiseNum()+"");

            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getType() == 1 || list.get(position).getType() == 4) {

            return PL;
        } else if (list.get(position).getType() == 2) {

            return BL;
        } else {
            return RB;
        }
    }

    private class PLViewHolder extends RecyclerView.ViewHolder {
        private ImageView dynamic_pl_icon;
        private TextView dynamic_pl_name;
        private TextView dynamic_pl_money;

        public PLViewHolder(View itemView) {
            super(itemView);
            dynamic_pl_icon = itemView.findViewById(R.id.dynamic_pl_icon);
            dynamic_pl_name = itemView.findViewById(R.id.dynamic_pl_name);
            dynamic_pl_money = itemView.findViewById(R.id.dynamic_pl_money);
        }
    }

    private class BLViewHolder extends RecyclerView.ViewHolder {

        private ImageView dynamic_bl_icon;
        private TextView dynamic_bl_name;
        private ImageView dynamic_bl_img;
        private TextView dynamic_bl_content;

        public BLViewHolder(View itemView) {
            super(itemView);

            dynamic_bl_icon = itemView.findViewById(R.id.dynamic_bl_icon);
            dynamic_bl_name = itemView.findViewById(R.id.dynamic_bl_name);
            dynamic_bl_img = itemView.findViewById(R.id.dynamic_bl_img);
            dynamic_bl_content = itemView.findViewById(R.id.dynamic_bl_content);
        }
    }

    private class RBViewHolder extends RecyclerView.ViewHolder {

        private ImageView dynamic_rb_icon;
        private TextView dynamic_rb_name;
        private ImageView dynamic_rb_img;
        private TextView dynamic_rb_content;
        private TextView dynamic_rb_eyes;
        private TextView dynamic_rb_news;
        private TextView dynamic_rb_likes;

        public RBViewHolder(View itemView) {
            super(itemView);

            dynamic_rb_icon = itemView.findViewById(R.id.dynamic_rb_icon);
            dynamic_rb_name = itemView.findViewById(R.id.dynamic_rb_name);
            dynamic_rb_img = itemView.findViewById(R.id.dynamic_rb_img);
            dynamic_rb_content = itemView.findViewById(R.id.dynamic_rb_content);
            dynamic_rb_eyes = itemView.findViewById(R.id.dynamic_rb_eyes);
            dynamic_rb_news = itemView.findViewById(R.id.dynamic_rb_news);
            dynamic_rb_likes = itemView.findViewById(R.id.dynamic_rb_likes);
        }
    }
}
