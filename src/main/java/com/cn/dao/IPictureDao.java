package com.cn.dao;

import com.cn.model.Picture;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IPictureDao {


    public List<Picture> pageList(Picture picture);
    public void insert(Picture picture);
    public void update(Picture picture);
    public void delete(String pictureId);
    public Picture find(Picture picture);


}