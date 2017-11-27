package com.cn.service;

import com.cn.model.Picture;


import java.util.List;

public interface IPictureService {
    //用户
    public List<Picture> getPicturePageByEntity(Picture picture);
    public Picture getPictureByEntity(Picture picture);
    public void addPicture(Picture picture);
    public void modifyPicture(Picture picture);
    public void deletePicture(Picture picture);

}
