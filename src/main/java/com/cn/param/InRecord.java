package com.cn.param;

import java.util.Date;

/**
 * Created by home on 2017/11/23.
 */
public class InRecord {
    private String id;
    private Date[] useTime;

    public InRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date[] getUseTime() {
        return useTime;
    }

    public void setUseTime(Date[] useTime) {
        this.useTime = useTime;
    }
}