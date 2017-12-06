package com.cn.param;

import java.math.BigDecimal;
import java.net.Inet4Address;

/**
 * Created by home on 2017/11/23.
 */
public class OutDevice {
    private String id;
    private String name;
    private Integer useCount;
    private Integer chargeCount;
    private Integer tar;
    private Integer co;
    private BigDecimal lat;
    private BigDecimal lon;


    public OutDevice() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getChargeCount() {
        return chargeCount;
    }

    public void setChargeCount(Integer chargeCount) {
        this.chargeCount = chargeCount;
    }

    public Integer getTar() {
        return tar;
    }

    public void setTar(Integer tar) {
        this.tar = tar;
    }

    public Integer getCo() {
        return co;
    }

    public void setCo(Integer co) {
        this.co = co;
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