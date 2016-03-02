package me.zeph.lagou.model;

public class DiscountPromotion extends Promotion {

    public DiscountPromotion(int priority, double discountPercentage) {
        super(priority);
        this.discountPercentage = discountPercentage;
    }

    private double discountPercentage;

    public double getDiscountPercentage() {
        return discountPercentage;
    }

}
