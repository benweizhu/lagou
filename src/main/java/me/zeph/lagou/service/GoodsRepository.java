package me.zeph.lagou.service;

import me.zeph.lagou.model.Goods;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class GoodsRepository {
    public List<Goods> getAllGoods() {
        return newArrayList(
                new Goods("ITEM000001", "badminton", 1, 1d, true, false, 0),
                new Goods("ITEM000003", "apple", 1, 5.5d, false, true, 0.95),
                new Goods("ITEM000005", "cola", 1, 5.5d, false, true, 0.95)
        );
    }
}
