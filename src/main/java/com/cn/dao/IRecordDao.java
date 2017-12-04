package com.cn.dao;

import com.cn.model.Record;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IRecordDao {


    public List<Record> pageList(Record record);
    public void insert(Record record);
    public void update(Record record);
    public Record find(Record record);


}