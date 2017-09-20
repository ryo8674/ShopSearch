package com.example.peter.hotpepper.dto;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.ACCESS;
import static com.example.peter.hotpepper.util.Constants.ADDRESS;
import static com.example.peter.hotpepper.util.Constants.AVAILABLE;
import static com.example.peter.hotpepper.util.Constants.AVAILABLE_CARD;
import static com.example.peter.hotpepper.util.Constants.BLANK;
import static com.example.peter.hotpepper.util.Constants.CARD;
import static com.example.peter.hotpepper.util.Constants.COURSE;
import static com.example.peter.hotpepper.util.Constants.EXISTENCE;
import static com.example.peter.hotpepper.util.Constants.FREE_DRINK;
import static com.example.peter.hotpepper.util.Constants.FREE_FOOD;
import static com.example.peter.hotpepper.util.Constants.GENRE;
import static com.example.peter.hotpepper.util.Constants.HORIGOTATSU;
import static com.example.peter.hotpepper.util.Constants.NON_SMOKING;
import static com.example.peter.hotpepper.util.Constants.NO_EXISTENCE;
import static com.example.peter.hotpepper.util.Constants.PARTLY_SMOKING;
import static com.example.peter.hotpepper.util.Constants.PRIVATE_ROOM;
import static com.example.peter.hotpepper.util.Constants.SEPARATE;
import static com.example.peter.hotpepper.util.Constants.SMOKING;
import static com.example.peter.hotpepper.util.Constants.SMOKING_SEAT;
import static com.example.peter.hotpepper.util.Constants.TATAMI;
import static com.example.peter.hotpepper.util.Constants.UNAVAILABLE;

/**
 * ShopDtoクラス
 */
public class ShopDto {


    @SerializedName("large_area")
    private LargeAreaDto largeArea;
    private String id;
    private String name;
    private String access;
    @SerializedName("mobile_access")
    private String mobileAccess;
    private GenreDto genre;
    @SerializedName("private_room")
    private String privateRoom;
    private String card;
    @SerializedName("non_smoking")
    private String nonSmoking;
    private String address;
    private String course;
    @SerializedName("free_drink")
    private String freeDrink;
    @SerializedName("free_food")
    private String freeFood;
    private PhotoDto photo;
    private String tatami;
    private String horigotatsu;

    private StringBuilder message;

    /**
     * 大エリアを取得
     */
    public LargeAreaDto getLargeArea() {
        return largeArea;
    }

    /**
     * 店舗IDを取得
     */
    public String getId() {
        return id;
    }

    /**
     * 店舗IDを設定
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 店舗名を取得
     */
    public String getName() {
        return name;
    }

    /**
     * 交通アクセスを取得
     */
    private String getAccess() {
        return access;
    }

    /**
     * 携帯用交通アクセスを取得
     */
    public String getMobileAccess() {
        return mobileAccess;
    }

    /**
     * ジャンルを取得
     */
    public GenreDto getGenre() {
        return genre;
    }

    /**
     * 個室情報を取得
     */
    private String getPrivateRoom() {
        return privateRoom;
    }

    /**
     * クレジットカード利用可否情報を取得
     */
    private String getCard() {
        return card;
    }

    /**
     * 禁煙席情報を取得
     */
    private String getNonSmoking() {
        return nonSmoking;
    }

    /**
     * 住所を取得
     */
    private String getAddress() {
        return address;
    }

    /**
     * コースの有無情報を取得
     */
    private String getCourse() {
        return course;
    }

    /**
     * 飲み放題情報を取得
     */
    private String getFreeDrink() {
        return freeDrink;
    }

    /**
     * 食べ放題情報を取得
     */
    private String getFreeFood() {
        return freeFood;
    }

    /**
     * 画像を取得
     */
    public PhotoDto getPhoto() {
        return photo;
    }

    /**
     * 座敷の有無情報を取得
     */
    private String getTatami() {
        return tatami;
    }

    /**
     * 掘りごたつの有無を取得
     */
    private String getHorigotatsu() {
        return horigotatsu;
    }

    /**
     * 店舗一覧に表示する店舗の補足情報を取得
     */
    public String getShopInfo() {
        StringBuilder message = new StringBuilder();
        if (!getInstantPrivateRoom().isEmpty()) {
            message.append(getInstantPrivateRoom()).append(SEPARATE);
        }
        if (!getInstantCard().isEmpty()) {
            message.append(getInstantCard()).append(SEPARATE);
        }
        if (getInstantNonSmoking().isEmpty() && message.length() != 0) {
            message.deleteCharAt(message.length() - 1);
        } else {
            message.append(getInstantNonSmoking());
        }
        return message.toString();
    }

    /**
     * 店舗一覧に表示する個室情報を取得
     */
    private String getInstantPrivateRoom() {
        message = new StringBuilder();
        message.append(PRIVATE_ROOM);
        if (getPrivateRoom().contains(EXISTENCE)) {
            message.append(EXISTENCE);
        } else if (getPrivateRoom().contains(NO_EXISTENCE)) {
            message.append(NO_EXISTENCE);
        }
        return message.toString();
    }

    /**
     * 店舗一覧に表示するカードの利用可否を取得
     */
    private String getInstantCard() {
        message = new StringBuilder();
        message.append(CARD);
        if (getCard().contains(AVAILABLE)) {
            message.append(AVAILABLE);
        } else if (getCard().contains(UNAVAILABLE)) {
            message.append(UNAVAILABLE);
        }
        return message.toString();
    }

    /**
     * 店舗一覧に表示する禁煙席情報を取得
     */
    private String getInstantNonSmoking() {
        message = new StringBuilder();
        if (getNonSmoking().contains(NON_SMOKING)) {
            message.append(NON_SMOKING);
        } else if (getNonSmoking().contains(PARTLY_SMOKING)) {
            message.append(PARTLY_SMOKING);
        } else if (getNonSmoking().contains(SMOKING)) {
            message.append(SMOKING);
        }
        return message.toString();
    }

    /**
     * 店舗詳細に表示する情報を取得
     */
    public Map<String, String> getShopDetailInfo() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(BLANK, getPhoto().getMobile().getPhotoL());
        map.put(GENRE, getGenre().getName());
        map.put(ACCESS, getAccess());
        map.put(ADDRESS, getAddress());
        map.put(COURSE, getCourse());
        map.put(FREE_DRINK, getFreeDrink());
        map.put(FREE_FOOD, getFreeFood());
        map.put(PRIVATE_ROOM, getPrivateRoom());
        map.put(HORIGOTATSU, getHorigotatsu());
        map.put(TATAMI, getTatami());
        map.put(AVAILABLE_CARD, getCard());
        map.put(SMOKING_SEAT, getNonSmoking());

        return map;
    }
}
