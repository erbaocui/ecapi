package com.cn.service;

import com.cn.vo.ActionLog;
import com.cn.vo.ActionLogEx;

import java.util.List;
import java.util.Map;

public interface IEsService {

    //操作日志相关
    public void createActionLog(ActionLog log);

    public Map getActionLogPageByEntity(ActionLogEx queryLog,Integer pageNo,Integer pageSize)throws Exception;


}
