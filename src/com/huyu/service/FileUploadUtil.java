package com.huyu.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huyu.util.UtilFianl;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24.
 */
@Component
public class FileUploadUtil {
    private SimpleDateFormat datefile = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * kindEditor上传文件
     * @param file
     * @return
     * @throws Exception
     */
    public Map<String, Object> uploadFile(CommonsMultipartFile file, HttpServletRequest request) throws Exception{
        String fileName = null;
        String url="kindEditor/";
        if(!file.isEmpty()){
            String dirName = request.getParameter("dir");
            if (dirName == null) {
                dirName = "image";
            }
            url+=dirName+"/";
            //最大文件大小   5MB
            long maxSize = 5120000;
            //定义允许上传的文件扩展名
            Map<String, String> extMap = new HashMap<String, String>();
            extMap.put("image", "gif,jpg,jpeg,png,bmp");
            extMap.put("flash", "swf,flv");
            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,txt,zip,rar,gz,bz2");

            if(!extMap.containsKey(dirName)){
                return getError("目录名不正确。");
            }

            String fileName_ = file.getOriginalFilename();
            checkFile(UtilFianl.uploadAdress+url);
            String hzm = fileName_.substring(fileName_.lastIndexOf(".")+1);

            //检查文件大小
            if(file.getSize() > maxSize){
                return getError("上传文件大小超过5MB限制。");
            }
            //检查扩展名
            String fileExt = fileName_.substring(fileName_.lastIndexOf(".") + 1).toLowerCase();
            if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
                return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
            }

            fileName = url + getFileName() + "." + hzm;
            FileOutputStream os = new FileOutputStream(UtilFianl.uploadAdress + fileName);
            InputStream is = file.getInputStream();
            int b = 0;
            while((b = is.read()) != -1){
                os.write(b);
            }
            os.flush();
            os.close();
            is.close();

            Map<String, Object> succMap = new HashMap<String, Object>();
            succMap.put("error", 0);
            succMap.put("url", "/file/"+fileName);
            return succMap;
        }
        return getError("请选择有内容的文件。");
    }
    private Map<String, Object> getError(String errorMsg) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("error", 1);
        errorMap.put("message", errorMsg);
        return errorMap;
    }
    /**
     * 上传图片
     * @param file
     * @param url
     * @return
     * @throws Exception
     */
    public String uploadFile(CommonsMultipartFile file,String url) throws Exception{
        String fileName = null;
        if(!file.isEmpty()){
            String fileName_ = file.getOriginalFilename();
            checkFile(UtilFianl.uploadAdress+url);
            String hzm = fileName_.substring(fileName_.lastIndexOf(".")+1);
            if("jpg".equals(hzm) || "jpeg".equals(hzm) || "png".equals(hzm) || "gif".equals(hzm)){
                fileName = url + getFileName() + "." + hzm;
                FileOutputStream os = new FileOutputStream(UtilFianl.uploadAdress + fileName);
                InputStream is = file.getInputStream();
                int b = 0;
                while((b = is.read()) != -1){
                    os.write(b);
                }
                os.flush();
                os.close();
                is.close();
            }
        }
        return "/upload/"+fileName;
    }

    //循环添加文件路径
    private void checkFile(String path){
        if(path.indexOf("/") > -1){
            String[] args = path.split("/");
            String path_ = "";
            for(int i=0;i<args.length;i++){
                path_ += args[i]+"/";
                if(args[i] != null && !"".equals(args[i])){
                    File file_ = new File(path_);
                    if (!file_.exists()) {
                        file_.mkdir();
                    }
                }
            }
        }
    }

    private String getFileName(){
        String fileName = datefile.format(new Date());
        fileName += Math.random()*10 + Math.random()*10;
        return fileName;
    }
}
