package com.example.peter.hotpepper.dto;

import com.google.gson.annotations.SerializedName;

/**
 * MobileDtoクラス
 */
public class MobileDto {
    @SerializedName("l")
    private String photoL;
    @SerializedName("s")
    private String photoS;

    /**
     * 店舗トップ写真(大)を取得
     */
    public String getPhotoL() {
        return photoL;
    }

    /**
     * 店舗トップ写真(小)を取得
     */
    public String getPhotoS() {
        return photoS;
    }
}
