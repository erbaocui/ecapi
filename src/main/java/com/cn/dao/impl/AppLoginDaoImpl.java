package com.cn.dao.impl;

import com.cn.dao.IAppLoginDao;
import com.cn.model.AppLogin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("appLoginDao")
public class AppLoginDaoImpl extends BaseDaoImpl implements IAppLoginDao {

    @Override
    public List<AppLogin> appLoginList( AppLogin  appLogin) {
        return (List< AppLogin>)list("com.cn.dao.AppLoginMapper.selectPageByEntity",  appLogin);

    }


    @Override
    public void insert(AppLogin  appLogin) {
        addObject("com.cn.dao.AppLoginMapper.insert",  appLogin);
    }

    @Override
    public void update(AppLogin  appLogin) {
        updateObject("com.cn.dao.AppLoginMapper.updateByCustomerId",  appLogin);
    }

    @Override
    public  AppLogin find(AppLogin  appLogin) {
        return ( AppLogin)findObject("com.cn.dao.AppLoginMapper.selectOneByEntity",  appLogin);
    }

    @Override
    public void delete(String appLoginId) {
        deleteObject("com.cn.dao.AppLoginMapper.deleteById", appLoginId);
    }
}
