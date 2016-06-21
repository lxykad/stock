package com.lxy.stock.presenter;

import com.lxy.stock.common.CommonResponseHandler;
import com.lxy.stock.module.Stock;
import com.lxy.stock.utils.HSJsonUtil;

import java.util.ArrayList;

/**
 * Created by liuxinyu on 2016/6/21.
 */
public class StockPresenter {

    //Thread
    //获取组合股票的数据
    public void getGroupStockData(CommonResponseHandler handler) {
        ArrayList<Stock> stockList = HSJsonUtil.getRealStockList("", "data.json");
        if (stockList.size() == 0) {
            //数据解析失败
            handler.onFailure();
        } else {
            //数据解析成功
            handler.onLoadGroupStockSuccess(stockList);
        }
    }

    //获取单个股票的数据
    public void getSingleStockData(CommonResponseHandler handler) {
        Stock stock = HSJsonUtil.getRealInfo("", "data.json");
        if (stock.id == null) {
            //数据解析失败
            handler.onFailure();
        } else {
            //数据解析成功
            handler.onLoadSingleStockSuccess(stock);
        }
    }
}
