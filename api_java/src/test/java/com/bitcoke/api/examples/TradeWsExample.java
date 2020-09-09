package com.bitcoke.api.examples;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.digest.DigestUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class TradeWsExample {

    private static String WS_TRADE_URL = "wss://api.bitcoke.com/ws/trade";

    private static String apiKey = "your api key";
    private static String apiSecret = "your api secret";

    private static String loginUser = "your id";
    private static String loginPwd = "your password";

    private static String txId = "TX20200724-133934-511-458";

    private static class TradingWebSocketClient extends WebSocketClient {

        private final String ACCOUNT_UPDATE = "/api/trade/accountUpdate";
        private final String ORDER_UPDATE = "/api/trade/orderUpdate";
        private final String POSITION_UPDATE = "/api/trade/positionUpdate";
        private final String TRADELEDGER_UPDATE = "/api/trade/tradeLedgerUpdate";
        private final String LOGIN = "login";

        public TradingWebSocketClient(URI serverUri) {
            super(serverUri, new Draft_6455());
        }

        @Override
        public void onOpen(ServerHandshake serverHandshake) {
            System.out.println("Connection opened");
            login(txId, loginUser, loginPwd);
        }

        @Override
        public void onMessage(String s) {
//            System.out.println(s);
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(s);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String event = jsonObject.get("event").getAsString();
            if (event.equalsIgnoreCase(LOGIN)) {
                String errCode = jsonObject.get("errCode").getAsString();
                if (!errCode.equalsIgnoreCase("0")) {
                    System.out.println("Login failed");
                    this.close();
                    return;
                }
                System.out.println("Login successfully");
                // Subscribe accountUpdate
                String accountUpdateSub = getContent(ACCOUNT_UPDATE);
                System.out.println(accountUpdateSub);
                this.send(accountUpdateSub);
                // Subscribe orderUpdate
                accountUpdateSub = getContent(ORDER_UPDATE);
                System.out.println(accountUpdateSub);
                this.send(accountUpdateSub);
                // Subscribe positionUpdate
                accountUpdateSub = getContent(POSITION_UPDATE);
                System.out.println(accountUpdateSub);
                this.send(accountUpdateSub);
                // Subscribe tradeLedgerUpdate
                accountUpdateSub = getContent(TRADELEDGER_UPDATE);
                System.out.println(accountUpdateSub);
                this.send(accountUpdateSub);

                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (event.equalsIgnoreCase(ACCOUNT_UPDATE)) {
                System.out.println(s);
                //do something
            } else if (event.equalsIgnoreCase(ORDER_UPDATE)) {
                System.out.println(s);
            } else if (event.equalsIgnoreCase(POSITION_UPDATE)) {
                System.out.println(s);
            } else if (event.equalsIgnoreCase(TRADELEDGER_UPDATE)) {
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

        private void login(String txId, String user, String pwd) {
            String pass = DigestUtils.sha1Hex(pwd);
            this.send("{\"txId\":\"" + txId + "\",\"password\":\""
                    + pass + "\",\"user\":\"" + user + "\",\"op\":\"" + LOGIN + "\"}");
        }

        private String getContent(String path) {
            long expires = System.currentTimeMillis() / 1000 + 5;
            String orginContent = "GET" + path + expires;
            String signature = DigestUtils.sha256Hex(apiSecret + orginContent);
            JsonObject object = new JsonObject();
            object.addProperty("apiKey", apiKey);
            object.addProperty("signature", signature);
            object.addProperty("expires", expires);
            object.addProperty("op", "subscribe");
            object.addProperty("channel", path);
            return object.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        TradingWebSocketClient client = new TradingWebSocketClient(new URI(WS_TRADE_URL));
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
