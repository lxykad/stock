package com.lxy.stock.common;

import com.lxy.stock.module.Stock;

import java.util.ArrayList;

/**
 * Created by liuxinyu on 2016/6/21.
 */
public class CommonResponseHandler {
    public void onStart() { }
    public void onFailure() { }
    public void onLoadGroupStockSuccess(ArrayList<Stock> list){}
    public void onLoadSingleStockSuccess(Stock stock){}

}