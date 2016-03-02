package me.zeph.lagou.service;

import me.zeph.lagou.model.Goods;
import me.zeph.lagou.model.PurchasedGoods;
import org.easymock.EasyMockSupport;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CashRegisterInputParserTest {

    private EasyMockSupport easyMockSupport;
    private GoodsRepository mockedGoodsRepository;
    private CashRegisterInputParser cashRegisterInputParser;
    private Map<String, Goods> goods;

    @Before
    public void setUp() throws Exception {
        easyMockSupport = new EasyMockSupport();
        mockedGoodsRepository = easyMockSupport.createStrictMock(GoodsRepository.class);
        cashRegisterInputParser = new CashRegisterInputParser(new JSONParser(), "/goods.json", mockedGoodsRepository);
        goods = newHashMap();
        initGoods();
    }

    @Test
    public void shouldContains5BadmintonWhenParseTheInput() throws Exception {
        expect(mockedGoodsRepository.getAllGoods()).andReturn(goods).anyTimes();

        easyMockSupport.replayAll();
        Map<String, PurchasedGoods> allPurchasedGoods = cashRegisterInputParser.getAllPurchasedGoods();
        easyMockSupport.verifyAll();

        assertThat(allPurchasedGoods.size(), is(3));
        assertThat(allPurchasedGoods.get("ITEM000001").getCount(), is(5));
    }

    @Test
    public void shouldContains3ColaWhenParseTheInput() throws Exception {
        expect(mockedGoodsRepository.getAllGoods()).andReturn(goods).anyTimes();

        easyMockSupport.replayAll();
        Map<String, PurchasedGoods> allPurchasedGoods = cashRegisterInputParser.getAllPurchasedGoods();
        easyMockSupport.verifyAll();

        assertThat(allPurchasedGoods.size(), is(3));
        assertThat(allPurchasedGoods.get("ITEM000005").getCount(), is(3));
    }

    @Test
    public void shouldContains2AppleWhenParseTheInput() throws Exception {
        expect(mockedGoodsRepository.getAllGoods()).andReturn(goods).anyTimes();

        easyMockSupport.replayAll();
        Map<String, PurchasedGoods> allPurchasedGoods = cashRegisterInputParser.getAllPurchasedGoods();
        easyMockSupport.verifyAll();

        assertThat(allPurchasedGoods.size(), is(3));
        assertThat(allPurchasedGoods.get("ITEM000003").getCount(), is(2));
    }

    private void initGoods() {
        goods.put("ITEM000001", new Goods("羽毛球", 1d, "个"));
        goods.put("ITEM000002", new Goods("苹果", 5.5d, "斤"));
        goods.put("ITEM000005", new Goods("可口可乐", 3d, "瓶"));
        goods.put("ITEM000007", new Goods("ipad", 100d, "个"));
    }


}