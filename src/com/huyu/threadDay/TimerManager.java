package com.huyu.threadDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.Calendar;
/**
 * java定时任务，每天定时执行任务
 * @author wls
 *
 */
public class TimerManager {
    //时间间隔
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
        private static TimerManager timerManager;
    public static TimerManager getTimerManager(){
        if(timerManager == null){
            timerManager = new TimerManager();
        }
        return timerManager;
    }
    //private static final long PERIOD_DAY = 1000;
    private TimerManager() {
        Calendar calendar = Calendar.getInstance();

        /*** 定制每日2:00执行方法 ***/

        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);

        //Date date=calendar.getTime(); //第一次执行定时任务的时间
       Date date=getDate(23,59,00,00);
        System.out.println(date);
        System.out.println("before 方法比较："+date.before(new Date()));
        //如果第一次执行定时任务的时间 小于 当前的时间
        //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
        if (date.before(new Date())) {
            date = this.addDay(date, 1);
            System.out.println(date);
        }

        Timer timer = new Timer();

        DayThread task = new DayThread();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        timer.schedule(task,date,PERIOD_DAY);
    }

    // 增加或减少天数
    public Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
    //获取当天指定时间的date
    public Date getDate(int hour,int minute,int second,int milliSecond){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, hour);

        cal.set(Calendar.SECOND, second);

        cal.set(Calendar.MINUTE, minute);

        cal.set(Calendar.MILLISECOND, milliSecond);



        Date date = new Date(cal.getTimeInMillis());

        try {

            return format.parse(format.format(date));

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return null;

    }
}