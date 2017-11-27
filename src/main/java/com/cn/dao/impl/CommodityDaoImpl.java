package com.cn.dao.impl;

import com.cn.dao.ICommodityDao;
import com.cn.model.Commodity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("commodityDao")
public class CommodityDaoImpl extends BaseDaoImpl implements ICommodityDao {

    @Override
    public List<Commodity> pageList(Commodity commodity) {
        return (List<Commodity>)list("com.cn.dao.CommodityMapper.selectPageByEntity", commodity);

    }

    @Override
    public void insert(Commodity commodity) {
        addObject("com.cn.dao.CommodityMapper.insert", commodity);
    }

    @Override
    public void update(Commodity commodity) {
        updateObject("com.cn.dao.CommodityMapper.updateByPrimaryKey", commodity);
    }

    @Override
    public Commodity find(Commodity commodity) {
        return (Commodity)findObject("com.cn.dao.CommodityMapper.selectOneByEntity", commodity);
    }
}
