package com.example.ti2demo.adap;
/*
 *         王董辉
 *                             class
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ti2demo.R;

import java.util.ArrayList;

public class Vpadap extends PagerAdapter {
    ArrayList<String> strings;
    Context context;
    OnLong onLong;

    public void setOnLong(OnLong onLong) {
        this.onLong = onLong;
    }

    public interface OnLong{

        void onLong(int potion);
    }

    public Vpadap(ArrayList<String> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_vp, null);
        ImageView imageView = view.findViewById(R.id.iv);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText((position+1)+"/"+strings.size());
        Glide.with(context).load(strings.get(position)).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLong.onLong(position);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_vp, null);
        ImageView imageView = view.findViewById(R.id.iv);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText((position+1)+"/"+strings.size());
        Glide.with(context).load(strings.get(position)).into(imageView);
        container.removeView(view);
    }
}
