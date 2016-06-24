package com.lxy.stock.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/6/24.
 */
public class JsonBean {

    public ArrayList<MessagesBean> Messages;

    public static class MessagesBean {
        public String Id;
        public String AuthorId;
        public int Style;
        public String Title;
        public String Summary;
        public String Image;
        public String ImageType;
        public String Url;
        public String Source;
        public String DisplayAuthor;
        public boolean Liked;
        public int LikeCount;
        public int CreatedAt;
        public int UpdatedAt;
        public int Type;
        public String ShareUrl;

        public List<StocksBean> Stocks;

        public static class StocksBean {
            public String Name;
            public String Symbol;
        }
    }
}
