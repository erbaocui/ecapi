package com.cn.service;

import com.cn.model.Param;

import java.util.List;

public interface IParamService {
    //用户
    public List<Param> getParamListByEntity(Param param);
    public Param getParamByEntity(Param param);
    public void addParam(Param param);
    public void modifyParam(Param param);

}
