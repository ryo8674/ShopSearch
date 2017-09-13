package com.example.peter.hotpepper;

import com.google.gson.annotations.SerializedName;

class MobileDto {
    @SerializedName("l")
    private String pictL;
    @SerializedName("s")
    private String pictS;

    String getPictL() {
        return pictL;
    }

    void setPictL(String pictL) {
        this.pictL = pictL;
    }

    String getPictS() {
        return pictS;
    }

    void setPictS(String pictS) {
        this.pictS = pictS;
    }
}
