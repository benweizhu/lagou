package me.zeph.lagou.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Invoice {

    private List<PurchasedGoods> allPurchasedGoods = newArrayList();

    public List<PurchasedGoods> getAllPurchasedGoods() {
        return allPurchasedGoods;
    }

    public void addPurchasedGoods(PurchasedGoods purchasedGoods) {
        allPurchasedGoods.add(purchasedGoods);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (PurchasedGoods purchasedGood : allPurchasedGoods) {
            totalPrice += purchasedGood.getTotalPrice();
        }
        return totalPrice;
    }

    public double getSavedMoney() {
        double savedMoney = 0;
        for (PurchasedGoods purchasedGood : allPurchasedGoods) {
            savedMoney += purchasedGood.getSavedMoney();
        }
        return savedMoney;
    }

}
