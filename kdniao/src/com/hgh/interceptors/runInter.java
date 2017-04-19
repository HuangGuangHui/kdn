package com.hgh.interceptors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.hgh.dao.commonDao;
import com.hgh.receive.Receive;
import com.hgh.servlet.LinkQueue;

public class runInter implements ServletContextListener{
	Logger log = Logger.getLogger(this.getClass());
	private static boolean IS_END=false;
	
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------------启动------------------");
		Thread t=new Thread("aaaa"){
			public void run() {
				while (true) {
					try {
						if (LinkQueue.getLinkQueue().empty()) {
							Thread.sleep(10);
							if(IS_END==false){
								log.error("----------为空开始------------");
							}
							IS_END=true;
						}else{
							if(IS_END==true){
								log.error("----------为空结束------------");
							}
							IS_END=false;
							com.hgh.servlet.LinkQueue.Node<Receive> d=LinkQueue.getLinkQueue().poll();
							commonDao dao = new commonDao();
							if (d!=null) {
								dao.recevieAdd(d.getE());
								dao.close();
								Thread.sleep(1);
							}else {
								Thread.sleep(10);
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		};
		t.start();
	}
	
}
