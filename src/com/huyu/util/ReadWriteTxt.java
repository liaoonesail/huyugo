package com.huyu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class ReadWriteTxt {
	 public static String read(String path,HttpServletRequest request){
		 String realPath="D:/"+path;
		 File file = new File(realPath);
	        StringBuilder result = new StringBuilder();
	        if(file.exists()){
	        	try{
		            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
		            String s = null;
		            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
		                result.append(System.getProperty("line.separator")+s);
		            }
		            br.close();    
		        }catch(Exception e){
		            e.printStackTrace();
		        }
		        return result.toString();
	        }else{
	        	return null;
	        }
	        
	    }
	 public static void write(String html,String time,HttpServletRequest request){
		 if(html!=null&&!"".equals(html)){
				String realPath="D:/upload"+request.getContextPath()+"/html/";
				File dir = new File(realPath);
				if(!dir.exists()){
					dir.mkdirs();
				}
				File destFile = new File(dir, time+".txt");
				FileWriter writer;
				try {
					writer=new FileWriter(destFile);
					writer.write(html);
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 }
	 public static boolean del(String path,HttpServletRequest request){
		 String realPath="D:/"+path;

		        boolean flag = false;
		        File file = new File(realPath);
		        // 判断目录或文件是否存在
		        if (!file.exists()) {  // 不存在返回 false
		            return flag;
		        } else {
		            // 判断是否为文件
		            if (file.isFile()) {  // 为文件时调用删除文件方法
		                return deleteFile(realPath);
		            }else{
		            	return flag;
		            }
		        }
		    
	 }
	    public static boolean deleteFile(String path) {
	        boolean flag = false;
	        File file = new File(path);
	        // 路径为文件且不为空则进行删除
	        if (file.isFile() && file.exists()) {
	            file.delete();
	            flag = true;
	        }
	        return flag;
	    }
}

