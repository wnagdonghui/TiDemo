package com.example.ti2demo.util;

import com.example.ti2demo.bean.Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 王董辉    class
 */
public interface MyServer {
    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1

    public String url = "http://gank.io/api/data/";

    @GET("%E7%A6%8F%E5%88%A9/20/{page}")
    Call<Bean> getData(@Path("page") int page);
}
