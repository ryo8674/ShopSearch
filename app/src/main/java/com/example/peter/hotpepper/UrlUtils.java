package com.example.peter.hotpepper;

import android.net.Uri;

import java.util.Map;

class UrlUtils {
    private static final String HTTP_PROTOCOL = "http";
    private static final String AUTHORITY = "webservice.recruit.co.jp";
    private static final String PATH_FIRST = "hotpepper/";
    private static final String PATH_END = "/v1";

    private static final String USER_PARAMETER = "key";
    private static final String USER_KEY = "8928fba69a934d6e";
    private static final String FORMAT_PARAMETER = "format";
    private static final String FORMAT_KEY = "json";
    private static final String COUNT_KEY = "count";
    private static final String MAX_COUNT = "50";

    private static Uri.Builder uriBuilder;

    private static void initUri(String path) {
        uriBuilder = new Uri.Builder();
        uriBuilder.scheme(HTTP_PROTOCOL);
        uriBuilder.authority(AUTHORITY);
        uriBuilder.path(PATH_FIRST + path + PATH_END);
        uriBuilder.appendQueryParameter(USER_PARAMETER, USER_KEY);
        uriBuilder.appendQueryParameter(FORMAT_PARAMETER, FORMAT_KEY);
        uriBuilder.appendQueryParameter(COUNT_KEY, MAX_COUNT);
    }

    static String createUri(String path) {
        initUri(path);
        return uriBuilder.toString();
    }

    static String createUri(String path, Map<String, String> param) {
        initUri(path);
        for (Map.Entry<String, String> entry : param.entrySet()) {
            uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return uriBuilder.toString();
    }

}
