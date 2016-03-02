package me.zeph.lagou.service;

import me.zeph.lagou.model.*;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

public class CashRegisterTest {

    private static final double DELTA_3 = 0.001;
    private EasyMockSupport easyMockSupport;
    private Cart mockedCart;
    private CashRegister cashRegister;

    @Before
    public void setUp() throws Exception {
        easyMockSupport = new EasyMockSupport();
        mockedCart = easyMockSupport.createStrictMock(Cart.class);
        cashRegister = new CashRegister();
    }

    @Test
    public void shouldReturnTotalPrice10Point45AndSavedMoney0Point55WhenBuy2KgApple() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("ITEM000003", "apple", 5.5d), 2);
        initPurchasedGoods.getGoods().getPromotionList().add(new DiscountPromotion(2, 0.95));
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(initPurchasedGoods));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(10.45d, purchasedGoods.getTotalPrice(), DELTA_3);
        assertEquals(0.55d, purchasedGoods.getSavedMoney(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice4WhenBuy5Badminton() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("ITEM000001", "badminton", 1), 5);
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(initPurchasedGoods));
        initPurchasedGoods.getGoods().getPromotionList().add(new FreePromotion(1, 1));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(4.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice4WhenBuy6Badminton() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("ITEM000001", "badminton", 1), 6);
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(initPurchasedGoods));
        initPurchasedGoods.getGoods().getPromotionList().add(new FreePromotion(1, 1));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(4.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice9WhenBuy3Cola() throws Exception {
        PurchasedGoods initPurchasedGoods = new PurchasedGoods(new Goods("ITEM000005", "cola", 3), 3);
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(initPurchasedGoods));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(9.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }
}