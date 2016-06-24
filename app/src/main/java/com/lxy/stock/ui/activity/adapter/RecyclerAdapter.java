package com.lxy.stock.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.stock.R;
import com.lxy.stock.bean.JsonBean;

import java.util.ArrayList;

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

        holder.textView.setText(bean.Title);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
