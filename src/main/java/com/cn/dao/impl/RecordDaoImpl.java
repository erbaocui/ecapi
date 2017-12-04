package com.cn.dao.impl;

import com.cn.dao.IRecordDao;
import com.cn.model.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("recordDao")
public class RecordDaoImpl extends BaseDaoImpl implements IRecordDao {

    @Override
    public List< Record> pageList( Record record) {
        return (List< Record>)list("com.cn.dao.RecordMapper.selectPageByEntity", record);

    }

    @Override
    public void insert( Record record) {
        addObject("com.cn.dao.RecordMapper.insert", record);
    }

    @Override
    public void update( Record record) {
        updateObject("com.cn.dao.RecordMapper.updateByPrimaryKey", record);
    }

    @Override
    public  Record find( Record record) {
        return ( Record)findObject("com.cn.dao.RecordMapper.selectOneByEntity", record);
    }
}
