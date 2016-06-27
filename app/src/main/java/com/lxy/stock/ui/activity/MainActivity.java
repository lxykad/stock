package com.lxy.stock.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lxy.stock.R;
import com.lxy.stock.bean.JsonBean;
import com.lxy.stock.common.CommonResponseHandler;
import com.lxy.stock.presenter.StockPresenter;
import com.lxy.stock.ui.activity.adapter.RecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StockPresenter mPresenter;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int REFRESH = 2;
    private static final int REMOVE = 1;
    //默认每5秒刷新一下数据
    private Handler mRefreshHandler = new Handler();

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            //此处刷新数据  根据服务器返回的字段做是否刷新逻辑
            loadData();
            Log.d("refresh data heare ", "run: loadData");

        }
    };

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
    public void loadData() {
        mPresenter.getGroupStockData(new CommonResponseHandler() {
            @Override
            public void onFailure() {
                //数据解析失败
            }

            @Override
            public void onLoadGroupStockSuccess(final ArrayList<JsonBean.MessagesBean> list) {
                //数据解析成功 更新列表
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addItems(list);
                        refresh();
                    }
                });

            }
        });
    }

    //刷新数据 设置5秒刷新一次
    public void refresh() {
        mRefreshHandler.postDelayed(mRunnable, 5000);
    }

    //页面销毁的时候取消刷新
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRefreshHandler.removeCallbacks(mRunnable);
    }
}
