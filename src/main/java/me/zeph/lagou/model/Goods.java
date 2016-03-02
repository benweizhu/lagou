package me.zeph.lagou.model;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Goods {

    private String id;
    private String name;
    private double price;

    private List<Promotion> promotionList = newArrayList();

    public Goods(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public Promotion getPromotion() {
        if (promotionList.isEmpty()) {
            return null;
        }
        Collections.sort(promotionList);
        return promotionList.get(0);
    }
}
