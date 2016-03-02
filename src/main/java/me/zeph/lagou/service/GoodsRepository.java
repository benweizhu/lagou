package me.zeph.lagou.service;

import me.zeph.lagou.model.DiscountPromotion;
import me.zeph.lagou.model.FreePromotion;
import me.zeph.lagou.model.Goods;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Maps.newHashMap;

public class GoodsRepository {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String UNIT = "unit";
    private static final String DISCOUNT = "discount";
    private static final String VALUE = "value";
    private static final String FREE_COUNT = "freeCount";

    private JSONParser parser;
    private String filePath;

    public GoodsRepository(JSONParser parser, String filePath) {
        this.parser = parser;
        this.filePath = filePath;
    }

    public Map<String, Goods> getAllGoods() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream(filePath));
        Iterator iterator = ((JSONArray) parser.parse(inputStreamReader)).iterator();
        Map<String, Goods> goodsMap = newHashMap();
        while (iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            Goods goods = initGoods(jsonObject);
            setFreePromotionIfHas(jsonObject, goods);
            setDiscountPromotionIfHas(jsonObject, goods);
            goodsMap.put((String) jsonObject.get(ID), goods);
        }
        return goodsMap;
    }

    public Goods initGoods(JSONObject jsonObject) {
        String name = (String) jsonObject.get(NAME);
        double price = Double.valueOf((String) jsonObject.get(PRICE));
        String unit = (String) jsonObject.get(UNIT);
        return new Goods(name, price, unit);
    }

    private void setDiscountPromotionIfHas(JSONObject jsonObject, Goods goods) {
        JSONObject discount = (JSONObject) jsonObject.get(DISCOUNT);
        if (!isNullOrEmpty((String) discount.get(VALUE))) {
            DiscountPromotion promotion = new DiscountPromotion(Integer.valueOf((String) discount.get("priority")),
                    Double.valueOf((String) discount.get(VALUE)));
            goods.addPromotion(promotion);
        }
    }

    private void setFreePromotionIfHas(JSONObject jsonObject, Goods goods) {
        JSONObject freeCount = (JSONObject) jsonObject.get(FREE_COUNT);
        if (!isNullOrEmpty((String) freeCount.get(VALUE))) {
            FreePromotion promotion = new FreePromotion(Integer.valueOf((String) freeCount.get("priority")),
                    Integer.valueOf((String) freeCount.get(VALUE)));
            goods.addPromotion(promotion);
        }
    }


}
