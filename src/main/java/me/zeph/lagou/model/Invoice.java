package me.zeph.lagou.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Invoice {

    private List<PurchasedGoods> allPurchasedGoods = newArrayList();
    private double totalPrice;
    private double savedMoney;

    public List<PurchasedGoods> getAllPurchasedGoods() {
        return allPurchasedGoods;
    }

    public void addPurchasedGoods(PurchasedGoods purchasedGoods){
        allPurchasedGoods.add(purchasedGoods);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(double savedMoney) {
        this.savedMoney = savedMoney;
    }
}
