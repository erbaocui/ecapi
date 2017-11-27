package com.cn.service;

import com.cn.model.Commodity;

import java.util.List;

public interface ICommodityService {
    //用户
    public List<Commodity> getCommodityPageByEntity(Commodity commodity);
    public Commodity getCommodityByEntity(Commodity commodity);
    public void addCommodity(Commodity commodity);
    public void modifyCommodity(Commodity commodity);

}
