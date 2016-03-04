package me.zeph.lagou.service;

import me.zeph.lagou.model.*;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;

public class CashRegisterPrinter {

    private static final String NONE_DISCOUNT_MESSAGE_FORMAT = "名称：%s，数量：%d%s，单价：%.2f(元)，小计：%.2f(元)\n";
    private static final String DISCOUNT_MESSAGE_FORMAT = "名称：%s，数量：%d%s，单价：%.2f(元)，小计：%.2f(元)，节省%.2f(元)\n";
    private static final String FREE_ITEM_MESSAGE_FORMAT = "名称：%s，数量：%d%s\n";
    private static final String TOTAL_PRICE_MESSAGE_FORMAT = "总计：%.2f(元)\n";
    private static final String TOTAL_SAVED_MONEY_MESSAGE_FORMAT = "节省：%.2f(元)\n";

    public void print(Invoice invoice) {
        System.out.println(getInvoiceResult(invoice));
    }

    private String getInvoiceResult(Invoice invoice) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getInvoicePurchaseGoodsList(invoice));
        stringBuffer.append(getInvoiceFreeGoodsList(invoice));
        stringBuffer.append(getInvoiceTotalPrice(invoice));

        return stringBuffer.toString();
    }

    private String getInvoicePurchaseGoodsList(Invoice invoice) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("***<没钱赚商店>购物清单***\n");
        List<PurchasedGoods> allPurchasedGoods = invoice.getAllPurchasedGoods();
        for (PurchasedGoods purchasedGood : allPurchasedGoods) {
            String name = purchasedGood.getGoods().getName();
            int count = purchasedGood.getCount();
            String unit = purchasedGood.getGoods().getUnit();
            double price = purchasedGood.getGoods().getPrice();
            double totalPrice = purchasedGood.getTotalPrice();
            double savedMoney = purchasedGood.getSavedMoney();
            Promotion promotion = purchasedGood.getGoods().getPromotion();
            stringBuffer.append(getFormatPurchaseGoodsListMessage(name, count, unit, price, totalPrice, savedMoney, promotion));
        }
        stringBuffer.append("----------------------\n");
        return stringBuffer.toString();
    }

    private String getFormatPurchaseGoodsListMessage(String name, int count, String unit, double price,
                                                     double totalPrice, double savedMoney, Promotion promotion) {
        return promotion instanceof DiscountPromotion ?
                format(DISCOUNT_MESSAGE_FORMAT, name, count, unit, price, totalPrice, savedMoney) :
                format(NONE_DISCOUNT_MESSAGE_FORMAT, name, count, unit, price, totalPrice);
    }

    private String getInvoiceFreeGoodsList(Invoice invoice) {
        StringBuffer stringBuffer = new StringBuffer();
        List<PurchasedGoods> allPurchasedGoods = invoice.getAllPurchasedGoods();
        for (PurchasedGoods purchasedGood : allPurchasedGoods) {
            if (purchasedGood.getGoods().getPromotion() instanceof FreePromotion) {
                String name = purchasedGood.getGoods().getName();
                String unit = purchasedGood.getGoods().getUnit();
                int giftCount = purchasedGood.getGiftCount();
                stringBuffer.append(format(FREE_ITEM_MESSAGE_FORMAT, name, giftCount, unit));
            }
        }
        if (!isNullOrEmpty(stringBuffer.toString())) {
            stringBuffer.insert(0, "买二赠一商品：\n");
            stringBuffer.append("----------------------\n");
        }
        return stringBuffer.toString();
    }

    private String getInvoiceTotalPrice(Invoice invoice) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(format(TOTAL_PRICE_MESSAGE_FORMAT, invoice.getTotalPrice()));
        if (invoice.getSavedMoney() > 0) {
            stringBuffer.append(format(TOTAL_SAVED_MONEY_MESSAGE_FORMAT, invoice.getSavedMoney()));
        }
        stringBuffer.append("**********************\n");
        return stringBuffer.toString();
    }
}
