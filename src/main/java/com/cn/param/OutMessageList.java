package com.cn.param;

import java.util.Date;

/**
 * Created by home on 2017/12/9.
 */
public class OutMessageList {

    private String id;
    private Date sendTime;
    private String title;

    public OutMessageList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
