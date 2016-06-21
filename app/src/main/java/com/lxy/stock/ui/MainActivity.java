package com.lxy.stock.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.lxy.stock.R;
import com.lxy.stock.common.CommonResponseHandler;
import com.lxy.stock.module.Stock;
import com.lxy.stock.presenter.StockPresenter;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StockPresenter mPresenter;
    private ArrayList<Stock> mStockList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mPresenter = new StockPresenter();
    }

    //获取stock的集合
    public void loadData(){
        mPresenter.getGroupStockData(new CommonResponseHandler(){
            @Override
            public void onFailure() {

            }

            @Override
            public void onLoadGroupStockSuccess(ArrayList<Stock> list) {

            }
        });
    }
}