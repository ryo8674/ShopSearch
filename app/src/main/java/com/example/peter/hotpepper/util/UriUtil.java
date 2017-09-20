package com.example.peter.hotpepper.util;

import android.net.Uri;

import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.AUTHORITY;
import static com.example.peter.hotpepper.util.Constants.COUNT;
import static com.example.peter.hotpepper.util.Constants.COUNT_NUMBER;
import static com.example.peter.hotpepper.util.Constants.FORMAT;
import static com.example.peter.hotpepper.util.Constants.FORMAT_PARAMETER;
import static com.example.peter.hotpepper.util.Constants.HTTP_PROTOCOL;
import static com.example.peter.hotpepper.util.Constants.KEY;
import static com.example.peter.hotpepper.util.Constants.PATH_END;
import static com.example.peter.hotpepper.util.Constants.PATH_FIRST;
import static com.example.peter.hotpepper.util.Constants.USER_KEY;

/**
 * UriUtilクラス
 */
public class UriUtil {


    private static Uri.Builder builder;
    private static String path;


    /**
     * builderの初期化
     */
    private static void initUri() {
        builder = new Uri.Builder();
        builder.scheme(HTTP_PROTOCOL);
        builder.authority(AUTHORITY);
    }

    /**
     * リクエストパラメータの初期値
     */
    private static void initParam() {
        builder.appendQueryParameter(KEY, USER_KEY);
        builder.appendQueryParameter(FORMAT, FORMAT_PARAMETER);
        builder.appendQueryParameter(COUNT, COUNT_NUMBER);
    }

    /**
     * Uriを生成するメソッド
     */
    public static String createUri(String genre) {
        initUri();
        path = PATH_FIRST + genre + PATH_END;
        builder.path(path);
        initParam();

        return builder.toString();
    }

    /**
     * リクエストパラメータがあるUriを生成するメソッド
     */
    public static String createUri(String genre, Map<String, String> request) {
        initUri();
        path = PATH_FIRST + genre + PATH_END;
        builder.path(path);
        initParam();

        for (Map.Entry<String, String> entry : request.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.toString();
    }
}
