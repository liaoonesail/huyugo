package com.huyu.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	
	/***判断字符是否含有特殊符号***/
	public static boolean checkString(String str){
		if(str != null && !"".equals(str)){
			if(str.indexOf("<") > -1){
				return false;
			}
			if(str.indexOf("&") > -1){
				return false;
			}
		}
		return true;
	}
	/***生成6位随机数***/
	public static int getRondom(){
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x+100000;
		return x;
	}
	
	/**
	 * 手机号通用判断
	 * 
	 * @param telNum
	 * @return
	 */
	public static boolean isMobiPhoneNum(String telNum) {
		String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(telNum);
		return m.matches();
	}
	
	/**
	 * 查看用户名是否只是由字母、数字、下划线、汉字组成
	 * @param userName
	 * @return true 为匹配成功，可以注册
	 */
	public static boolean pipei(String userName){
		String pipei  = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,20}$";
		Pattern pattern = Pattern.compile(pipei);
		boolean tf = pattern.matcher(userName).matches();
		return tf;
	}
	
	/**
     * 
     * @Title: processFileName
     * 
     * @Description: ie,chrom,firfox下处理文件名显示乱码
     */
    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }
    
    /**
     * 将字符串转换大写
     * @param varc
     * @return
     */
    public static String changeXiaoxie(String varc){
		if(varc != null && !"".equals(varc)){
			varc.replace(" ", "");   //将验证码里的空格去掉
			return varc.toLowerCase();
		}else{
			return "";
		}
	}
    
    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){ 
    	Pattern pattern = Pattern.compile("[0-9]*"); 
    	Matcher isNum = pattern.matcher(str);
    	if( !isNum.matches() ){
    	    return false; 
    	} 
    	return true; 
    }
    
    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String MD5(String str) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte tmp[] = mdTemp.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char strs[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                strs[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
//            return new String(strs).toUpperCase(); // 换后的结果转换为字符串
            return new String(strs); // 换后的结果转换为字符串
        } catch (Exception e) {
            return null;
        }
    }

}
