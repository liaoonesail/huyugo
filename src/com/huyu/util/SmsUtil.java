package com.huyu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsUtil {
	
	private static final String name = "18581358679";
	private static final String pwd = "B3EE0DD62B4E8A8809CA7FED6499";
	private static final String sign = "互余购商城";

	public static String reg(String code){
		return "尊敬的用户，您的短信验证码为 " + code + ",如非本人操作，请忽略本信息";
	}
	
	public static void main(String[] args) throws Exception {
		
		// 电话号码字符串，中间用英文逗号间隔
		StringBuffer mobileString=new StringBuffer("15330322445");
		// 内容字符串
		StringBuffer contextString=new StringBuffer("再次测试短信是否能发送2");
		// 追加发送时间，可为空，为空为及时发送
		//String stime="";
		// 扩展码，必须为数字 可为空
		//StringBuffer extno=new StringBuffer();
		
        System.out.println(doPost(mobileString, contextString));
    }

	/**
	 * 短信发送
	 * @param phone
	 * @param phoneCode
	 * @return
	 */
	public static boolean send(String phone, String phoneCode){
		boolean flag = false;
		String result = "";
		try {
			result = doPost(new StringBuffer(phone), new StringBuffer(phoneCode));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!"".equals(result)){
			if("0".equals(result.split(",")[0])){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 发送短信
	 * 
	 * @param name			用户名
	 * @param pwd			密码
	 * @param mobileString	电话号码字符串，中间用英文逗号间隔
	 * @param contextString	内容字符串
	 * @param sign			签名
	 * @param stime			追加发送时间，可为空，为空为及时发送
	 * @param extno			扩展码，必须为数字 可为空
	 * @return				
	 * @throws Exception
	 */
    public static String doPost(StringBuffer mobileString, StringBuffer contextString) throws Exception {
    	StringBuffer param = new StringBuffer();
    	param.append("name="+name);
    	param.append("&pwd="+pwd);
    	param.append("&mobile=").append(mobileString);
    	param.append("&content=").append(URLEncoder.encode(contextString.toString(),"UTF-8"));
    	param.append("&stime="+"");
    	param.append("&sign=").append(URLEncoder.encode(sign,"UTF-8"));
    	param.append("&type=pt");
    	param.append("&extno=").append("");
        
        URL localURL = new URL("http://web.cr6868.com/asmx/smsservice.aspx?");
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String resultBuffer = "";
        
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            
            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);
            
        } finally {
            
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer;
    }
	
	
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
}
