package com.cn.dao;

import com.cn.model.Version;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IVersionDao {


    public List<Version> pageList(Version version);
    public void insert(Version version);
    public void update(Version version);
    public Version find(Version version);
    public void delete(Version version);


}