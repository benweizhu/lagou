package me.zeph.lagou.model;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Goods {

    private String name;
    private double price;
    private String unit;

    private List<Promotion> promotionList = newArrayList();

    public Goods(String name, double price, String unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void addPromotion(Promotion promotion) {
        promotionList.add(promotion);
    }

    public Promotion getPromotion() {
        if (promotionList.isEmpty()) {
            return null;
        }
        Collections.sort(promotionList);
        return promotionList.get(0);
    }
}
