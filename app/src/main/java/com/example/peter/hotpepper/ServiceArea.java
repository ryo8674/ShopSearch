package com.example.peter.hotpepper;

import com.google.gson.annotations.SerializedName;

/**
 * The type Service area.
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class ServiceArea {

    @SerializedName("name")
    private String serviceAreaName;

    @SerializedName("code")
    private String serviceAreaCode;

    /**
     * Gets service area name.
     *
     * @return the service area name
     */
    String getServiceAreaName() {
        return serviceAreaName;
    }

}
