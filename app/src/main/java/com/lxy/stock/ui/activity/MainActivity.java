package com.lxy.stock.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxy.stock.R;
import com.lxy.stock.common.CommonResponseHandler;
import com.lxy.stock.bean.Message;
import com.lxy.stock.bean.Stock;
import com.lxy.stock.presenter.StockPresenter;
import com.lxy.stock.ui.activity.adapter.RecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StockPresenter mPresenter;
    private ArrayList<Stock> mStockList;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mPresenter = new StockPresenter(this);
        mAdapter = new RecyclerAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //获取数据
    public void loadData(){
        mPresenter.getGroupStockData(new CommonResponseHandler(){
            @Override
            public void onFailure() {

            }

            @Override
            public void onLoadGroupStockSuccess(final ArrayList<Message> list) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addItems(list);
                    }
                });

            }
        });
    }

}
