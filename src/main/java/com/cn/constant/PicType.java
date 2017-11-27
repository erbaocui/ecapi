package com.cn.constant;

/**
 * Created by home on 2017/7/22.
 */
public enum PicType {
    MERCHANT("MERCHANT", 0),COMMODITY("COMMODITY", 1),COMMODITY_THUMB("COMMODITY_THUMB", 2);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private PicType(String name, int index) {
        this.index=index;
        this.name = name;
    }
    // 普通方法
    public static String getName(int index) {
        System.out.println(PicType.values());
        for (PicType c : PicType.values()) {
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
