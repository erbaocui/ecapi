package com.cn.constant;

/**
 * Created by home on 2017/7/22.
 */
public enum Status {
    EFFECTIVE("有效", 0),INVALID("无效", 1);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private Status(String name, int index) {
        this.index=index;
        this.name = name;
    }
    // 普通方法
    public static String getName(int index) {
        System.out.println(Status.values());
        for (Status c : Status.values()) {
            System.out.println(c.getIndex());
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
