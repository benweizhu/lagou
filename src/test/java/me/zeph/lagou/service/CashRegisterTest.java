package me.zeph.lagou.service;

import me.zeph.lagou.model.*;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class CashRegisterTest {

    private static final double DELTA_3 = 0.001;
    private EasyMockSupport easyMockSupport;
    private CashRegister cashRegister;

    @Before
    public void setUp() throws Exception {
        easyMockSupport = new EasyMockSupport();
        cashRegister = new CashRegister();
    }

    @Test
    public void shouldReturnTotalPrice10Point45AndSavedMoney0Point55WhenBuy2KgApple() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("apple", 5.5d, "斤"), 2);
        initPurchasedGoods.getGoods().getPromotionList().add(new DiscountPromotion(2, 0.95));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(newArrayList(initPurchasedGoods));
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(10.45d, purchasedGoods.getTotalPrice(), DELTA_3);
        assertEquals(0.55d, purchasedGoods.getSavedMoney(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice4WhenBuy5Badminton() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("badminton", 1, "个"), 5);
        initPurchasedGoods.getGoods().getPromotionList().add(new FreePromotion(1, 1));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(newArrayList(initPurchasedGoods));
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(4.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice4WhenBuy6Badminton() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("badminton", 1, "个"), 6);
        initPurchasedGoods.getGoods().getPromotionList().add(new FreePromotion(1, 1));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(newArrayList(initPurchasedGoods));
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(4.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice9WhenBuy3Cola() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("cola", 3, "瓶"), 3);

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(newArrayList(initPurchasedGoods));
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(9.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }
}