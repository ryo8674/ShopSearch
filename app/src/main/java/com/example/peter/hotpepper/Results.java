package com.example.peter.hotpepper;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Results.
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class Results {

    @SerializedName("large_area")
    private List<LargeArea> largeAreas;

    /**
     * Gets large areas.
     *
     * @return the large areas
     */
    List<LargeArea> getLargeAreas() {
        return largeAreas;
    }

}
