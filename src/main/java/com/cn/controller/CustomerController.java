package com.cn.controller;

import com.cn.constant.CustomerType;
import com.cn.constant.Status;
import com.cn.model.Customer;
import com.cn.model.Customer;
import com.cn.service.ICustomerService;
import com.cn.util.ExcelExportUtil;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
import com.cn.view.ViewExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/customer")
@Scope("prototype")
public class CustomerController extends BaseController{

    Logger logger= Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private ICustomerService customerService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 个人客户管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/person")
    public String person() throws Exception{
        return "redirect:/page/customer/personList.jsp";
    }
    /**
     * 企业客户管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/enterprise")
    public String enterprise() throws Exception{
        return "redirect:/page/customer/enterpriseList.jsp";
    }
    /**
     * 客户列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map customerList(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String loginName,String displayName,String status,String type,HttpSession session)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        Customer customer=new Customer();
        if(StringUtil.isNotEmpty(loginName)) {
            customer.setLoginName(loginName);
            session.setAttribute("loginName",loginName);
        }
        if(StringUtil.isNotEmpty(displayName)) {
            customer.setDisplayName(displayName);
            session.setAttribute("displayName",displayName);
        }
        if(StringUtil.isNotEmpty(status)) {
           if(!status.equals("-1")){
               customer.setStatus(status);
               session.setAttribute("status",status);
           }
        }
        customer.setType(type);
        List<Customer> list=customerService.getCustomerPageByEntity(customer);
        PageInfo<Customer>p=new PageInfo<Customer>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
     /*   map.put("total", 0);
        map.put("rows",new ArrayList());*/
        return map;
    }

    /**
     * 客户查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exist(String loginName)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Customer customer=new Customer();
        customer.setLoginName(loginName);
        Customer u=customerService.getCustomerByEntity(customer);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }


    /**
     * 客户添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String loginName,String displayName,String idNumber,String type,String password,String remark,String taxId,String contactMobile,String contact)throws Exception {
        Map result = new HashMap();
        Customer customer = new Customer();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        customer.setId(uuid);
        customer.setLoginName(loginName);
        customer.setDisplayName(displayName);
        customer.setType(type);
        if (type.equals("1")) {
            customer.setContact(contact);
            customer.setContactMobile(contactMobile);
            customer.setTaxId(taxId);

        }else{
            if (StringUtil.isNotEmpty(idNumber)){
                customer.setIdNumber(idNumber);
            }
        }
        if (StringUtil.isNotEmpty(remark)){
            customer.setRemark(remark);
        }
        customer.setStatus("0");
        customer.setPassword(MD5Util.md5(password));
        customerService.addCustomer(customer);
        result.put("result","success");
         return result;
    }

    /**
     * 客户编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String idNumber,String displayName,String type,String password,String remark,String taxId,String contactMobile,String contact,String status)throws Exception
    {
        Map result=new HashMap();
        Customer customer=new Customer();
        customer.setId(id);
        customer.setDisplayName(displayName);
        if(type.equals("1")){
            customer.setContact(contact);
            customer.setContactMobile(contactMobile);
            customer.setTaxId(taxId);
        }else{
            if (StringUtil.isNotEmpty(idNumber)){
                customer.setIdNumber(idNumber);
            }
        }
        if (StringUtil.isNotEmpty(remark)) {
            customer.setRemark(remark);
        }
        if (StringUtil.isNotEmpty(password)) {
            customer.setPassword(MD5Util.md5(password));
        }
        customer.setStatus(status);

        customerService.modifyCustomer(customer);
        result.put("result","success");
        return result;
    }
    /**
     *有效乘客列表
     * @param
     *
     * @return listCar json
     */
    @RequestMapping(value = "/validList")
    public @ResponseBody
    List<Customer> validList(String type)throws Exception
    {
        List<Customer> list=  customerService.getAllValidCustomer(type);
        return list;
    }

    @RequestMapping(value="/export")
    public ModelAndView export(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                               HttpSession session,String type) {
        ViewExcel viewExcel = new ViewExcel();
        Map obj = new HashMap();

        String displayName,loginName,status;
        displayName=(String)session.getAttribute("displayName");
        loginName=(String)session.getAttribute("loginName");
        status=(String)session.getAttribute("status");
        Customer customer=new Customer();
        if(StringUtil.isNotEmpty(loginName)) {
            customer.setLoginName(loginName);
            session.removeAttribute("loginName");

        }
        if(StringUtil.isNotEmpty(displayName)) {
            customer.setDisplayName(displayName);
            session.removeAttribute("displayName");

        }
        customer.setType(type);
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                customer.setStatus(status);
                session.removeAttribute("status");
            }
        }
        List<Customer> list=customerService.getCustomerPageByEntity(customer);

        String[] personHeaders={"登录名", "显示名","身份证号","状态","备注"};
        String[] enterpriseHeaders={"登录名", "显示名","企业税号","联系人","联系电话","状态","备注"};

        String[] p_ds_titles = { "loginName", "displayName", "idNumber","status","remark"};
        String[] e_ds_titles = { "loginName", "displayName", "taxId","contact","contactMobile","status","remark"};



        int[] p_ds_format = { 1, 1, 1,1,1};
        int[] e_ds_format = { 1, 1, 1,1,1,1,1};
        List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
        //if(CollectionUtils.isNotEmpty(dataset)){
        for(Customer c :list){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("loginName", c.getLoginName());
            map.put("displayName", c.getDisplayName());
           if(type.equals(String.valueOf(CustomerType.PERSON.getIndex()))){
               map.put("idNumber", c.getIdNumber());
           }else{
               map.put("taxId", c.getTaxId());
               map.put("contact",c.getContact());
               map.put("contactMobile",c.getContactMobile());
           }
            map.put("status", Status.getName(Integer.parseInt(c.getStatus())) );
            map.put("remark", c.getRemark());
            data.add(map);
        }
        // }
        String filename="customer.xls";
        obj.put("filename",filename);
        try {
            HSSFWorkbook  workbook;
            if(type.equals(String.valueOf(CustomerType.PERSON.getIndex()))){
                workbook = ExcelExportUtil.export("", "sheet1", personHeaders, p_ds_titles, p_ds_format, null, data);
            }else {
                workbook = ExcelExportUtil.export("", "sheet1", enterpriseHeaders, e_ds_titles, e_ds_format, null, data);
            }
            viewExcel.buildExcelDocument(obj, workbook, request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ModelAndView(viewExcel, model);
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
