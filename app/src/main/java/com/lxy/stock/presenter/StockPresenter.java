package com.lxy.stock.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.lxy.stock.bean.JsonBean;
import com.lxy.stock.bean.Stock;
import com.lxy.stock.common.CommonResponseHandler;
import com.lxy.stock.common.HttpHelper;
import com.lxy.stock.utils.HSJsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by lxy
 */
public class StockPresenter {

    private Context mContext;

    public StockPresenter(Context context) {
        mContext = context;
    }

    //获取组合股票的数据    耗时操作 放在子线程
    public void getGroupStockData(final CommonResponseHandler handler) {

        //读取json数据，放在子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonData = loadJsonDataFromAssets("data.json");
                Gson gson = new Gson();
                JsonBean bean = gson.fromJson(jsonData, JsonBean.class);
                ArrayList<JsonBean.MessagesBean> list = bean.Messages;

                if (list.size() == 0) {
                    //数据解析失败 回调给activity
                    handler.onFailure();

                } else {
                    //数据解析成功  回调给activity
                    handler.onLoadGroupStockSuccess(list);
                }

            }
        }).start();

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

    public static void getSingleStock(String prod_name, final CommonResponseHandler handler) {

        String url = HttpHelper.BASEURL + prod_name + "&fields=" + HttpHelper.FIELDS;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .connTimeOut(10000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        handler.onFailure();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Stock stock = HSJsonUtil.getRealInfo(response, "snapshot");
                        handler.onLoadSingleStockSuccess(stock);
                    }
                });

    }

}
