package com.cn.service.impl;

import com.cn.dao.ICommodityDao;
import com.cn.model.Commodity;
import com.cn.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("commodityService")
public class CommodityService implements ICommodityService {


    @Autowired
    private ICommodityDao commodityDao;



    @Override
    public List<Commodity> getCommodityPageByEntity(Commodity commodity){
        return commodityDao.pageList(commodity);
    }
    @Override
    public Commodity getCommodityByEntity(Commodity commodity) {
        return commodityDao.find(commodity);
    }
    @Override
    public void addCommodity(Commodity commodity) {
        commodityDao.insert(commodity);
    }

    @Override
    public void modifyCommodity(Commodity commodity) {
        commodityDao.update(commodity);
    }



    public ICommodityDao getCommodityDao() {
        return commodityDao;
    }

    public void setCommodityDao(ICommodityDao commodityDao) {
        this.commodityDao = commodityDao;
    }

}
