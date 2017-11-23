package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.constant.FileUploadDic;
import com.cn.service.IQiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

/**
 * Created by 13 on 2017/4/7.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private IQiniuService qiniuService;

    /**
     *
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="upload",method=RequestMethod.POST,produces="text/html;charset=utf-8")
    @Config(methods = "测试方法",module = "测试模块",needlogin = false,interfaceLog =true)
    public String uploadFile(@RequestParam(value = "file",required=false) CommonsMultipartFile file,HttpServletRequest req,HttpServletResponse response) throws Exception{

//设置文件保存的本地路径

       // String filePath = req.getSession().getServletContext().getRealPath("/uploadFiles/");
        String filePath= FileUploadDic.PATH;
        filePath=  filePath+"/";
        File dic=new File(filePath);

        if(!dic.isDirectory()){
            dic.createNewFile();
        }

       // MultipartFile file =files;

        String fileName =file.getOriginalFilename();

        String fileType = fileName.split("[.]")[1];

//为了避免文件名重复，在文件名前加UUID

        String uuid = UUID.randomUUID().toString().replace("-","");

        String uuidFileName = uuid + fileName;

        // 包含原始文件名的字符串
        String f =file.getOriginalFilename();
        // 提取文件拓展名

        qiniuService.upload(file.getBytes(), uuidFileName);


       // FileUtils.writeByteArrayToFile(new File(filePath,  uuidFileName), file.getBytes());
        response.getWriter().print("/uploadFiles/"+uuidFileName);
        return null;

    }
}
