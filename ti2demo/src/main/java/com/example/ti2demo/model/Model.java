package com.example.ti2demo.model;

import android.util.Log;

import com.example.ti2demo.bean.Bean;
import com.example.ti2demo.contract.CallBack;
import com.example.ti2demo.contract.Contract;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 王董辉    class
 */
public class Model implements Contract.Model {
    @Override
    public void getModel(final CallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("tag", "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                List<Bean.ResultsBean> results = bean.getResults();
                callBack.getCallBack(results);
            }
        });
    }
}
