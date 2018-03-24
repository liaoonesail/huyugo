package com.huyu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author 廖该逼--生成订单号
 *
 */
public class OrderNum {
	public static String getOrderNum() {
		
		return System.currentTimeMillis()+""+(int)(100000+Math.random()*(999999-100000+1));
	}
	/**
	 * 
	 * @return 当前时间yyyy/MM/dd HH:mm
	 */
	public static String getSystemTime(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String time=format.format(date);
		return time;
	}
	public  static String getdate(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
		String time=format.format(date);
		return time;
	}
	/**
	 * 
	 * @return 当前时间yyyy-MM-dd HH:mm:ss
	 */
	public static String getregTime(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		return time;
	}
	/**
	 * 判断时间是否包含在内
	 * @param t1 
	 * @param t2
	 * @param format
	 * @return
	 */
	public static boolean largerTime(String t1,String t2,String format) 
    {
        Date date1 ,date2;
        DateFormat formart = new SimpleDateFormat(format);
        try
        {
            date1 = formart.parse(t1);
            date2 = formart.parse(t2);
            if(date1.compareTo(date2)<0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (ParseException e)
        {
            System.out.println("date init fail!");
            e.printStackTrace();
            return false;
        }
    }
	/**
	 * 判断时间是否包含在内
	 * @param t1 
	 * @param t2
	 * @param format
	 * @return
	 */
	public static boolean largerTime1(String t1,String t2,String format) 
	{
		Date date1 ,date2;
		DateFormat formart = new SimpleDateFormat(format);
		try
		{
			date1 = formart.parse(t1);
			date2 = formart.parse(t2);
			if(date1.compareTo(date2)<=0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (ParseException e)
		{
			System.out.println("date init fail!");
			e.printStackTrace();
			return false;
		}
	}
}
