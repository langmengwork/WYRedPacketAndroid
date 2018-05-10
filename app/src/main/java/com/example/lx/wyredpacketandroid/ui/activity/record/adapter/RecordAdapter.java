package com.example.lx.wyredpacketandroid.ui.activity.record.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFiveEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFourEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordOneEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordThreeEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordTwoEntity;
import com.example.lx.wyredpacketandroid.utils.LogUtil;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter {

    private Context context;
    private final int OTYPE = 1;
    private final int TTYPE = 2;
    private final int THTYPE = 3;
    private final int FTYPE = 4;
    private final int FVTYPE = 5;
    private List<RecordOneEntity.DataBean.ListBean> olist;
    private List<RecordTwoEntity.DataBean.ListBean> tlist;
    private List<RecordThreeEntity.DataBean.ListBean> thlist;
    private List<RecordFourEntity.DataBean.ListBean> flist;
    private List<RecordFiveEntity.DataBean.ListBean> fvlist;

    public RecordAdapter(Context context, List<RecordOneEntity.DataBean.ListBean> olist, List<RecordTwoEntity.DataBean.ListBean> tlist, List<RecordThreeEntity.DataBean.ListBean> thlist, List<RecordFourEntity.DataBean.ListBean> flist, List<RecordFiveEntity.DataBean.ListBean> fvlist) {

        this.context = context;
        this.olist = olist;
        this.tlist = tlist;
        this.thlist = thlist;
        this.flist = flist;
        this.fvlist = fvlist;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;

        if (viewType == OTYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.record_o_item, null);

            return new OViewHolder(view);
        } else if (viewType == TTYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.record_t_item, null);

            return new TViewHolder(view);
        } else if (viewType == THTYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.record_th_item, null);

            return new THViewHolder(view);
        } else if (viewType == FTYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.record_f_item, null);

            return new FViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.record_fv_item, null);

            return new FVViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (olist != null && olist.size() > 0) {

            initOview(holder, position);

        } else if (tlist != null && tlist.size() > 0) {

            initTview(holder, position);

        } else if (thlist != null && thlist.size() > 0) {

            initTHview(holder, position);

        } else if (flist != null && flist.size() > 0) {

            initFview(holder, position);

        } else {

            initFVview(holder, position);
        }
    }

    private void initFVview(@NonNull RecyclerView.ViewHolder holder, int position) {

        FVViewHolder ho = (FVViewHolder) holder;
        ho.record_fv_money.setText(fvlist.get(position).getMoney() + "");
        ho.record_fv_put_tv.setText(fvlist.get(position).getRemark());
        ho.record_fv_time.setText(fvlist.get(position).getCreated_at());
        switch (fvlist.get(position).getStatus()) {
            case 0:
                ho.record_fv_state.setText(context.getResources().getString(R.string.record_state_0));
                ho.record_fv_state.setTextColor(context.getResources().getColor(R.color.color_4AA84F));
                break;
            case 1:
                ho.record_fv_state.setText(context.getResources().getString(R.string.record_state_1));
                ho.record_fv_state.setTextColor(context.getResources().getColor(R.color.color_F45E0B));
                break;
            case 2:
                ho.record_fv_state.setText(context.getResources().getString(R.string.record_state_2));
                ho.record_fv_state.setTextColor(context.getResources().getColor(R.color.color_DC5444));
                break;
        }

    }

    private void initFview(@NonNull RecyclerView.ViewHolder holder, int position) {

        FViewHolder ho = (FViewHolder) holder;
        ho.record_f_money.setText(flist.get(position).getStockValue() + "元");
        ho.record_f_put_tv.setText(flist.get(position).getTitle());
        ho.record_f_time.setText(flist.get(position).getCreated_at());
    }

    private void initTHview(@NonNull RecyclerView.ViewHolder holder, int position) {

        THViewHolder ho = (THViewHolder) holder;
        ho.record_th_money.setText(thlist.get(position).getStock() + "元");
        ho.record_th_time.setText(thlist.get(position).getCreated_at());
    }

    private void initTview(@NonNull RecyclerView.ViewHolder holder, int position) {

        TViewHolder ho = (TViewHolder) holder;
        if (tlist.get(position).getStockNum() != null) {
            ho.record_t_money.setText("+"+tlist.get(position).getStockNum());
            ho.record_t_put_tv.setText(tlist.get(position).getTitle());
            ho.record_t_time.setText(tlist.get(position).getCreated_at());
        }

    }

    private void initOview(@NonNull RecyclerView.ViewHolder holder, int position) {

        OViewHolder ho = (OViewHolder) holder;
        ho.record_o_time.setText(olist.get(position).getCreated_at());
        ho.record_o_money.setText("-"+olist.get(position).getMoney() + "元");
    }

    @Override
    public int getItemCount() {

        if (olist != null && olist.size() > 0) {

            return olist.size();
        } else if (tlist != null && tlist.size() > 0) {

            return tlist.size();
        } else if (thlist != null && thlist.size() > 0) {

            return thlist.size();
        } else if (flist != null && flist.size() > 0) {

            return flist.size();
        } else {

            return fvlist.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (olist != null && olist.size() > 0) {

            return OTYPE;
        } else if (tlist != null && tlist.size() > 0) {

            return TTYPE;
        } else if (thlist != null && thlist.size() > 0) {

            return THTYPE;
        } else if (flist != null && flist.size() > 0) {

            return FTYPE;
        } else {

            return FVTYPE;
        }
    }

    private class OViewHolder extends RecyclerView.ViewHolder {
        private TextView record_o_time;
        private TextView record_o_money;

        public OViewHolder(View itemView) {
            super(itemView);

            record_o_time = itemView.findViewById(R.id.record_o_time);
            record_o_money = itemView.findViewById(R.id.record_o_time);
        }
    }

    private class TViewHolder extends RecyclerView.ViewHolder {
        private TextView record_t_put_tv;
        private TextView record_t_time;
        private TextView record_t_money;

        public TViewHolder(View itemView) {
            super(itemView);
            record_t_put_tv = itemView.findViewById(R.id.record_t_put_tv);
            record_t_time = itemView.findViewById(R.id.record_t_time);
            record_t_money = itemView.findViewById(R.id.record_t_money);
        }
    }

    private class THViewHolder extends RecyclerView.ViewHolder {
        private TextView record_th_time;
        private TextView record_th_money;

        public THViewHolder(View itemView) {
            super(itemView);
            record_th_time = itemView.findViewById(R.id.record_th_time);
            record_th_money = itemView.findViewById(R.id.record_th_money);
        }
    }

    private class FViewHolder extends RecyclerView.ViewHolder {
        private TextView record_f_put_tv;
        private TextView record_f_time;
        private TextView record_f_money;

        public FViewHolder(View itemView) {
            super(itemView);
            record_f_put_tv = itemView.findViewById(R.id.record_f_put_tv);
            record_f_time = itemView.findViewById(R.id.record_f_time);
            record_f_money = itemView.findViewById(R.id.record_f_money);
        }
    }

    private class FVViewHolder extends RecyclerView.ViewHolder {
        private TextView record_fv_put_tv;
        private TextView record_fv_time;
        private TextView record_fv_money;
        private TextView record_fv_state;
        public FVViewHolder(View itemView) {
            super(itemView);
            record_fv_put_tv = itemView.findViewById(R.id.record_fv_put_tv);
            record_fv_time = itemView.findViewById(R.id.record_fv_time);
            record_fv_money = itemView.findViewById(R.id.record_fv_money);
            record_fv_state = itemView.findViewById(R.id.record_fv_state);
        }
    }

    public void notify(List<RecordOneEntity.DataBean.ListBean> olist, List<RecordTwoEntity.DataBean.ListBean> tlist, List<RecordThreeEntity.DataBean.ListBean> thlist, List<RecordFourEntity.DataBean.ListBean> flist, List<RecordFiveEntity.DataBean.ListBean> fvlist){

        this.olist = olist;
        this.tlist = tlist;
        this.thlist = thlist;
        this.flist = flist;
        this.fvlist = fvlist;

        notifyDataSetChanged();
    }
}
