package com.huyu.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huyu.service.FileUploadUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/5/24.
 */
@Controller
@RequestMapping("/files")
public class FileUploadController {

    private FileUploadUtil fileUploadUtil;

    public FileUploadUtil getFileUploadUtil() {
        return fileUploadUtil;
    }
    @Resource
    public void setFileUploadUtil(FileUploadUtil fileUploadUtil) {
        this.fileUploadUtil = fileUploadUtil;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/uploadImage")
    public String uploadFile(@RequestParam("images") CommonsMultipartFile file, HttpServletResponse response) throws IOException{
        String fileName = "";
        try {
            fileName = fileUploadUtil.uploadFile(file, "huyugo/images/personphoto/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().print(fileName);
        return null;
    }
    
}
