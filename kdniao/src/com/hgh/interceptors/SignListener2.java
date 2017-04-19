package com.hgh.interceptors;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hgh.dao.commonDao;
import com.hgh.receive.newdata;
import com.hgh.zmentity.RequestData;

public class SignListener2 extends TimerTask{
	private Logger log=Logger.getLogger(this.getClass());
	private List<newdata> lists = new ArrayList<newdata>();
	private List<newdata> lists1 = new ArrayList<newdata>();
	private List<newdata> lists2 = new ArrayList<newdata>();
	private List<newdata> lists3 = new ArrayList<newdata>();
	private List<newdata> lists4 = new ArrayList<newdata>();
	private List<newdata> lists5 = new ArrayList<newdata>();
	private List<newdata> lists6 = new ArrayList<newdata>();
	Gson g = new Gson();
	@Override
	public void run() {
		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
		log.info("----------进入签收提取前2000条----------");
		lists = new commonDao().selectDataReceive3000();
		log.info(lists.size());
		if(lists.size()>0){
			int index = lists.size()/5;
			if(index>=1){
				lists1=lists.subList(0, index);
				lists2=lists.subList(index, index*2);
				lists3=lists.subList(index*2, index*3);
				lists4=lists.subList(index*3, index*4);
				lists5=lists.subList(index*4, index*5);
			}
			lists6=lists.subList(index*5, lists.size());
			Thread t1=new Thread("as"){
				public void run(){
					log.info(g.toJson(lists1));
					new signTimer().signTest(lists1);
				}
			};
			Thread t2=new Thread("bs"){
				public void run(){
					log.info(g.toJson(lists2));
					new signTimer().signTest(lists2);
				}
			};
			Thread t3=new Thread("cs"){
				public void run(){
					log.info(g.toJson(lists3));
					new signTimer().signTest(lists3);
				}
			};
			Thread t4=new Thread("ds"){
				public void run(){
					log.info(g.toJson(lists4));
					new signTimer().signTest(lists4);
				}
			};
			Thread t5=new Thread("es"){
				public void run(){
					log.info(g.toJson(lists5));
					new signTimer().signTest(lists5);
				}
			};
			Thread t6=new Thread("fs"){
				public void run(){
					log.info(g.toJson(lists6));
					new signTimer().signTest(lists6);
				}
			};
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
			t6.start();
		}
	}
}
