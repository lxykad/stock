package com.lxy.stock.common;

import com.lxy.stock.bean.JsonBean;

import java.util.ArrayList;

/**
 * Created by liuxinyu on 2016/6/21.
 */
public class CommonResponseHandler {
    public void onStart() { }
    public void onFailure() { }
    public void onLoadGroupStockSuccess(ArrayList<JsonBean.MessagesBean> list){}
    //public void onLoadSingleStockSuccess(Stock stock){}

}
