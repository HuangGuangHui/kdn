package com.hgh.interceptors;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


public class Listener implements ServletContextListener{
	private Timer timer=null;
	private Logger log=Logger.getLogger(this.getClass());
	
	public void contextInitialized(ServletContextEvent sce) {
		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
		log.info("----------启动监听器----------");
		timer = new Timer(true); 
		//签收数据推送到哲盟
//		timer.schedule(new sendSignZmTimer(), 0,10*1000);
		//签收提取
//		timer.schedule(new SignListener(), 0, 8*60*1000);
//		timer.schedule(new SignListener1(), 0, 8*60*1000);
//		timer.schedule(new SignListener2(), 0, 8*60*1000);
//		timer.schedule(new SignListener3(), 0, 8*60*1000);
		
		timer.schedule(new LogisticCodeListener(), 0,5*1000);
		log.info("----------添加定时任务----------");
	}

	// 在这里关闭监听器，所以在这里销毁定时器。
	public void contextDestroyed(ServletContextEvent sce) {
		if (timer!=null) {
			timer.cancel();  
			log.debug("定时器销毁");
		}
	}
}
