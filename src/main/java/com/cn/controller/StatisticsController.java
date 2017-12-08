package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;
import com.cn.constant.Status;
import com.cn.dao.IStatisticsDao;
import com.cn.model.*;
import com.cn.param.*;
import com.cn.service.*;
import com.cn.util.CoodinateCovertor;
import com.cn.util.DateUtil;
import com.cn.util.IdGenerator;
import com.cn.util.LngLat;
import com.cn.vo.RetObj;
import com.cn.vo.Statistics;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/statistics")
@Scope("prototype")
public class StatisticsController extends BaseController{

    Logger logger= Logger.getLogger(StatisticsController.class.getName());

    @Autowired
    private IStatisticsService statisticsService;



    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    /**
     * 日记录
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/day")
    @Config(methods = "day",module = "统计模块",needlogin =true,interfaceLog =true)
    public RetObj day(HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Customer customer=getCurrentCustomer(request);

        List<Statistics> list=statisticsService.getDay(customer.getId());
        List<OutDay> dataList=new ArrayList<OutDay>();
        for(Statistics statistics:list){
            OutDay  outDay=new OutDay();
            outDay.setUseTime(DateUtil.convert2String(statistics.getUseTime(),"HH:mm"));
            outDay.setDurationTime(statistics.getDurationTime()+requestContext.getMessage("sys.statistics.minute"));
            dataList.add(outDay);
        }
        retObj.setData(dataList);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;

    }

    /**
     * 周记录
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/week")
    @Config(methods = "week",module = "统计模块",needlogin =true,interfaceLog =true)
    public RetObj week(HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Customer customer=getCurrentCustomer(request);

        List<Statistics> list=statisticsService.getWeek(customer.getId());
        List<OutWeek> dataList=new ArrayList<OutWeek>();
        int weekDay=DateUtil.getWeekDay(new Date());
        if(weekDay==0){
            weekDay=7;
        }
        for(int i=1;i<=weekDay;i++) {

            OutWeek outWeek = new OutWeek();
            if(i==1) {
                outWeek.setWeekDay(requestContext.getMessage("sys.statistics.Monday"));
            }
            if(i==2) {
                outWeek.setWeekDay(requestContext.getMessage("sys.statistics.Tuesday"));
            }
            if(i==3) {
                outWeek.setWeekDay(requestContext.getMessage("sys.statistics.Wednesday"));
            }
            if(i==4) {
                outWeek.setWeekDay(requestContext.getMessage("sys.statistics.Thursday"));
            }
            if(i==5) {
                outWeek.setWeekDay(requestContext.getMessage("sys.statistics.Friday"));
            }
            if(i==6) {
                outWeek.setWeekDay(requestContext.getMessage("sys.statistics.Saturday"));
            }
            if(i==7) {
                outWeek.setWeekDay(requestContext.getMessage("sys.statistics.Sunday"));
            }
            Integer durationTime=0;
            for (Statistics statistics : list) {
                if(statistics.getUseNo().equals(i)) {
                    durationTime=statistics.getDurationTime();
                }
                if(i==7){
                    if(statistics.getUseNo().equals(0)) {
                        durationTime=statistics.getDurationTime();
                    }
                }
            }
            outWeek .setDurationTime(String.valueOf(durationTime)+ requestContext.getMessage("sys.statistics.minute"));
            dataList.add(outWeek);
        }
        retObj.setData(dataList);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;

    }

    /**
     * 月记录
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/month")
    @Config(methods = "month",module = "统计模块",needlogin =true,interfaceLog =true)
    public RetObj month(HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Customer customer=getCurrentCustomer(request);


        List<Statistics> list=statisticsService.getMonth(customer.getId());
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        List<OutMonth> dataList=new ArrayList<OutMonth>();
        for(int i=1;i<=week;i++) {
            OutMonth outMonth = new OutMonth();
            if(i==1) {
                outMonth.setWeek(requestContext.getMessage("sys.statistics.firstweek"));
            }
            if(i==2) {
                outMonth.setWeek(requestContext.getMessage("sys.statistics.secondweek"));
            }
            if(i==3) {
                outMonth.setWeek(requestContext.getMessage("sys.statistics.thirdweek"));
            }
            if(i==4) {
                outMonth.setWeek(requestContext.getMessage("sys.statistics.fourthweek"));
            }
            if(i==5) {
                outMonth.setWeek(requestContext.getMessage("sys.statistics.fifthweek"));
            }
            Integer durationTime=0;
            for (Statistics statistics : list) {
                if(statistics.getUseNo().equals(i)) {
                    durationTime=statistics.getDurationTime();
                }
            }
            outMonth .setDurationTime(String.valueOf(durationTime) + requestContext.getMessage("sys.statistics.minute"));
            dataList.add(outMonth);
        }
        retObj.setData(dataList);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;

    }




    public IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    public static  void  main(String args[]){

        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        int day = c.get(Calendar.DAY_OF_WEEK);//获致是本周的第几天地, 1代表星期天...7代表星期六
        System.out.println("今天是本月的第" + week + "周");
        //System.out.println("今天是星期" + weeks[day-1]);
        //System.out.println(DateUtil.getWeekDay(DateUtil.convert2Date("2017-12-10 16:11:11", "yyyy-MM-dd HH:mm:ss")));
    }
}
