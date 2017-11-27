package com.cn.service.impl;

import com.cn.dao.IPictureDao;
import com.cn.dao.IPictureDao;
import com.cn.model.Picture;
import com.cn.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("pictureService")
public class PictureService implements IPictureService {


    @Autowired
    private IPictureDao pictureDao;



    @Override
    public List<Picture> getPicturePageByEntity(Picture picture){
        return pictureDao.pageList(picture);
    }
    @Override
    public Picture getPictureByEntity(Picture picture) {
        return pictureDao.find(picture);
    }
    @Override
    public void addPicture(Picture picture) {
        pictureDao.insert(picture);
    }

    @Override
    public void modifyPicture(Picture picture) {
        pictureDao.update(picture);
    }

    @Override
    public void deletePicture(Picture picture) {
        pictureDao.delete(picture.getId());
    }

    public IPictureDao getPictureDao() {
        return pictureDao;
    }

    public void setPictureDao(IPictureDao pictureDao) {
        this.pictureDao = pictureDao;
    }

}
