package com.example.ti2demo.adap;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.ti2demo.DisplayUtils;
import com.example.ti2demo.MainActivity;
import com.example.ti2demo.R;
import com.example.ti2demo.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 王董辉    class
 */
public class AdAp extends RecyclerView.Adapter<AdAp.ViewHodel> {
    List<Bean.ResultsBean> results;
    Context context;
    OnLong onLong;
    private void setImageScale() {
        for (final Bean.ResultsBean girlBean : results) {
            if(girlBean.getScale() == 0){
                Glide.with(context).load(girlBean.getUrl()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        float scale = resource.getIntrinsicWidth() / (float) resource.getIntrinsicHeight();
                        girlBean.setScale(scale);
                        notifyDataSetChanged();
                    }
                });
            }else {
                notifyDataSetChanged();
            }
        }
    }

    public void setOnLong(OnLong onLong) {
        this.onLong = onLong;
    }

    public interface OnLong{
        void onLong(int potion,View view);
    }
    public AdAp(List<Bean.ResultsBean> results, Context context) {
        this.results = results;
        this.context = context;
        setImageScale();
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_kk, null);
        ViewHodel viewHodel = new ViewHodel(view);
        return viewHodel;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel viewHodel, final int i) {
        ViewHodel viewH=viewHodel;
        Bean.ResultsBean resultsBean = results.get(i);
        final ViewGroup.LayoutParams layoutParams = viewH.iv.getLayoutParams();
        layoutParams.width = DisplayUtils.getScreenWidth((Activity) context) / 2 - DisplayUtils.dp2px(context,8);
        if(resultsBean.getScale()!=0){
            layoutParams.height = (int) (layoutParams.width/ resultsBean.getScale());
        }

        Glide.with(context).load(results.get(i).getUrl())
                .transition(new DrawableTransitionOptions().crossFade())
                .into(viewH.iv);
        viewH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLong.onLong(i,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        ImageView iv;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
        }
    }
}
