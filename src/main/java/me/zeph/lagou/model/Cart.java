package me.zeph.lagou.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Cart {

    private List<PurchasedGoods> allGoods = newArrayList();

    public List<PurchasedGoods> getAllGoods() {
        return allGoods;
    }

    public void addGoods(PurchasedGoods goods) {
        allGoods.add(goods);
    }
}
