package com.cn.controller;

import com.cn.anno.Config;
import com.cn.model.User;
import com.cn.service.IEsService;
import com.cn.service.IUserService;
import com.cn.util.DateUtil;
import com.cn.util.StringUtil;
import com.cn.vo.ActionLog;
import com.cn.vo.ActionLogEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/actionlog")
@Scope("prototype")
public class ActionLogController extends BaseController{

    Logger logger= Logger.getLogger(ActionLogController.class.getName());


    @Autowired
    private IEsService esService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 用户管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/page")
    @Config(needlogin = false,interfaceLog =false)
    public String user() throws Exception{
        return "redirect:/page/actionLog/list.jsp";
    }

    /**
     * 用户列表查询
     * @param
     *
     * @return  json
     */
    @RequestMapping(value = "/list")
    @Config(needlogin = false,interfaceLog =false)
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String loginName,String beginTime,String endTime,String token,Integer status)throws Exception
    {

        ActionLogEx queryLog=new ActionLogEx();
        Integer pageNumber= Integer.valueOf(page)-1;
        Integer pageSize= Integer.valueOf(rows);
        if(StringUtil.isNotEmpty(loginName)){
            queryLog.setLoginId(loginName);
        }
        if(StringUtil.isNotEmpty(token)){
            queryLog.setToken(token);
        }
        if(StringUtil.isNotEmpty( beginTime)){
            queryLog.setBeginTime(DateUtil.convert2Date(beginTime,"yyyy-MM-dd HH:mm:ss"));
        }
        if(StringUtil.isNotEmpty( endTime)){
            queryLog.setEndTime(DateUtil.convert2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(StringUtil.isNotEmpty( status)){
            queryLog.setStatus(status);
        }
        Map map= esService.getActionLogPageByEntity(queryLog,pageNumber,pageSize);
        Map result=new HashMap();
        result.put("total", map.get("total"));
        result.put("rows",map.get("list"));
        return result;
    }


    public IEsService getEsService() {
        return esService;
    }

    public void setEsService(IEsService esService) {
        this.esService = esService;
    }
}
