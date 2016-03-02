package me.zeph.lagou.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvoiceTest {

    private Invoice invoice;
    private static final double DELTA_3 = 0.001;

    @Test
    public void shouldGetTotalPriceAndTotalSavedMoney() throws Exception {
        invoice = new Invoice();
        invoice.addPurchasedGoods(getApplePurchasedGoods());
        invoice.addPurchasedGoods(getBadmintonPurchasedGoods());
        assertEquals(14.45, invoice.getTotalPrice(), DELTA_3);
        assertEquals(1.55, invoice.getSavedMoney(), DELTA_3);
    }

    private PurchasedGoods getApplePurchasedGoods() {
        Goods goods = new Goods("苹果", 5.5d, "斤");
        goods.addPromotion(new DiscountPromotion(2, 0.95));

        PurchasedGoods purchasedGoods = new PurchasedGoods(goods, 2);
        purchasedGoods.setTotalPrice(10.45d);
        return purchasedGoods;
    }

    private PurchasedGoods getBadmintonPurchasedGoods() {
        Goods goods = new Goods("羽毛球", 1d, "个");
        goods.addPromotion(new FreePromotion(1, 1));

        PurchasedGoods purchasedGoods = new PurchasedGoods(goods, 5);
        purchasedGoods.setTotalPrice(4d);
        return purchasedGoods;
    }
}