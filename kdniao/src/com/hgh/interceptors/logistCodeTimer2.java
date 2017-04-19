package com.hgh.interceptors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.hgh.comman.commanConnet2;
import com.hgh.dao.commonDao;
import com.hgh.receive.ReturnJson;
import com.hgh.receive.reqData;
import com.hgh.zmentity.RequestData;

import common.Logger;

public class logistCodeTimer2{

Logger log = Logger.getLogger(this.getClass());
	
	public boolean test1(List<RequestData> lists) {
		log.info("-------------进入传数据第2个方法------------");
		commonDao dao = new commonDao();
		Gson gson = new Gson();
		System.out.println(lists.size());
		if(lists.size()>0){
			List<RequestData> ls = new ArrayList<RequestData>();
			Date ds  = new Date();
			for (int i = 0; i <lists.size(); i++) {
				reqData reqd = new reqData("YD", lists.get(i).getLogisticCode()) ;
				String rjson = gson.toJson(reqd);
				String result;
				try {
					result = new commanConnet2().postForm("http://api.kdniao.cc/api/dist", rjson);
					ReturnJson retur = gson.fromJson(result, ReturnJson.class);
					if(retur.getSuccess().equals("false")){
						ls.add(lists.get(i));
					}else{
						dao.insertHistoryLogisticCode(lists.get(i));
						dao.deletetmplogisticCode(lists.get(i).getLogisticCode());
					}
				} catch (Exception e) {
					log.error("请求错误：请核对APPkey等重要信息");
					ls.add(lists.get(i));
				}
			}
			Date de  = new Date();
			if(ls.size()>0){
				log.info("t2失败的单号如下:");
				log.info(gson.toJson(ls));
				for(int i = 0 ; i < ls.size() ; i ++){
					dao.insertTmpLogisticCode(ls.get(i));
				}
			}
			log.info("t2共耗时"+(de.getTime()-ds.getTime())+"毫秒");
			log.info("t2共导入"+(lists.size())+"条数据,进入接口成功"+(lists.size()-ls.size())+"条，失败"+(ls.size())+"条");
		}
		dao.close();
		return true;
	}
	
}
