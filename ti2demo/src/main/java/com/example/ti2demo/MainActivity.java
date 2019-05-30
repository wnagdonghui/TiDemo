package com.example.ti2demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.ti2demo.adap.AdAp;
import com.example.ti2demo.adap.Vpadap;
import com.example.ti2demo.bean.Bean;
import com.example.ti2demo.contract.Contract;
import com.example.ti2demo.presenter.Presenter;
import com.example.ti2demo.view.MyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 王董辉    class
 */
public class MainActivity extends AppCompatActivity implements Contract.View {
    private RecyclerView mRec;
    private ViewPager mVp;
    private AdAp adAp;
    private ArrayList<String> strings;
    int a;
    private Vpadap vpadap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void getData() {
        Presenter presenter = new Presenter(this);
        presenter.getPresenter();
    }

    private void initView() {
        mRec = (RecyclerView) findViewById(R.id.rec);
        mVp = (ViewPager) findViewById(R.id.vp);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRec.setLayoutManager(staggeredGridLayoutManager);

        mVp.setVisibility(View.GONE);

    }

    @Override
    public void getIfView(final List<Bean.ResultsBean> results) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                strings = new ArrayList<>();
                for (Bean.ResultsBean result : results) {
                    strings.add(result.getUrl());
                }
                adAp = new AdAp(results, MainActivity.this);
                mRec.setAdapter(adAp);
                getDate();
            }
        });
    }

    private void getDate() {
        adAp.setOnLong(new AdAp.OnLong() {
            @Override
            public void onLong(int potion, View view) {
                a = potion;
                mRec.setVisibility(View.GONE);
                mVp.setVisibility(View.VISIBLE);
                vpadap = new Vpadap(strings, MainActivity.this);
                mVp.setAdapter(vpadap);
                mVp.setCurrentItem(a);
                vpadap.setOnLong(new Vpadap.OnLong() {
                    @Override
                    public void onLong(int potion) {
                        mRec.setVisibility(View.VISIBLE);
                        mVp.setVisibility(View.GONE);
                        mVp.setAdapter(vpadap);
                    }
                });

            }

        });
    }
}
