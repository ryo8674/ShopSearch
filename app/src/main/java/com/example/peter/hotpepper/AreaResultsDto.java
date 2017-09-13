package com.example.peter.hotpepper;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type AreaResultsDto.
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class AreaResultsDto {

    @SerializedName("large_area")
    private List<LargeAreaDto> largeAreaDtos;

    /**
     * Gets large areas.
     *
     * @return the large areas
     */
    List<LargeAreaDto> getLargeAreaDtos() {
        return largeAreaDtos;
    }

}
