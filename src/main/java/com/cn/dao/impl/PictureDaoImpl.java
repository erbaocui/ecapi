package com.cn.dao.impl;

import com.cn.dao.IPictureDao;
import com.cn.dao.IUserDao;
import com.cn.model.Picture;
import com.cn.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("pictureDao")
public class PictureDaoImpl extends BaseDaoImpl implements IPictureDao {

    @Override
    public List<Picture> pageList(Picture picture) {
        return (List<Picture>)list("com.cn.dao.PictureMapper.selectPageByEntity", picture);

    }

    @Override
    public void insert(Picture  picture) {
        addObject("com.cn.dao.PictureMapper.insert", picture);
    }

    @Override
    public void update(Picture  picture) {
        updateObject("com.cn.dao.PictureMapper.updateByPrimaryKey", picture);
    }
    @Override
    public void delete(String pictureId) {
        deleteObject("com.cn.dao.PictureMapper.deleteByPictureId",pictureId);
    }


    @Override
    public Picture find(Picture  picture) {
        return (Picture)findObject("com.cn.dao.PictureMapper.selectOneByEntity",  picture);
    }


}
