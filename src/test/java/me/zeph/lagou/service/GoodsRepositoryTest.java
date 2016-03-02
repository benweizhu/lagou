package me.zeph.lagou.service;

import me.zeph.lagou.model.DiscountPromotion;
import me.zeph.lagou.model.FreePromotion;
import me.zeph.lagou.model.Goods;
import me.zeph.lagou.model.Promotion;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GoodsRepositoryTest {

    private GoodsRepository goodsRepository;
    private Map<String, Goods> allGoods;

    @Before
    public void setUp() throws Exception {
        goodsRepository = new GoodsRepository(new JSONParser(), "/mapping.json");
        allGoods = goodsRepository.getAllGoods();
    }

    @Test
    public void shouldHave3Goods() throws Exception {
        assertThat(allGoods.size(), is(4));
    }

    @Test
    public void shouldFreePromotionForBadminton() throws Exception {
        Promotion promotion = allGoods.get("ITEM000001").getPromotion();
        assertTrue(promotion instanceof FreePromotion);
        assertThat(((FreePromotion) promotion).getFreeCount(), is(1));
    }

    @Test
    public void shouldDiscountPromotionForApple() throws Exception {
        Promotion promotion = allGoods.get("ITEM000002").getPromotion();
        assertTrue(promotion instanceof DiscountPromotion);
        assertThat(((DiscountPromotion) promotion).getDiscountPercentage(), is(0.95d));
    }

    @Test
    public void shouldHaveDiscountPromotionForApple() throws Exception {
        Promotion promotion = allGoods.get("ITEM000007").getPromotion();
        assertTrue(promotion instanceof FreePromotion);
        assertThat(((FreePromotion) promotion).getFreeCount(), is(1));
    }
}