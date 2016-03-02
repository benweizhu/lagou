package me.zeph.lagou.service;

import me.zeph.lagou.model.DiscountPromotion;
import me.zeph.lagou.model.FreePromotion;
import me.zeph.lagou.model.Invoice;
import me.zeph.lagou.model.PurchasedGoods;

import java.util.List;

import static java.lang.String.format;

public class CashRegisterPrinter {

    private static final String NONE_DISCOUNT_MESSAGE_FORMAT = "名称：%s，数量：%d%s，单价：%f(%s)，小计：%f(%s)\n";
    private static final String DISCOUNT_MESSAGE_FORMAT = "名称：%s，数量：%d%s，单价：%f(%s)，小计：%f(%s)，节省%f(%s)\n";
    private static final String FREE_ITEM_MESSAGE_FORMAT = "名称：%s，数量：%d%s\n";

    public String getInvoiceResult(Invoice invoice) {
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
            String item = format(NONE_DISCOUNT_MESSAGE_FORMAT, name, count, unit, price, unit, totalPrice, unit);
            if (purchasedGood.getGoods().getPromotion() instanceof DiscountPromotion) {
                item = format(DISCOUNT_MESSAGE_FORMAT, name, count, unit, price, unit, totalPrice, unit);
            }
            stringBuffer.append(item);
        }
        return stringBuffer.toString();
    }

    private String getInvoiceFreeGoodsList(Invoice invoice) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("买二赠一商品：\n");
        List<PurchasedGoods> allPurchasedGoods = invoice.getAllPurchasedGoods();
        for (PurchasedGoods purchasedGood : allPurchasedGoods) {
            if (purchasedGood.getGoods().getPromotion() instanceof FreePromotion) {
                String name = purchasedGood.getGoods().getName();
                String unit = purchasedGood.getGoods().getUnit();
                int giftCount = purchasedGood.getGiftCount();
                stringBuffer.append(format(FREE_ITEM_MESSAGE_FORMAT, name, giftCount, unit));
            }
        }
        return stringBuffer.toString();
    }

    private String getInvoiceTotalPrice(Invoice invoice) {
        return null;
    }
}
