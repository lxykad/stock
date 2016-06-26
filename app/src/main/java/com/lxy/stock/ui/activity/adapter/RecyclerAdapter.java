package com.lxy.stock.ui.activity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxy.stock.R;
import com.lxy.stock.bean.JsonBean;
import com.lxy.stock.bean.Stock;
import com.lxy.stock.common.CommonResponseHandler;
import com.lxy.stock.presenter.StockPresenter;
import com.lxy.stock.utils.Tools;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lxy
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private Context mContext;
    ArrayList<JsonBean.MessagesBean> mList = new ArrayList<>();

    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    public void addItems(ArrayList<JsonBean.MessagesBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        JsonBean.MessagesBean bean = mList.get(position);

        holder.title.setText(bean.Title);
        holder.summary.setText(bean.Summary);
        holder.likeCount.setText(bean.LikeCount + "");
        holder.source.setText("来自 " + bean.Source);
        holder.time.setText(Tools.formartDate(new Date(), new Date(bean.UpdatedAt * 1000)));

        final Bitmap upImage = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_stock_up);
        final Bitmap downImage = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_stock_down);


        List<JsonBean.MessagesBean.StocksBean> stocks = bean.Stocks;
        for (int i = 0; i < stocks.size(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_stock, null);
            final TextView name = (TextView) view.findViewById(R.id.tv_name);
            final TextView change = (TextView) view.findViewById(R.id.tv_px_change);
            final ImageView img = (ImageView) view.findViewById(R.id.img);


            name.setText(stocks.get(i).Name);
            StockPresenter.getSingleStock(stocks.get(i).Symbol, new CommonResponseHandler() {
                @Override
                public void onFailure() {

                }

                @Override
                public void onLoadSingleStockSuccess(Stock stock) {
                    String px_change_rate = stock.px_change_rate;
                    String formateChange = Tools.formateChange(px_change_rate);
                    float number = Float.valueOf(formateChange);
                    if (number > 0) {
                        //涨
                        change.setTextColor(Color.parseColor("#DE3B37"));
                        name.setTextColor(Color.parseColor("#DE3B37"));
                        img.setImageBitmap(upImage);

                    } else {
                        //跌
                        change.setTextColor(Color.parseColor("#3AA63F"));
                        name.setTextColor(Color.parseColor("#3AA63F"));
                        img.setImageBitmap(downImage);

                    }
                    change.setText(formateChange + "%");
                }
            });


            holder.scrollLayout.addView(view);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView summary;
        TextView likeCount;
        TextView time;
        TextView source;
        HorizontalScrollView scrollView;
        LinearLayout scrollLayout;


        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            summary = (TextView) itemView.findViewById(R.id.tv_summary);
            likeCount = (TextView) itemView.findViewById(R.id.tv_like_count);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            source = (TextView) itemView.findViewById(R.id.tv_source);

            scrollView = (HorizontalScrollView) itemView.findViewById(R.id.scroll_view_layout);
            scrollLayout = (LinearLayout) itemView.findViewById(R.id.scroll_linear_layout);

        }
    }

}
