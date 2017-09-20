package com.example.peter.hotpepper.dto;

import com.google.gson.annotations.SerializedName;

/**
 * GenreDto
 */
public class GenreDto {
    private String code;
    private String name;
    @SerializedName("catch")
    private String genreCatch;

    /**
     * ジャンルコードを取得
     */
    public String getCode() {
        return code;
    }

    /**
     * ジャンル名を取得
     */
    public String getName() {
        return name;
    }

    /**
     * ジャンルキャッチを取得
     */
    public String getGenreCatch() {
        return genreCatch;
    }
}
