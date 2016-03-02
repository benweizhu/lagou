package me.zeph.lagou;

import me.zeph.lagou.model.Invoice;
import me.zeph.lagou.model.PurchasedGoods;
import me.zeph.lagou.service.CashRegister;
import me.zeph.lagou.service.CashRegisterInputParser;
import me.zeph.lagou.service.CashRegisterPrinter;
import me.zeph.lagou.service.GoodsRepository;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String[] args) throws Exception {
        CashRegisterPrinter cashRegisterPrinter = new CashRegisterPrinter();
        JSONParser parser = new JSONParser();
        String registerInputFilePath = "/goods.json";
        String repositoryFilePath = "/mapping.json";
        CashRegisterInputParser cashRegisterInputParser = new CashRegisterInputParser(parser, registerInputFilePath, new GoodsRepository(parser, repositoryFilePath));

        CashRegister cashRegister = new CashRegister();
        Collection<PurchasedGoods> values = cashRegisterInputParser.getAllPurchasedGoods().values();

        Invoice invoice = cashRegister.checkout(new ArrayList<>(values));
        cashRegisterPrinter.print(invoice);
    }
}
