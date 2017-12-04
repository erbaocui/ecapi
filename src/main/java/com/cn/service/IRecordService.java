package com.cn.service;

import com.cn.model.Record;

import java.util.List;

public interface IRecordService {
    //用户
    public List<Record> getRecordPageByEntity(Record record);
    public Record getRecordByEntity(Record record);
    public void addRecord(Record record);
    public void modifyRecord(Record record);

}
