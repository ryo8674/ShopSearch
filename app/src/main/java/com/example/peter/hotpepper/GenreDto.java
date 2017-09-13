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

    /**
     * Gets code.
     *
     * @return the code
     */
    String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
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

    /**
     * Gets genre catch.
     *
     * @return the genre catch
     */
    String getGenre_catch() {
        return genre_catch;
    }

    /**
     * Sets genre catch.
     *
     * @param genre_catch the genre catch
     */
    void setGenre_catch(String genre_catch) {
        this.genre_catch = genre_catch;
    }
}
