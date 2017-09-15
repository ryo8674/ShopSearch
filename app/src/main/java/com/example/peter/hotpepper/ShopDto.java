package com.example.peter.hotpepper;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;

class ShopDto {

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

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    private String getAccess() {
        return access;
    }

    String getMobileAccess() {
        return mobileAccess;
    }

    GenreDto getGenre() {
        return genre;
    }

    private String getPrivateRoom() {
        return privateRoom;
    }

    private String getCard() {
        return card;
    }

    private String getNonSmoking() {
        return nonSmoking;
    }

    private String getAddress() {
        return address;
    }

    private String getCourse() {
        return course;
    }

    private String getFreeDrink() {
        return freeDrink;
    }

    private String getFreeFood() {
        return freeFood;
    }

    PhotoDto getPhoto() {
        return photo;
    }

    private String getTatami() {
        return tatami;
    }

    String getShopInfo() {
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

    private static final String PRIVATE_ROOM = "個室";
    private static final String EXISTENCE = "あり";
    private static final String NO_EXISTENCE = "なし";
    private static final String CARD = "カード";
    private static final String AVAILABLE = "利用可";
    private static final String UNAVAILABLE = "利用不可";
    private static final String NON_SMOKING = "全面禁煙";
    private static final String PARTLY_SMOKING = "一部禁煙";
    private static final String SMOKING = "禁煙席なし";
    private static final String SEPARATE = ",";
    private StringBuilder message;

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

    Map<String, String> getShopMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("pic", getPhoto().getMobile().getPictL());
        map.put("ジャンル", getGenre().getName());
        map.put("アクセス", getAccess());
        map.put("住所", getAddress());
        map.put("カード", getCard());
        map.put("禁煙席", getNonSmoking());
        map.put("コース", getCourse());
        map.put("飲み放題", getFreeDrink());
        map.put("食べ放題", getFreeFood());
        map.put("座敷", getTatami());

        return map;
    }
}
