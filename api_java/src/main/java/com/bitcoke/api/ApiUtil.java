package com.bitcoke.api;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;

abstract class ApiUtil {

    private static final OkHttpClient client = new OkHttpClient();


    public static String callSync(Request request) throws Exception {
        String str;
        Response response = client.newCall(request).execute();
        str = response.body().string();
        response.close();
        return str;
    }

    private static String getSignature(String apiSecret, String requestMethod, String path, Long expires) {
        return DigestUtils.sha256Hex(apiSecret + requestMethod + path + expires.toString());
    }

    public static Headers getHeaders(String apiKey, String apiSecret, String requestMethod, String apiPath) {
        Long expires = System.currentTimeMillis() / 1000 + 5;
        Headers.Builder builder = new Headers.Builder();
        builder.add("Content-Type", "application/json;charset=UTF-8")
                .add("apiKey", apiKey)
                .add("expires", expires.toString())
                .add("signature", getSignature(apiSecret, requestMethod, apiPath, expires));
        return builder.build();
    }
}
