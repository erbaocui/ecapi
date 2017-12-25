package com.cn.param;


/**
 * Created by home on 2017/11/23.
 */
public class OutDevice {
    private String id;
    private String name;
    private Integer useCount=0;
    private Integer chargeCount=0;
    private Integer tar=0;
    private Integer nicotine=0;
    private Integer health=0;
    private Integer co=0;
    private Double lat=new Double(0);
    private Double lon=new Double(0);
    private Double baiduLat=new Double(0);
    private Double baiduLon=new Double(0);
    private String appShareIcon;
    private String appShareTitle;
    private String appShareContent;
    private String shareIcon;
    private String shareTitle;
    private String shareContent;
    private String shareUrl;


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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getBaiduLat() {
        return baiduLat;
    }

    public void setBaiduLat(Double baiduLat) {
        this.baiduLat = baiduLat;
    }

    public Double getBaiduLon() {
        return baiduLon;
    }

    public void setBaiduLon(Double baiduLon) {
        this.baiduLon = baiduLon;
    }

    public String getAppShareIcon() {
        return appShareIcon;
    }

    public void setAppShareIcon(String appShareIcon) {
        this.appShareIcon = appShareIcon;
    }

    public String getAppShareTitle() {
        return appShareTitle;
    }

    public void setAppShareTitle(String appShareTitle) {
        this.appShareTitle = appShareTitle;
    }

    public String getAppShareContent() {
        return appShareContent;
    }

    public void setAppShareContent(String appShareContent) {
        this.appShareContent = appShareContent;
    }

    public String getShareIcon() {
        return shareIcon;
    }

    public void setShareIcon(String shareIcon) {
        this.shareIcon = shareIcon;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getNicotine() {
        return nicotine;
    }

    public void setNicotine(Integer nicotine) {
        this.nicotine = nicotine;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}