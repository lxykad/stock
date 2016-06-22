package com.lxy.stock.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.lxy.stock.common.CommonResponseHandler;
import com.lxy.stock.module.Stock;
import com.lxy.stock.utils.HSJsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by liuxinyu on 2016/6/21.
 */
public class StockPresenter {

    private static final int GROUP_SUCCESS = 1;
    private Context mContext;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch (msg.what) {
                case GROUP_SUCCESS:
                    String jsonData = (String) msg.obj;
                    ArrayList<Stock> stockList = HSJsonUtil.getRealStockList(jsonData, "Messages");
                    System.out.println("11111111111==========list===="+stockList.size());

//                    if (stockList.size() == 0) {
//                        //数据解析失败
//                        handler.onFailure();
//                    } else {
//                        //数据解析成功
//                        handler.onLoadGroupStockSuccess(stockList);
//                    }

                    break;
            }
        }
    };

    public StockPresenter(Context context) {
        mContext = context;
    }

    //Thread

    //获取组合股票的数据
    public void getGroupStockData(CommonResponseHandler handler) {

        //读取json数据，放在子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonData = loadJsonDataFromAssets("data.json");
                Message msg = mHandler.obtainMessage();
                msg.what = GROUP_SUCCESS;
                msg.obj = jsonData;
                mHandler.sendMessage(msg);

            }
        }).start();





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

    //解析本地json数据
    protected String loadJsonDataFromAssets(String fileName) {

        StringBuffer buffer = new StringBuffer();
        try {
            InputStreamReader inputReader = new InputStreamReader(mContext.getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            //
            e.printStackTrace();
        }
        return buffer.toString();
    }


}
