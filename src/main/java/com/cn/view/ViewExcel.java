package com.cn.view;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by home on 2017/7/28.
 */
public class ViewExcel extends AbstractExcelView {

    @Override
    public void buildExcelDocument(Map obj,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //String filename = "抽奖活动人员名单.xls";//设置下载时客户端Excel的名称
        String filename=(String)obj.get("filename");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"),"ISO-8859-1"));
        //response.setHeader("Content-Disposition", "attachment;filename=data.xls");
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

}