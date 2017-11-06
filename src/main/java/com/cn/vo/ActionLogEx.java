package com.cn.vo;


import java.util.Date;

/**
 * Created by home on 2017/11/1.
 */
public class ActionLogEx extends  ActionLog{



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
