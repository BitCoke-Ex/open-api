package com.bitcoke.api;

import okhttp3.*;

public class TradeRestApi {

    private final String BASE_URL = "http://api.bitcoke.com/trade";

    private final String HTTP_GET = "GET";
    private final String HTTP_POST = "POST";

    private final String QUERY_ACCOUNT = "/api/trade/queryAccounts";
    private final String QUERY_ACTIVE_ORDERS = "/api/trade/queryActiveOrders";
    private final String QUERY_ORDERS = "/api/trade/queryOrders";
    private final String QUERY_ORDERS_PRO = "/api/trade/queryOrdersPro";
    private final String QUERY_ORDER_BY_ID = "/api/trade/queryOrderById";
    private final String QUERY_POSITION = "/api/trade/queryPosition";
    private final String LEDGER = "/api/trade/ledger";
    private final String ENTER_ORDER = "/api/trade/enterOrder";
    private final String CANCEL_ORDER = "/api/trade/cancelOrder";
    private final String AMEND_ORDER = "/api/trade/amendOrder";
    private final String CLOSE_POSITION = "/api/trade/closePosition";
    private final String CHANGE_POS_LEVERAGE = "/api/trade/changePosLeverage";
    private final String RISK_SETTING = "/api/trade/riskSetting";
    private final String SWITCH_POS_SIDE = "/api/trade/switchPosSide";
    private final String SWITCH_TO_CROSS = "/api/trade/switchToCross";

    private String apiKey;
    private String apiSecret;

