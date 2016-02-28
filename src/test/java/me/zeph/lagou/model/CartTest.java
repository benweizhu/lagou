package me.zeph.lagou.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CartTest {

    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void shouldContainsGoodsWhenAddToCart() throws Exception {
        cart.addGoods(new Goods());
        assertThat(cart.getAllGoods().isEmpty(), is(false));
    }
}