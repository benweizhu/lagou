package me.zeph.lagou.service;

import me.zeph.lagou.model.PurchasedGoods;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Integer.valueOf;

public class CashRegisterInputParser {

    private static final int COUNT = 1;
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SPLIT_SYMBOL = "-";

    private JSONParser parser;
    private String filePath;

    private GoodsRepository goodsRepository;

    public CashRegisterInputParser(JSONParser parser, String filePath, GoodsRepository goodsRepository) {
        this.parser = parser;
        this.filePath = filePath;
        this.goodsRepository = goodsRepository;
    }

    public Map<String, PurchasedGoods> getAllPurchasedGoods() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream(filePath));
        Iterator iterator = ((JSONArray) parser.parse(inputStreamReader)).iterator();
        HashMap<String, PurchasedGoods> purchasedGoodsMap = newHashMap();
        while (iterator.hasNext()) {
            String[] keyAndCount = ((String) iterator.next()).split(SPLIT_SYMBOL);
            initPurchasedGoods(purchasedGoodsMap, keyAndCount[KEY_INDEX], getAddedCount(keyAndCount));
        }
        return purchasedGoodsMap;
    }

    private int getAddedCount(String[] split) {
        return split.length > 1 ? valueOf(split[VALUE_INDEX]) : COUNT;
    }

    private void initPurchasedGoods(HashMap<String, PurchasedGoods> purchasedGoodsMap, String key, int addedCount) throws Exception {
        if (purchasedGoodsMap.containsKey(key)) {
            purchasedGoodsMap.get(key).addCount(addedCount);
        } else {
            purchasedGoodsMap.put(key, new PurchasedGoods(goodsRepository.getAllGoods().get(key), addedCount));
        }
    }


}
