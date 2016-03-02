package me.zeph.lagou.service;

import me.zeph.lagou.model.DiscountPromotion;
import me.zeph.lagou.model.FreePromotion;
import me.zeph.lagou.model.Invoice;
import me.zeph.lagou.model.PurchasedGoods;

import java.util.List;

public class CashRegister {

    private static final int GOODS_NEED_TO_PAY_FACTOR = 2;

    public Invoice checkout(List<PurchasedGoods> allGoods) {
        Invoice invoice = new Invoice();
        for (PurchasedGoods goods : allGoods) {
            PurchasedGoods purchasedGoods = covertNormalGoods(goods);
            if (purchasedGoods.getGoods().getPromotion() instanceof FreePromotion) {
                purchasedGoods = covertBuyTwoGetOneForFreeGoods(goods);
            }
            if (purchasedGoods.getGoods().getPromotion() instanceof DiscountPromotion) {
                purchasedGoods = covertDiscountGoods(goods);
            }
            invoice.addPurchasedGoods(purchasedGoods);
        }
        return invoice;
    }

    private PurchasedGoods covertBuyTwoGetOneForFreeGoods(PurchasedGoods purchasedGoods) {
        FreePromotion promotion = (FreePromotion) purchasedGoods.getGoods().getPromotion();
        int freeGoodFactor = promotion.getFreeCount() + GOODS_NEED_TO_PAY_FACTOR;
        purchasedGoods.setGiftCount(purchasedGoods.getCount() / freeGoodFactor);
        purchasedGoods.setTotalPrice(purchasedGoods.getGoods().getPrice() *
                ((purchasedGoods.getGiftCount() * GOODS_NEED_TO_PAY_FACTOR) + (purchasedGoods.getCount() % freeGoodFactor)));
        return purchasedGoods;
    }

    private PurchasedGoods covertDiscountGoods(PurchasedGoods purchasedGoods) {
        DiscountPromotion promotion = (DiscountPromotion) purchasedGoods.getGoods().getPromotion();
        purchasedGoods.setTotalPrice(purchasedGoods.getGoods().getPrice() * purchasedGoods.getCount() * promotion.getDiscountPercentage());
        return purchasedGoods;
    }

    private PurchasedGoods covertNormalGoods(PurchasedGoods purchasedGoods) {
        purchasedGoods.setTotalPrice(purchasedGoods.getGoods().getPrice() * purchasedGoods.getCount());
        return purchasedGoods;
    }
}
