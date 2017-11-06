package com.cn.dao.impl;

import com.cn.dao.ISupplierDao;
import com.cn.model. Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("supplierDao")
public class SupplierDaoImpl extends BaseDaoImpl implements ISupplierDao {

    @Override
    public List<Supplier> pageList( Supplier  supplier) {
        return (List< Supplier>)list("com.cn.dao.SupplierMapper.selectPageByEntity",  supplier);

    }

    @Override
    public List<Supplier> allValidList() {
        return (List< Supplier>)list("com.cn.dao.SupplierMapper.selectAllValid", "");
    }

    @Override
    public void insert(Supplier  supplier) {
        addObject("com.cn.dao.SupplierMapper.insert",  supplier);
    }

    @Override
    public void update(Supplier  supplier) {
        updateObject("com.cn.dao.SupplierMapper.updateByPrimaryKey",  supplier);
    }

    @Override
    public  Supplier find(Supplier  supplier) {
        return ( Supplier)findObject("com.cn.dao.SupplierMapper.selectOneByEntity",  supplier);
    }

    @Override
    public void delete(String supplierId) {
        deleteObject("com.cn.dao.SupplierMapper.deleteById", supplierId);
    }
}
