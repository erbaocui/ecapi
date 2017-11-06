package com.cn.service.impl;

import com.cn.dao.IAppLoginDao;
import com.cn.model.AppLogin;

import com.cn.service.IAppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("appLoginService")
public class AppLoginService implements IAppLoginService {


    @Autowired
    private IAppLoginDao appLoginDao;
 



    @Override
    public List<AppLogin>  getAppLoginListByEntity(AppLogin appLogin){
        return appLoginDao.appLoginList( appLogin);
    }


    @Override
    public AppLogin getAppLoginByEntity(AppLogin appLogin) {
        return appLoginDao.find(appLogin);
    }
    @Override
    public void addAppLogin(AppLogin appLogin) {
        appLoginDao.insert(appLogin);
    }

    @Override
    public void modifyAppLogin(AppLogin appLogin) {
        appLoginDao.update(appLogin);
    }

    @Override
    public void deleteAppLogin(AppLogin appLogin) {
        appLoginDao.delete(appLogin.getId());
    }


    public IAppLoginDao getAppLoginDao() {
        return appLoginDao;
    }

    public void setAppLoginDao(IAppLoginDao appLoginDao) {
        this.appLoginDao = appLoginDao;
    }


}
