package com.example.peter.hotpepper;


import com.google.gson.annotations.SerializedName;

/**
 * The type Large area.
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class LargeArea {

    @SerializedName("service_area")
    private ServiceArea serviceArea;
    private String name;
    @SerializedName("large_service_area")
    private LargeServiceArea largeServiceArea;
    private String code;

    /**
     * Gets service area.
     *
     * @return the service area
     */
    ServiceArea getServiceArea() {
        return serviceArea;
    }

    /**
     * Sets service area.
     *
     * @param serviceArea the service area
     */
    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets large service area.
     *
     * @return the large service area
     */
    public LargeServiceArea getLargeServiceArea() {
        return largeServiceArea;
    }

    /**
     * Sets large service area.
     *
     * @param largeServiceArea the large service area
     */
    public void setLargeServiceArea(LargeServiceArea largeServiceArea) {
        this.largeServiceArea = largeServiceArea;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
