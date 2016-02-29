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
            if (goods.isBuyTwoGetOneForFree()) {
                invoice.addPurchasedGoods(covertButTwoGetOneForFreeGoods(goods));
            }
            if (goods.isDiscount()) {
                invoice.addPurchasedGoods(covertDiscountGoods(goods));
            }
        }
        return invoice;
    }

    private PurchasedGoods covertButTwoGetOneForFreeGoods(Goods goods) {
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
}
