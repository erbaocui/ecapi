package com.cn.vo;

import com.cn.model.Message;

import java.util.Date;

/**
 * Created by home on 2017/12/8.
 */
public class MessageEx extends Message {
    private Date beginTime;
    private Date endTime;
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
