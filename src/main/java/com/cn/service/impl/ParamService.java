package com.cn.service.impl;

import com.cn.dao.IParamDao;
import com.cn.model.Param;
import com.cn.service.IParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("paramService")
public class ParamService implements IParamService {


    @Autowired
    private IParamDao paramDao;



    @Override
    public List<Param> getParamListByEntity(Param param){
        return paramDao.list(param);
    }
    @Override
    public Param getParamByEntity(Param param) {
        return paramDao.find(param);
    }
    @Override
    public void addParam(Param param) {
        paramDao.insert(param);
    }

    @Override
    public void modifyParam(Param param) {
        paramDao.update(param);
    }



    public IParamDao getParamDao() {
        return paramDao;
    }

    public void setParamDao(IParamDao paramDao) {
        this.paramDao = paramDao;
    }

}