    public TradeRestApi(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    // Request accounts of user by api key
    public String queryAccount() throws Exception {
        String url = BASE_URL + QUERY_ACCOUNT;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_GET, QUERY_ACCOUNT));
        Request request = builder.url(url).build();
        return ApiUtil.callSync(request);
    }

    // Request active orders of user by api key
    public String queryActiveOrders(String symbol) throws Exception {
        String url = BASE_URL + QUERY_ACTIVE_ORDERS;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_GET, QUERY_ACTIVE_ORDERS));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("symbol", symbol);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Request orders of user by api key
    // will be decommissioned soon, please use queryOrdersPro
    public String queryOrders(String prevId) throws Exception {
        String url = BASE_URL + QUERY_ORDERS;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_GET, QUERY_ORDERS));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        if (prevId != null) urlBuilder.addQueryParameter("prevId", prevId);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Request orders of user by api key
    public String queryOrdersPro(Long prevTimeMillis) throws Exception {
        String url = BASE_URL + QUERY_ORDERS_PRO;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_GET, QUERY_ORDERS_PRO));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        if (prevTimeMillis != null) urlBuilder.addQueryParameter("prevTimeMillis", prevTimeMillis.toString());
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Request order by id
    public String queryOrderById(String orderId) throws Exception {
        String url = BASE_URL + QUERY_ORDER_BY_ID;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_GET, QUERY_ORDER_BY_ID));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        if (orderId != null) urlBuilder.addQueryParameter("orderId", orderId);
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Request position of user by api key
    public String queryPosition() throws Exception {
        String url = BASE_URL + QUERY_POSITION;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_GET, QUERY_POSITION));
        Request request = builder.url(url).build();
        return ApiUtil.callSync(request);
    }

    // Request trade ledger of user by api key
    public String ledger(String currency, String from, Long pageNo, Long pageSize) throws Exception {
        String url = BASE_URL + LEDGER;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_GET, LEDGER));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("currency", currency);
        urlBuilder.addQueryParameter("from", from);
        urlBuilder.addQueryParameter("pageNo", pageNo.toString());
        urlBuilder.addQueryParameter("pageSize", pageSize.toString());
        builder.url(urlBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Enter order by complete parameters
    public String enterOrder(String currency, String symbol, Boolean openPosition, String side,
                             Double qty, String orderType, Double price, Boolean hidden,
                             Double showQty, Double stopLossPrice, Double stopWinPrice,
                             Double stopWinType, String tif, Double trailingStop, Double triggerPrice,
                             String triggerType) throws Exception {
        String url = BASE_URL + ENTER_ORDER;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, ENTER_ORDER));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("currency", currency);
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("openPosition", openPosition.toString());
        urlBuilder.addQueryParameter("side", side);
        urlBuilder.addQueryParameter("qty", qty.toString());
        urlBuilder.addQueryParameter("orderType", orderType);
        if (price != null) urlBuilder.addQueryParameter("price", price.toString());
        if (hidden != null) urlBuilder.addQueryParameter("hidden", hidden.toString());
        if (showQty != null) urlBuilder.addQueryParameter("showQty", showQty.toString());
        if (stopLossPrice != null) urlBuilder.addQueryParameter("stopLossPrice", stopLossPrice.toString());
        if (stopWinPrice != null) urlBuilder.addQueryParameter("stopWinPrice", stopWinPrice.toString());
        if (stopWinType != null) urlBuilder.addQueryParameter("stopWinType", stopWinType.toString());
        if (tif != null) urlBuilder.addQueryParameter("tif", tif);
        if (trailingStop != null) urlBuilder.addQueryParameter("trailingStop", trailingStop.toString());
        if (triggerPrice != null) urlBuilder.addQueryParameter("triggerPrice", triggerPrice.toString());
        if (triggerType != null) urlBuilder.addQueryParameter("triggerType", triggerType);
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Enter order by simplified parameters
    public String enterOrder(String currency, String symbol, Boolean openPosition, String side, Double qty,
                             String orderType, Double price) throws Exception {
        return enterOrder(currency, symbol, openPosition, side, qty, orderType, price, null, null, null,
                null, null, null, null, null, null);
    }

    // Cancel order by orderId
    public String cancelOrder(String orderId) throws Exception {
        String url = BASE_URL + CANCEL_ORDER;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, CANCEL_ORDER));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("orderId", orderId);
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    public String amendOrder(String orderId, Double price, Double qty, Double stopLossPrice,
                             Double stopWinPrice, Double trailingStop, Double triggerPrice) throws Exception {
        String url = BASE_URL + AMEND_ORDER;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, AMEND_ORDER));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("orderId", orderId);
        if (price != null) urlBuilder.addQueryParameter("price", price.toString());
        if (qty != null) urlBuilder.addQueryParameter("qty", qty.toString());
        if (stopLossPrice != null) urlBuilder.addQueryParameter("stopLossPrice", stopLossPrice.toString());
        if (stopWinPrice != null) urlBuilder.addQueryParameter("stopWinPrice", stopWinPrice.toString());
        if (trailingStop != null) urlBuilder.addQueryParameter("trailingStop", trailingStop.toString());
        if (triggerPrice != null) urlBuilder.addQueryParameter("triggerPrice", triggerPrice.toString());
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    public String closePosition(String currency, String symbol, String side) throws Exception {
        String url = BASE_URL + CLOSE_POSITION;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, CLOSE_POSITION));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("currency", currency);
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("side", side);
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Change position leverage
    public String changePosLeverage(String currency, String symbol, Double leverage) throws Exception {
        String url = BASE_URL + CHANGE_POS_LEVERAGE;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, CHANGE_POS_LEVERAGE));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("currency", currency);
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("leverage", leverage.toString());
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Change risk setting of position
    public String riskSetting(String currency, String symbol, String side, Double addDeposit,
                              Double stopLossPrice, Double stopWinPrice, Double stopWinType,
                              Double trailingStop) throws Exception {
        String url = BASE_URL + RISK_SETTING;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, RISK_SETTING));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("currency", currency);
        urlBuilder.addQueryParameter("symbol", symbol);
        urlBuilder.addQueryParameter("side", side);
        if (addDeposit != null) urlBuilder.addQueryParameter("addDeposit", addDeposit.toString());
        if (stopLossPrice != null) urlBuilder.addQueryParameter("stopLossPrice", stopLossPrice.toString());
        if (stopWinPrice != null) urlBuilder.addQueryParameter("stopWinPrice", stopWinPrice.toString());
        if (stopWinType != null) urlBuilder.addQueryParameter("stopWinType", stopWinType.toString());
        if (trailingStop != null) urlBuilder.addQueryParameter("trailingStop", trailingStop.toString());
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Switch position side
    public String switchPosSide(String currency, Boolean twoSidePosition) throws Exception {
        String url = BASE_URL + SWITCH_POS_SIDE;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, SWITCH_POS_SIDE));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("currency", currency);
        urlBuilder.addQueryParameter("twoSidePosition", twoSidePosition.toString());
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }

    // Switch to cross position mode
    public String switchToCross(String currency) throws Exception {
        String url = BASE_URL + SWITCH_TO_CROSS;
        Request.Builder builder = new Request.Builder();
        builder.headers(ApiUtil.getHeaders(apiKey, apiSecret, HTTP_POST, SWITCH_TO_CROSS));
        Request request = builder.url(url).build();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter("currency", currency);
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        builder.url(urlBuilder.build()).post(formBodyBuilder.build());
        return ApiUtil.callSync(builder.build());
    }
}
