package com.cn.service.impl;

import com.cn.dao.IAppLoginDao;
import com.cn.model.AppLogin;

import com.cn.service.IAppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public void modifyAppLogin(AppLogin appLogin) {
        appLoginDao.update(appLogin);
    }

    @Override
    public void deleteAppLogin(AppLogin appLogin) {
        appLoginDao.delete(appLogin.getId());
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)
    public void  addApplogin( AppLogin appLogin,AppLogin lastAppLogin)throws Exception{
        if( lastAppLogin!=null) {
            appLoginDao.update(lastAppLogin);
        }
        appLoginDao.insert(appLogin);
    }

    @Override
    public AppLogin getLastApploginByEntity(AppLogin lastAppLogin) {
        return  appLoginDao.find(lastAppLogin);
    }


    public IAppLoginDao getAppLoginDao() {
        return appLoginDao;
    }

    public void setAppLoginDao(IAppLoginDao appLoginDao) {
        this.appLoginDao = appLoginDao;
    }


}
