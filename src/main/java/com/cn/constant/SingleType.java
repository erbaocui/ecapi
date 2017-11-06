package com.cn.constant;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * Created by home on 2017/7/22.
 */
public enum SingleType {
    SINGLE("单程", 0),RETURN("往返", 1);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private SingleType(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (SingleType c : SingleType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
