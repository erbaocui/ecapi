package com.cn.dao.impl;

import com.cn.dao.ICarDao;
import com.cn.dao.ICarTypeDao;
import com.cn.model.Car;
import com.cn.model.CarType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("carTypeDao")
public class CarTypeDaoImpl extends BaseDaoImpl implements ICarTypeDao {

    @Override
    public List<CarType> allList( ) {
        return (List< CarType>)list("com.cn.dao.CarTypeMapper.selectAllList",  "");

    }

    @Override
    public CarType find(CarType carType) {
        return ( CarType)findObject("com.cn.dao.CarTypeMapper.selectOneByEntity",  carType);
    }
}
