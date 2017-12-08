package com.cn.vo;

import java.util.Date;

/**
 * Created by home on 2017/12/8.
 */
public class Statistics {
    private Date useTime;
    private Integer useNo;
    private Integer durationTime;

    public Statistics() {
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getUseNo() {
        return useNo;
    }

    public void setUseNo(Integer useNo) {
        this.useNo = useNo;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }
}
