package com.hgh.interceptors;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hgh.dao.commonDao;
import com.hgh.receive.newdata;
import com.hgh.receive.newtace;
import com.hgh.receive.signData;

public class signTimer{

	Logger log = Logger.getLogger(this.getClass());
	
	public void signTest(List<newdata> dlist) {
		log.info("-------------------进入签收提取----------------");
		try {
//			log.info("------------"+dlist.size()+"---------------");
//			Date date = new Date();
			for (int i = 0; i < dlist.size(); i++) {
				List<newtace> tlist = new commonDao().selectTaceForPageNum(dlist.get(i).getLogisticCode());
				int num=0;
				Boolean state = true;
				//取第一条数据的时间
				//循环判断第一条数据和第二条是否是同一条  --解决重复问题
				if(tlist.size()>0){
					while(state){
						//判断最后一条是否为签收数据
						int indexof = tlist.get(num).getAcceptStation().indexOf("签收");
						if(indexof>-1){
							if(tlist.size()>(num+1)){
								//解决重复数据问题
								if(tlist.get(num).getAcceptTime().equals(tlist.get(num+1).getAcceptTime())){
									num++;
								}else{
									//对取到的数据进行分析,取站点
									int sindex = tlist.get(num+1).getAcceptStation().indexOf("到达：")+3;
									int eindex = tlist.get(num+1).getAcceptStation().indexOf(" ");
									String zd="";
									if(eindex>-1){
										zd = tlist.get(num+1).getAcceptStation().substring(sindex, eindex);
									}else{
										zd = tlist.get(num+1).getAcceptStation().substring(sindex);
									}
									signData s = new commonDao().selectsignDataforId(dlist.get(i).getLogisticCode());
									//生成签收数据，有签收数据的不在重新生成；
									if(s.getId()==null){
										signData ns = new signData(dlist.get(i).getLogisticCode(), zd, tlist.get(num).getAcceptTime(), new Timestamp(new Date().getTime()));
										new commonDao().insertsignData(ns);
									}
									//生成签收数据后对该条数据进行删除和存入历史表中
									//先存data表再存tace表
									new commonDao().insertHistorydata(dlist.get(i));
									new commonDao().insertHistorytace(tlist);
									new commonDao().deleteDataforLogisticCold(dlist.get(i).getLogisticCode());
									new commonDao().deleteTaceforDataId(dlist.get(i).getLogisticCode());
									state = false;	
								}
							}else{
								state = false;	
							}
						}else{
							state = false;
						}
					}
				}
			}
//			log.info("结束共耗时："+(new Date().getTime()-date.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
