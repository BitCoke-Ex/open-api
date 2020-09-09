package com.bitcoke.api.examples;

import com.bitcoke.api.MarketRestApi;
import com.bitcoke.api.enums.Indices;
import com.bitcoke.api.enums.Symbol;

import java.util.Arrays;

public class MarketRestApiExample {

    public static void main(String[] args) throws Exception {
        MarketRestApi marketRestApi = new MarketRestApi();
        String result;

        System.out.println("refData");
        result = marketRestApi.refData();
        System.out.println(result);

        System.out.println("lastPrice");
        result = marketRestApi.lastPrice(Arrays.asList(
                Symbol.XBTCUSD.toString(),
                Symbol.XETHUSD.toString(),
                Symbol.XEOSUSD.toString(),
                Symbol.XLTCUSD.toString(),
                Symbol.XBCHUSD.toString()
        ));
        System.out.println(result);

        System.out.println("price");
        result = marketRestApi.price(Arrays.asList(
                Indices.BTCUSD.toString()
        ));
        System.out.println(result);

        System.out.println("depth");
        result = marketRestApi.depth(Symbol.XBTCUSD.toString());
        System.out.println(result);

        System.out.println("trades");
        result = marketRestApi.trades(Symbol.XBTCUSD.toString());
        System.out.println(result);

        System.out.println("quotes");
        result = marketRestApi.quotes(Symbol.XBTCUSD.toString());
        System.out.println(result);

        System.out.println("kLineByIndex");
        result = marketRestApi.kLineByIndex(Symbol.XBTCUSD.toString(),
                "1M", 10, 0);
        System.out.println(result);

        System.out.println("kLineByTime");
        result = marketRestApi.kLineByTime(Symbol.XBTCUSD.toString(),
                "1M", 10, null);
        System.out.println(result);

        System.out.println("kLineLatest");
        result = marketRestApi.kLineLatest(Symbol.XBTCUSD.toString(), "1M");
        System.out.println(result);

        System.out.println("kLineLatestPeriod");
        Long timeSamp = System.currentTimeMillis() - 10 * 60 * 60 * 1000;
        result = marketRestApi.kLineLatestPeriod(Symbol.XBTCUSD.toString(),
                "1M", timeSamp);
        System.out.println(result);

        System.out.println("fundingRate");
        result = marketRestApi.fundingRate(Arrays.asList(
                Symbol.XBTCUSD.toString(),
                Symbol.XETHUSD.toString(),
                Symbol.XEOSUSD.toString()
        ));
        System.out.println(result);

        System.out.println("tradeStatistics");
        result = marketRestApi.tradeStatistics(Arrays.asList(
                Symbol.XBTCUSD.toString(),
                Symbol.XETHUSD.toString(),
                Symbol.XEOSUSD.toString()
        ));
        System.out.println(result);

        System.out.println("openInterest");
        result = marketRestApi.openInterest(Indices.BTCUSD.toString());
        System.out.println(result);
    }
}
