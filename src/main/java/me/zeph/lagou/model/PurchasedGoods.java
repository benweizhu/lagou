package me.zeph.lagou.model;

public class PurchasedGoods {

    private Goods goods;
    private int count;
    private int giftCount;
    private double totalPrice;

    public PurchasedGoods(Goods goods, int count) {
        this.goods = goods;
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getSavedMoney() {
        return count * goods.getPrice() - getTotalPrice();
    }

    public int getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(int giftCount) {
        this.giftCount = giftCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount(int addedCount){
        this.count = this.count + addedCount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
