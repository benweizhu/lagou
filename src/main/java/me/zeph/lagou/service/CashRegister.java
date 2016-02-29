package me.zeph.lagou.service;

import me.zeph.lagou.model.Cart;
import me.zeph.lagou.model.Goods;
import me.zeph.lagou.model.Invoice;
import me.zeph.lagou.model.PurchasedGoods;

import java.util.List;

public class CashRegister {

    public Invoice checkout(Cart cart) {
        List<Goods> allGoods = cart.getAllGoods();
        Invoice invoice = new Invoice();
        for (Goods goods : allGoods) {
            PurchasedGoods purchasedGoods = covertNormalGoods(goods);
            if (goods.isBuyTwoGetOneForFree()) {
                purchasedGoods = covertBuyTwoGetOneForFreeGoods(goods);
            }
            if (goods.isDiscount()) {
                purchasedGoods = covertDiscountGoods(goods);
            }
            invoice.addPurchasedGoods(purchasedGoods);
        }
        return invoice;
    }

    private PurchasedGoods covertBuyTwoGetOneForFreeGoods(Goods goods) {
        PurchasedGoods purchasedGoods = (PurchasedGoods) goods;
        purchasedGoods.setGiftCount(goods.getCount() / 3);
        purchasedGoods.setTotalPrice(purchasedGoods.getPrice() * ((purchasedGoods.getGiftCount() * 2) + (purchasedGoods.getCount() % 3)));
        return purchasedGoods;
    }

    private PurchasedGoods covertDiscountGoods(Goods goods) {
        PurchasedGoods purchasedGoods = (PurchasedGoods) goods;
        purchasedGoods.setTotalPrice(goods.getPrice() * goods.getCount() * goods.getDiscountPercentage());
        return purchasedGoods;
    }

    private PurchasedGoods covertNormalGoods(Goods goods) {
        PurchasedGoods purchasedGoods = (PurchasedGoods) goods;
        purchasedGoods.setTotalPrice(goods.getPrice() * goods.getCount());
        return purchasedGoods;
    }
}
