package com.cn.vo;

import com.cn.model.Feedback;

import java.util.Date;

/**
 * Created by home on 2017/11/28.
 */
public class FeedbackEx  extends Feedback{


    private Date beginTime;
    private Date endTime;

    public FeedbackEx() {
    }

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
