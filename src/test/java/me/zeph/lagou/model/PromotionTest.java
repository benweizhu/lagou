package me.zeph.lagou.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PromotionTest {
    @Test
    public void shouldGetPriority1WhenContains1and2() throws Exception {
        Promotion promotionWithP1 = new Promotion(1);
        Promotion promotionWithP2 = new Promotion(2);
        Promotion promotionWithP3 = new Promotion(3);
        List<Promotion> promotions = newArrayList(promotionWithP3, promotionWithP2, promotionWithP1);

        assertThat(promotions.get(0).getPriority(), is(3));
        assertThat(promotions.get(1).getPriority(), is(2));
        assertThat(promotions.get(2).getPriority(), is(1));

        Collections.sort(promotions);

        assertThat(promotions.get(0).getPriority(), is(1));
        assertThat(promotions.get(1).getPriority(), is(2));
        assertThat(promotions.get(2).getPriority(), is(3));
    }
}