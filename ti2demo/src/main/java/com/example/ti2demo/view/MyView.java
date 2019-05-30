package com.example.ti2demo.view;

import com.example.ti2demo.bean.Bean; /**
 * 王董辉    class
 */
public interface MyView {
    void onSuccess(Bean body);

    void onFail(String error);

}
