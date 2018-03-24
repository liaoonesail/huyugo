package com.huyu.threadDay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
@Controller
@RequestMapping("/timerTask")
public  class Fenhong {
   /* @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TimerManager.getTimerManager();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }*/
   @RequestMapping("/run")
   public void run(){
       System.out.println("11111111111111111111");
       TimerManager.getTimerManager();
   }
   @RequestMapping("/runOnce")
    public void runOnce(){
       new FazhanSystem().FazhanSystem();
   }
}
