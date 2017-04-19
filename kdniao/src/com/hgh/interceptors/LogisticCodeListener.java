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
import com.hgh.zmentity.RequestData;

public class LogisticCodeListener extends TimerTask{
	private Logger log=Logger.getLogger(this.getClass());
	private List<RequestData> lists = new ArrayList<RequestData>();
	private List<RequestData> lists1 = new ArrayList<RequestData>();
	private List<RequestData> lists2 = new ArrayList<RequestData>();
	private List<RequestData> lists3 = new ArrayList<RequestData>();
	private List<RequestData> lists4 = new ArrayList<RequestData>();
	private List<RequestData> lists5 = new ArrayList<RequestData>();
	private List<RequestData> lists6 = new ArrayList<RequestData>();
	Gson g = new Gson();
	@Override
	public void run() {
		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
		log.info("----------启动单号上传监听器----------");
		log.info("进入单号上传定时器");
		commonDao dao = new commonDao();
		lists = dao.selectTmpLogisticCode();
		dao.close();
		log.info(lists.size());
		int index = lists.size()/5;
		if(index>=1){
			lists1=lists.subList(0, index);
			lists2=lists.subList(index, index*2);
			lists3=lists.subList(index*2, index*3);
			lists4=lists.subList(index*3, index*4);
			lists5=lists.subList(index*4, index*5);
		}
		lists6=lists.subList(index*5, lists.size());
		Thread t1=new Thread("a"){
			public void run(){
				log.info(g.toJson(lists1));
				new logistCodeTimer1().test1(lists1);
			}
		};
		Thread t2=new Thread("b"){
			public void run(){
				log.info(g.toJson(lists2));
				new logistCodeTimer2().test1(lists2);
			}
		};
		Thread t3=new Thread("c"){
			public void run(){
				log.info(g.toJson(lists3));
				new logistCodeTimer3().test1(lists3);
			}
		};
		Thread t4=new Thread("d"){
			public void run(){
				log.info(g.toJson(lists4));
				new logistCodeTimer4().test1(lists4);
			}
		};
		Thread t5=new Thread("e"){
			public void run(){
				log.info(g.toJson(lists5));
				new logistCodeTimer5().test1(lists5);
			}
		};
		Thread t6=new Thread("f"){
			public void run(){
				log.info(g.toJson(lists6));
				new logistCodeTimer6().test1(lists6);
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
