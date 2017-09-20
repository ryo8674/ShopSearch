package com.example.peter.hotpepper.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * AreaResultsDtoクラス
 */
public class AreaResultsDto {

    @SerializedName("large_area")
    private List<LargeAreaDto> largeAreaDto;

    /**
     * LargeAreaDtoのリストを取得
     */
    public List<LargeAreaDto> getLargeAreaDto() {
        return largeAreaDto;
    }
}
