package com.bitcoke.api.examples;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class MarketWsExample {

    private static String WS_MARKET_URL = "wss://api.bitcoke.com/ws/market";

    private static class MarketDataWebSocketClient extends WebSocketClient {

        private final String INDEX = "/api/index/price";
        private final String DEPTH = "/api/depth/depth";
        private final String FUNDINGRATE = "/api/kLine/fundingRate";
        private final String OPENINTEREST = "/api/kLine/openInterest";
        private final String TRADESTATISTICS = "/api/kLine/tradeStatistics";
        private final String KLINE = "/api/kLine/kLine";

        public MarketDataWebSocketClient(URI serverUri) {
            super(serverUri, new Draft_6455());
        }

        @Override
        public void onOpen(ServerHandshake serverHandshake) {
            System.out.println("Connection opened");
            // Index
            this.send("{\"op\":\"subscribe\", \"channel\":\"" + INDEX + "\", \"key\":\"BTCUSD\"}");
            this.send("{\"op\":\"subscribe\", \"channel\":\"" + DEPTH + "\", \"key\":\"XBTCUSD\"}");
            this.send("{\"op\":\"subscribe\", \"channel\":\"" + FUNDINGRATE + "\", \"key\":\"XBTCUSD\"}");
            this.send("{\"op\":\"subscribe\", \"channel\":\"" + OPENINTEREST + "\", \"key\":\"BTCUSD\"}");
            this.send("{\"op\":\"subscribe\", \"channel\":\"" + TRADESTATISTICS + "\", \"key\":\"BTCUSD\"}");
            this.send("{\"op\":\"subscribe\", \"channel\":\"" + KLINE + "\", \"key\":\"XBTCUSD\", \"type\":\"1M\"}");
        }

        @Override
        public void onMessage(String s) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(s);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String event = jsonObject.get("event").getAsString();

            if (event.equalsIgnoreCase(INDEX)) {
                System.out.println(s);
                // do something
            } else if (event.equalsIgnoreCase(DEPTH)) {
                System.out.println(s);
            } else if (event.equalsIgnoreCase(FUNDINGRATE)) {
                System.out.println(s);
            } else if (event.equalsIgnoreCase(OPENINTEREST)) {
                System.out.println(s);
            } else if (event.equalsIgnoreCase(TRADESTATISTICS)) {
                System.out.println(s);
            } else if (event.equalsIgnoreCase(KLINE)) {
                System.out.println(s);
            }
        }

        @Override
        public void onClose(int i, String s, boolean b) {
            System.out.println("Connection is closed");
        }

        @Override
        public void onError(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        WebSocketClient client = new MarketDataWebSocketClient(new URI(WS_MARKET_URL));
        client.connect();
        int i = 60;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (--i < 0) break;
        }
        client.close();
    }
}
