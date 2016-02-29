package me.zeph.lagou.service;

import me.zeph.lagou.model.Cart;
import me.zeph.lagou.model.Invoice;
import me.zeph.lagou.model.PurchasedGoods;
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
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(new PurchasedGoods("apple", 2, 5.5d, false, true, 0.95)));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(10.45d, purchasedGoods.getTotalPrice(), DELTA_3);
        assertEquals(0.55d, purchasedGoods.getSavedMoney(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice4WhenBuy5Badminton() throws Exception {
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(new PurchasedGoods("badminton", 5, 1, true, false, 0)));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(4.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice4WhenBuy6Badminton() throws Exception {
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(new PurchasedGoods("badminton", 6, 1, true, false, 0)));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(4.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }

    @Test
    public void shouldReturnTotalPrice9WhenBuy3Cola() throws Exception {
        expect(mockedCart.getAllGoods()).andReturn(newArrayList(new PurchasedGoods("cola", 3, 3, false, false, 0)));

        easyMockSupport.replayAll();
        Invoice invoice = cashRegister.checkout(mockedCart);
        easyMockSupport.verifyAll();

        PurchasedGoods purchasedGoods = invoice.getAllPurchasedGoods().get(0);

        assertEquals(9.0d, purchasedGoods.getTotalPrice(), DELTA_3);
    }
}