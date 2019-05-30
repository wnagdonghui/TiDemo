package com.example.ti2demo.contract;
/*
 *         王董辉
 *                             class
 */

import com.example.ti2demo.bean.Bean;

import java.util.List;

public interface Contract {
    interface Model {
        void getModel(CallBack callBack);
    }

    interface View {
        void getIfView(List<Bean.ResultsBean> results);
    }

    interface Presenter {
        void getPresenter();
    }
}
