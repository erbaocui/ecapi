package com.cn.param;

import java.util.List;

/**
 * Created by home on 2017/11/27.
 */
public class OutCommodity {

    private String id;
    private  String	name;
    private  String	brief;
    private   String price;
    private   List<String> thumb;
    private	   List<String> pics;
    private   String hint;
    private   String urlJd;
    private   String urlTmall;

    public OutCommodity() {
    }

    public String getUrlTmall() {
        return urlTmall;
    }

    public void setUrlTmall(String urlTmall) {
        this.urlTmall = urlTmall;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getThumb() {
        return thumb;
    }

    public void setThumb(List<String> thumb) {
        this.thumb = thumb;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getUrlJd() {
        return urlJd;
    }

    public void setUrlJd(String urlJd) {
        this.urlJd = urlJd;
    }
}
