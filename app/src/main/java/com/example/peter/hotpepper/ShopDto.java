package com.example.peter.hotpepper;

import com.google.gson.annotations.SerializedName;

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

    String getAccess() {
        return access;
    }

    String getMobileAccess() {
        return mobileAccess;
    }

    GenreDto getGenre() {
        return genre;
    }

    String getPrivateRoom() {
        return privateRoom;
    }

    String getCard() {
        return card;
    }

    String getNonSmoking() {
        return nonSmoking;
    }

    String getAddress() {
        return address;
    }

    String getCourse() {
        return course;
    }

    String getFreeDrink() {
        return freeDrink;
    }

    String getFreeFood() {
        return freeFood;
    }

    PhotoDto getPhoto() {
        return photo;
    }

    String getTatami() {
        return tatami;
    }
}
