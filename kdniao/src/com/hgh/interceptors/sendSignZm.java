package com.hgh.interceptors;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.hgh.comman.ConnetZmData;
import com.hgh.dao.commonDao;
import com.hgh.receive.signData;
import com.hgh.tools.NameOfDate;
import com.hgh.zmentity.ReturnResult;
import com.hgh.zmentity.ReturnResultBloon;
import com.hgh.zmentity.SignApplyData;
import com.hgh.zmentity.Signs;
import com.hgh.zmentity.sendSignData;

import common.Logger;

public class sendSignZm{

	Logger log = Logger.getLogger(this.getClass());
	
	public void run(List<signData> lists) {
		if(lists.size()>0){
			log.info("-------------签收数据推送接口------------");
			Gson gson = new Gson();
			log.info(gson.toJson(lists));
			List<Signs> signs = new ArrayList<Signs>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < lists.size(); i++) {
				Signs s = new Signs(lists.get(i).getId(),lists.get(i).getLastSite(),sdf.format(new Date(lists.get(0).getAcceptTime().getTime())));
				signs.add(s);
			}
			SignApplyData sad = new SignApplyData(sdf.format(new Date()), signs);
			sendSignData ssd = new sendSignData(NameOfDate.getNum(), gson.toJson(sad));
			try {
				String result = new ConnetZmData().postForm(ssd);
				ReturnResultBloon rr = gson.fromJson(result, ReturnResultBloon.class);
				if(rr.isSuccess()){
					for (int i = 0; i < lists.size(); i++) {
						new commonDao().insertHistorySignData(lists.get(i));
						new commonDao().deleteSignData(lists.get(i).getId());
					}
					log.info("对方成功接收"+ssd);
				}else if(rr.isSuccess()==false||!rr.isSuccess()){
					log.error("对方未接到数据，二次推送启动："+ssd);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("数据推送错误，错误数据为："+ssd);
			}
		}
	}
	
}
