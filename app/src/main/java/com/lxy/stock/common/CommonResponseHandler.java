package com.lxy.stock.common;

import com.lxy.stock.bean.JsonBean;
import com.lxy.stock.bean.Stock;

import java.util.ArrayList;

/**
 * Created by lxy
 */
public class CommonResponseHandler {
    public void onStart() { }
    public void onFailure() { }
    public void onLoadGroupStockSuccess(ArrayList<JsonBean.MessagesBean> list){}
    public void onLoadSingleStockSuccess(Stock stock){}

}
