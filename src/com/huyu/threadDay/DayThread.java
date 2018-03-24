package com.huyu.threadDay;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
public class DayThread extends TimerTask {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        try {
            //在这里写你要执行的内容
            System.out.println("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
            new FazhanSystem().FazhanSystem();
        } catch (Exception e) {
            System.out.println("-------------FazhanSystem.java解析信息发生异常--------------");
        }
    }
}
