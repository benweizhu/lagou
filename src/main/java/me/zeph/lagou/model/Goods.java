package me.zeph.lagou.model;

public class Goods {
    private String id;
    private double discountPercentage;
    private String name;
    private int count;
    private double price;
    private boolean isBuyTwoGetOneForFree;
    private boolean isDiscount;

    public Goods(String id, String name, int count, double price, boolean isBuyTwoGetOneForFree, boolean isDiscount, double discountPercentage) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.isBuyTwoGetOneForFree = isBuyTwoGetOneForFree;
        this.isDiscount = isDiscount;
        this.discountPercentage = discountPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBuyTwoGetOneForFree() {
        return isBuyTwoGetOneForFree;
    }

    public void setBuyTwoGetOneForFree(boolean buyTwoGetOneForFree) {
        isBuyTwoGetOneForFree = buyTwoGetOneForFree;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
