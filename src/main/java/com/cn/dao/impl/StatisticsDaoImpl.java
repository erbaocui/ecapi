package com.cn.dao.impl;

import com.cn.dao.IStatisticsDao;
import com.cn.vo.Statistics;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by home on 2017/7/7.
 */

@Repository("statisticsDao")
public class StatisticsDaoImpl extends BaseDaoImpl implements IStatisticsDao {

    @Override
    public List<Statistics> day(String customerId) {
        Map queryMap = new HashMap();
        queryMap.put("v_customer_id", customerId);
        return (List<Statistics>)list("com.cn.dao.StatisticsMapper.selectDay", queryMap);
    }

    @Override
    public List<Statistics> month(String customerId) {
        Map queryMap = new HashMap();
        queryMap.put("v_customer_id", customerId);
        return (List<Statistics>)list("com.cn.dao.StatisticsMapper.selectMonth", queryMap);
    }

    @Override
    public List<Statistics> week(String customerId) {
        Map queryMap = new HashMap();
        queryMap.put("v_customer_id", customerId);
        return (List<Statistics>)list("com.cn.dao.StatisticsMapper.selectWeek", queryMap);
    }

    @Override
    public Integer all(String customerId) {
        Map queryMap = new HashMap();
        queryMap.put("v_customer_id", customerId);
        return (Integer)findObject("com.cn.dao.StatisticsMapper.selectAll", queryMap);
    }
}
