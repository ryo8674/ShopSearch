package com.example.peter.hotpepper;

import com.google.gson.annotations.SerializedName;

/**
 * The type Genre dto.
 */
class GenreDto {

    private String code;
    private String name;
    @SerializedName("catch")
    private String genre_catch;

    String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    void setName(String name) {
        this.name = name;
    }

}
