package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;

import com.cn.constant.PicType;

import com.cn.model.Commodity;

import com.cn.model.Picture;
import com.cn.param.InCommodityList;
import com.cn.param.InFindPwd;
import com.cn.param.OutCommodity;
import com.cn.service.ICommodityService;
import com.cn.service.IPictureService;


import com.cn.vo.RetObj;
import com.github.pagehelper.PageHelper;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/commodity")
@Scope("prototype")
public class CommodityController extends BaseController{

    Logger logger= Logger.getLogger(CommodityController.class.getName());

    @Autowired
    private ICommodityService commodityService;
    @Autowired
    private IPictureService pictureService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 商品管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/commodity")
    public String commodity() throws Exception{
        return "redirect:/page/commodity/list.jsp";
    }

    /**
     * 商品列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    @Config(methods = "list",module = "商品模块",needlogin = true,interfaceLog =true)
    public @ResponseBody
    RetObj list(@JsonParam InCommodityList inCommodityList,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        PageHelper.startPage(inCommodityList.getPage()+1 ,inCommodityList.getSize());

        Commodity commodity=new Commodity();
        List<OutCommodity> outList=new ArrayList<OutCommodity>();
        List<Commodity> list=commodityService.getCommodityPageByEntity(commodity);

       /* PageInfo<Commodity>p=new PageInfo<Commodity>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);*/
        PageHelper.startPage(1,0);
        for(Commodity c:list){
            OutCommodity outCommodity=new OutCommodity();
            BeanUtils.copyProperties(outCommodity, c);
            String hint="";
            hint+=c.getCertified()+","+c.getSpeedRefund()+","+c.getSevenReturn();
            outCommodity.setHint(hint);
            Picture picture=new Picture();
            picture.setType(String.valueOf(PicType.COMMODITY.getIndex()));
            picture.setRelationId(c.getId());
            List<Picture> picList=pictureService.getPicturePageByEntity(picture);
            List<String> pics=new ArrayList<String>();
            for(Picture p:picList){
               pics.add(p.getUrl());
            }
            outCommodity.setPics(pics);
            picture.setType(String.valueOf(PicType.COMMODITY_THUMB.getIndex()));
            picture.setRelationId(c.getId());
             picList=pictureService.getPicturePageByEntity(picture);
             List<String> thumb=new ArrayList<String>();
            for(Picture p:picList){
                thumb.add(p.getUrl());
            }
            outCommodity.setThumb(thumb);
            outList.add(outCommodity);
        }
        retObj.setData(outList);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }







    public ICommodityService getCommodityService() {
        return commodityService;
    }

    public void setCommodityService(ICommodityService commodityService) {
        this.commodityService = commodityService;
    }
}
