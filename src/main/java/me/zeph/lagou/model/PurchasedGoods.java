package me.zeph.lagou.model;

public class PurchasedGoods extends Goods {

    private int giftCount;
    private double totalPrice;

    public PurchasedGoods(String name, int count, double price, boolean isBuyTwoGetOneForFree, boolean isDiscount, double discountPercentage) {
        super(name, count, price, isBuyTwoGetOneForFree, isDiscount, discountPercentage);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getSavedMoney() {
        return getCount() * getPrice() - getTotalPrice();
    }

    public int getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(int giftCount) {
        this.giftCount = giftCount;
    }
}
