package com.cn.dao;

import com.cn.model.Commodity;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ICommodityDao {


    public List<Commodity> pageList(Commodity commodity);
    public void insert(Commodity commodity);
    public void update(Commodity commodity);
    public Commodity find(Commodity commodity);

}