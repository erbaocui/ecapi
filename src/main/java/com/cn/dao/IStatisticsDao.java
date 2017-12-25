package com.cn.dao;

import com.cn.vo.Statistics;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IStatisticsDao {


    public List<Statistics> day(String customerId);
    public List<Statistics> month(String customerId);
    public List<Statistics> week(String customerId);
    public Integer all(String customerId);



}