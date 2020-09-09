package com.bitcoke.api.examples;

import com.bitcoke.api.TradeRestApi;
import com.bitcoke.api.enums.*;

public class TradeRestApiExample {

    private static String apiKey = "your api key";
    private static String apiSecret = "your api secret";


    public static void main(String[] args) throws Exception {
        TradeRestApi tradeRestApi = new TradeRestApi(apiKey, apiSecret);

        String result;

        System.out.println("queryAccount");
        result = tradeRestApi.queryAccount();
        System.out.println(result);

        System.out.println("queryActiveOrders");
        result = tradeRestApi.queryActiveOrders(Symbol.XBTCUSD.toString());
//        result = tradeRestApi.queryActiveOrders(null);
        System.out.println(result);

        System.out.println("queryOrders");
        result = tradeRestApi.queryOrders(null);
//        result = tradeRestApi.queryOrders("O101-20200904-035541-618-1495");
        System.out.println(result);

        System.out.println("queryOrdersPro");
        result = tradeRestApi.queryOrdersPro(null);
//        result = tradeRestApi.queryOrdersPro(System.currentTimeMillis());
        System.out.println(result);

        System.out.println("queryOrderById");
        result = tradeRestApi.queryOrderById("O101-20200904-035541-618-1495");
        System.out.println(result);

        System.out.println("queryPosition");
        result = tradeRestApi.queryPosition();
        System.out.println(result);

        System.out.println("ledger");
        result = tradeRestApi.ledger(Currency.BTC.toString(), "", 1L, 10L);
        System.out.println(result);

//        System.out.println("enterOrder");
////        result = tradeRestApi.enterOrder(Currency.BTC.toString(), Symbol.XBTCUSD.toString(), true,
////                OrderSide.Buy.toString(), 20.0, OrderType.Market.toString(), null);
//        result = tradeRestApi.enterOrder(Currency.BTC.toString(), Symbol.XBTCUSD.toString(), true,
//                OrderSide.Buy.toString(), 20.0, OrderType.Limit.toString(), 9400.0);
//        System.out.println(result);

        System.out.println("amendOrder");
        String orderId = "O101-20200904-081706-418-0885";
        result = tradeRestApi.amendOrder(orderId, 9410.0, null,
                null, null, null, null);
        System.out.println(result);

        System.out.println("cancelOrder");
        result = tradeRestApi.cancelOrder("O101-20200904-081650-026-0590");
        System.out.println(result);

        System.out.println("closePosition");
        result = tradeRestApi.closePosition(Currency.BTC.toString(), Symbol.XBTCUSD.toString(),
                PositionSide.Short.toString());
        System.out.println(result);

        System.out.println("changePosLeverage");
        result = tradeRestApi.changePosLeverage(Currency.EOS.toString(), Symbol.XBTCUSD.toString(), 20.0);
        System.out.println(result);

        System.out.println("riskSetting");
        result = tradeRestApi.riskSetting(Currency.BTC.toString(), Symbol.XBTCUSD.toString(),
                PositionSide.Long.toString(), 0.0001, null, null,
                null, null);
        System.out.println(result);

        System.out.println("switchPosSide");
        result = tradeRestApi.switchPosSide(Currency.EOS.toString(), true);
        System.out.println(result);

        System.out.println("switchToCross");
        result = tradeRestApi.switchToCross(Currency.EOS.toString());
        System.out.println(result);
    }
}
