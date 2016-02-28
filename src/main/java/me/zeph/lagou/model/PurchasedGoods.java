package me.zeph.lagou.model;

public class PurchasedGoods extends Goods {

    private double totalPrice;
    private double savedMoney;

    public PurchasedGoods(String name, int count, double price, boolean isBuyTwoGetOneForFree, boolean isDiscount) {
        super(name, count, price, isBuyTwoGetOneForFree, isDiscount);
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
