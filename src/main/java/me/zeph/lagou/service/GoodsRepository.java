package me.zeph.lagou.service;

import me.zeph.lagou.model.Goods;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class GoodsRepository {
    public List<Goods> getAllGoods() {
        return newArrayList(
                new Goods("badminton", 1, 1d, true, false),
                new Goods("apple", 1, 5.5d, false, true)
        );
    }
}
