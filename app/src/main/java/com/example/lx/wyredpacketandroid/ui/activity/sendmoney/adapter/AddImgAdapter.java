package com.example.lx.wyredpacketandroid.ui.activity.sendmoney.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.MainApplication;
import com.example.lx.wyredpacketandroid.utils.CodeUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;


public class AddImgAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Uri> list;
    private boolean showFoot;
    private static int TYPEONE = 0;
    private static int TYPETWO = 1;
    private onLiner liner;

    public void setOnLiner(onLiner liner){
        this.liner = liner;
    }

    public interface onLiner{

        void onImgClick(int position);
    }

    public AddImgAdapter(Context context, List<Uri> list, boolean showFoot) {

        this.showFoot = showFoot;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;

        if (viewType == TYPEONE) {
            view = LayoutInflater.from(context).inflate(R.layout.send_add_img_item_default, null);

            return new AddViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.send_add_img_item, null);

            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (list.size() == position || list.size() <= 0) {

            AddViewHolder ho = (AddViewHolder) holder;
            ho.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //图库选择
                    Matisse.from(MainApplication.activity)
                            .choose(MimeType.allOf()) // 选择 mime 的类型
                            .countable(true)
                            .capture(true)
                            .captureStrategy(new CaptureStrategy(true,"com.example.lx.wyredpacketandroid"))//存储到哪里
                            .maxSelectable(9) // 图片选择的最多数量
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f) // 缩略图的比例
                            .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                            .forResult(CodeUtil.REQUEST_CODE_CHOOSE); // 设置作为标记的请求码


                }
            });

        } else {

            MyViewHolder ho = (MyViewHolder) holder;
            Glide.with(context).load(list.get(position)).into(ho.imageView);
            ho.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (liner != null) {
                        liner.onImgClick(position);

                    }
                }
            });
//            ho.imageView.setImageURI(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() >= 9) {
            return list.size();
        }
        if (list.size() >= 1 && showFoot == true) {
            return list.size();
        }
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {

        if (list.size() == position || list.size() <= 0) {
            return TYPEONE;
        }
        return TYPETWO;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_item);

        }
    }

    private class AddViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout imageView;

        public AddViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.add_img_item);

        }
    }

    public void notify(List<Uri> list, boolean showFoot) {

        this.list = list;
        this.showFoot = showFoot;
        notifyDataSetChanged();
    }
}
