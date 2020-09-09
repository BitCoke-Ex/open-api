package com.bitcoke.api;

import com.sun.deploy.util.StringUtils;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.List;

public class MarketRestApi {

    private final String BASE_URL = "http://api.bitcoke.com";

    private final String REF_DATA = "/api/basic/refData";
    private final String LAST_PRICE = "/api/basic/lastPrice";
    private final String PRICE = "/api/index/price";
    private final String DEPTH = "/api/depth/depth";
    private final String TRADES = "/api/depth/trades";
    private final String QUOTES = "/api/depth/quotes";
    private final String KLINE_BY_INDEX = "/api/kLine/byIndex";
    private final String KLINE_BY_TIME = "/api/kLine/byTime";
    private final String KLINE_LATEST = "/api/kLine/latest";
    private final String KLINE_LATEST_PERIOD = "/api/kLine/latestPeriod";
    private final String KLINE_FUNDING_RATE = "/api/kLine/fundingRate";
    private final String KLINE_TRADE_STATISTICS = "/api/kLine/tradeStatistics";
    private final String KLINE_OPEN_INTEREST = "/api/kLine/openInterest";

    // Query basic refData info
    public String refData() throws Exception {
        String url = BASE_URL + REF_DATA;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        return ApiUtil.callSync(request);
    }

    // Query last price of symbol(s)
    public String lastPrice(List<String> symbols) throws Exception {
        String url = BASE_URL + LAST_PRICE;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbols", StringUtils.join(symbols, ","));
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Query index price of instrument
    public String price(List<String> symbols) throws Exception {
        String url = BASE_URL + PRICE;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbols", StringUtils.join(symbols, ","));
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Query market depth of instrument
    public String depth(String symbol) throws Exception {
        String url = BASE_URL + DEPTH;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Query trades of instrument
    public String trades(String symbol) throws Exception {
        String url = BASE_URL + TRADES;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Query market depth & trades of instrument
    public String quotes(String symbol) throws Exception {
        String url = BASE_URL + QUOTES;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Query K line of instrument by index and step
    public String kLineByIndex(String symbol, String type, Integer step, Integer from) throws Exception {
        String url = BASE_URL + KLINE_BY_INDEX;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("type", type);
        urlBuilder.addQueryParameter("step", step.toString());
        urlBuilder.addQueryParameter("from", from.toString());
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Query K line of instrument by million seconds of key time
    public String kLineByTime(String symbol, String type, Integer step, Long from) throws Exception {
        String url = BASE_URL + KLINE_BY_TIME;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("type", type);
        urlBuilder.addQueryParameter("step", step.toString());
        if (from != null) urlBuilder.addQueryParameter("from", from.toString());
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Retrieve the latest K line of Instrument
    public String kLineLatest(String symbol, String type) throws Exception {
        String url = BASE_URL + KLINE_LATEST;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("type", type);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Retrieve K line(s) of Instrument since the last K line time(million seconds), Maximum 100 records can be returned
    public String kLineLatestPeriod(String symbol, String type, Long lastKTime) throws Exception {
        String url = BASE_URL + KLINE_LATEST_PERIOD;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("type", type);
        if (lastKTime != null) urlBuilder.addQueryParameter("lastKTime", lastKTime.toString());
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Latest funding rate
    public String fundingRate(List<String> symbols) throws Exception {
        String url = BASE_URL + KLINE_FUNDING_RATE;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbols", StringUtils.join(symbols, ","));
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Trade Statistics in latest 24 hours
    public String tradeStatistics(List<String> symbols) throws Exception {
        String url = BASE_URL + KLINE_TRADE_STATISTICS;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbols", StringUtils.join(symbols, ","));
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Total Open Position In Exchange
    public String openInterest(String symbol) throws Exception {
        String url = BASE_URL + KLINE_OPEN_INTEREST;
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }
}
