package me.zeph.lagou.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Invoice {

    private List<PurchasedGoods> allPurchasedGoods = newArrayList();
    private double totalPrice;
    private double savedMoney;

}
