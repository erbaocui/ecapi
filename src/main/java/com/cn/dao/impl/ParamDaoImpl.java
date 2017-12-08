package com.cn.dao.impl;

import com.cn.dao.IParamDao;
import com.cn.model.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("paramDao")
public class ParamDaoImpl extends BaseDaoImpl implements IParamDao {

    @Override
    public List<Param> list(Param param) {
        return (List<Param>)list("com.cn.dao.ParamMapper.selectByEntity", param);

    }

    @Override
    public void insert(Param param) {
        addObject("com.cn.dao.ParamMapper.insert", param);
    }

    @Override
    public void update(Param param) {
        updateObject("com.cn.dao.ParamMapper.updateByKey", param);
    }

    @Override
    public Param find(Param param) {
        return (Param)findObject("com.cn.dao.ParamMapper.selectOneByEntity", param);
    }
}
