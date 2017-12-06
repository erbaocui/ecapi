package com.cn.param;

import java.math.BigDecimal;

/**
 * Created by home on 2017/11/23.
 */
public class InDeviceDisconncet {
    private String id;

    private BigDecimal lat;
    private BigDecimal lon;

    public InDeviceDisconncet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }
}