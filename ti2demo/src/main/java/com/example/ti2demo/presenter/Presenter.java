package com.example.ti2demo.presenter;

import com.example.ti2demo.bean.Bean;
import com.example.ti2demo.contract.CallBack;
import com.example.ti2demo.contract.Contract;
import com.example.ti2demo.model.Model;

import java.util.List;

/**
 * 王董辉    class
 */
public class Presenter implements Contract.Presenter {
    Contract.View view;
    private final Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void getPresenter() {
        model.getModel(new CallBack() {
            @Override
            public void getCallBack(List<Bean.ResultsBean> results) {
                view.getIfView(results);
            }
        });
    }
}
