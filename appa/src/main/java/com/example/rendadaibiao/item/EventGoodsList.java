package com.example.rendadaibiao.item;

import com.example.rendadaibiao.item.bean.Shop;

import java.util.List;

/**
 * Created by SHfeihu on 2018/3/14
 */

public class EventGoodsList {
    List<Shop.TuijianBean.ListBean> list;

    public EventGoodsList(List<Shop.TuijianBean.ListBean> list) {
        this.list = list;
    }

    public List<Shop.TuijianBean.ListBean> getList() {
        return list;
    }

    public void setList(List<Shop.TuijianBean.ListBean> list) {
        this.list = list;
    }
}
