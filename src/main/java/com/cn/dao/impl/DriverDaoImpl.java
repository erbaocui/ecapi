package com.cn.dao.impl;

import com.cn.dao.IDriverDao;
import com.cn.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("driverDao")
public class DriverDaoImpl extends BaseDaoImpl implements IDriverDao {

    @Override
    public List<Driver> pageList( Driver  driver) {
        return (List< Driver>)list("com.cn.dao.DriverMapper.selectPageByEntity",  driver);

    }

    @Override
    public List<Driver> allValidList() {
        return (List< Driver>)list("com.cn.dao.DriverMapper.selectAllValid", "");
    }

    @Override
    public void insert(Driver  driver) {
        addObject("com.cn.dao.DriverMapper.insert",  driver);
    }

    @Override
    public void update(Driver  driver) {
        updateObject("com.cn.dao.DriverMapper.updateByPrimaryKey",  driver);
    }

    @Override
    public  Driver find(Driver  driver) {
        return ( Driver)findObject("com.cn.dao.DriverMapper.selectOneByEntity",  driver);
    }

    @Override
    public void delete(String driverId) {
        deleteObject("com.cn.dao.DriverMapper.deleteById", driverId);
    }
}
