package com.cn.service.impl;

import com.cn.dao.IRecordDao;
import com.cn.model.Record;
import com.cn.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("recordService")
public class RecordService implements IRecordService {


    @Autowired
    private IRecordDao recordDao;



    @Override
    public List<Record> getRecordPageByEntity(Record record){
        return recordDao.pageList(record);
    }
    @Override
    public Record getRecordByEntity(Record record) {
        return recordDao.find(record);
    }
    @Override
    public void addRecord(Record record) {
        recordDao.insert(record);
    }

    @Override
    public void modifyRecord(Record record) {
        recordDao.update(record);
    }



    public IRecordDao getRecordDao() {
        return recordDao;
    }

    public void setRecordDao(IRecordDao recordDao) {
        this.recordDao = recordDao;
    }

}
