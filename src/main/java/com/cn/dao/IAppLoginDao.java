package com.cn.dao;

import com.cn.model.AppLogin;
import com.cn.model.AppLogin;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IAppLoginDao {
    public List<AppLogin> appLoginList(AppLogin appLogin);
    public void insert(AppLogin appLogin);
    public void update(AppLogin appLogin);
    public AppLogin find(AppLogin appLogin);
    public void delete(String appLoginId);


}