package com.cn.service;

import com.cn.vo.Statistics;

import java.util.List;

public interface IStatisticsService {
    //用户
    public List<Statistics> getDay(String customerId);
    public List<Statistics>  getMonth(String customerId);
    public List<Statistics> getWeek(String customerId);
    public Integer getAll(String customerId);
}
