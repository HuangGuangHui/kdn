package com.hgh.interceptors;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.hgh.dao.commonDao;
import com.hgh.receive.signData;

import common.Logger;

public class sendSignZmTimer extends TimerTask{

	Logger log = Logger.getLogger(this.getClass());
	private List<signData> lists1 = new ArrayList<signData>();
	private List<signData> lists2 = new ArrayList<signData>();
	private List<signData> lists3 = new ArrayList<signData>();
	private List<signData> lists4 = new ArrayList<signData>();
	private List<signData> lists5 = new ArrayList<signData>();
	private List<signData> lists6 = new ArrayList<signData>();
	
	@Override
	public void run() {
		log.info("-------------签收数据推送接口------------");
		commonDao dao = new commonDao();
		Gson gson = new Gson();
		List<signData> lists = dao.selectsignDataAll();
		dao.close();
		int index = lists.size()/5;
		if(index>=1){
			lists1=lists.subList(0, index);
			lists2=lists.subList(index, index*2);
			lists3=lists.subList(index*2, index*3);
			lists4=lists.subList(index*3, index*4);
			lists5=lists.subList(index*4, index*5);
		}
		Thread t1=new Thread("az"){
			public void run(){
				log.info(gson.toJson(lists1));
				new sendSignZm().run(lists1);
			}
		};		
		Thread t2=new Thread("bz"){
			public void run(){
				log.info(gson.toJson(lists2));
				new sendSignZm().run(lists2);
			}
		};	
		Thread t3=new Thread("cz"){
			public void run(){
				log.info(gson.toJson(lists3));
				new sendSignZm().run(lists3);
			}
		};	
		Thread t4=new Thread("dz"){
			public void run(){
				log.info(gson.toJson(lists4));
				new sendSignZm().run(lists4);
			}
		};	
		Thread t5=new Thread("ez"){
			public void run(){
				log.info(gson.toJson(lists5));
				new sendSignZm().run(lists5);
			}
		};	
		Thread t6=new Thread("fz"){
			public void run(){
				log.info(gson.toJson(lists6));
				new sendSignZm().run(lists6);
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
