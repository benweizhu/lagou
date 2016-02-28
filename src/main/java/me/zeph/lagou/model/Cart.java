package me.zeph.lagou.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Cart {

    private List<Goods> allGoods = newArrayList();

    public List<Goods> getAllGoods() {
        return allGoods;
    }

    public void addGoods(Goods goods){
        allGoods.add(goods);
    }
}
