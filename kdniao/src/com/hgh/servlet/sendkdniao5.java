package com.hgh.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hgh.comman.commanConnet;
import com.hgh.comman.commanConnet5;
import com.hgh.dao.commonDao;
import com.hgh.receive.ReturnJson;
import com.hgh.receive.reqData;
import com.hgh.tools.ExcelImport;

public class sendkdniao5 extends HttpServlet{
	
	private Gson gson=new Gson();
	 Logger log = Logger.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory factory=new DiskFileItemFactory();
	  	ServletFileUpload upload=new ServletFileUpload(factory);
	  	String fileName=null;
	  	upload.setHeaderEncoding("UTF-8");
	  	String root=req.getSession().getServletContext().getRealPath("/")+"files\\";
	  	try{
	  		List items=upload.parseRequest(req);
	  		Iterator it=items.iterator();
	  		while(it.hasNext()){
	  			FileItem item=(FileItem)it.next();
	  			if(item.isFormField()){
	  				String name = item.getFieldName();
	  				String value = item.getString("UTF-8");
	  			}else{
	  				if(item.getName()!=null&&!"".equals(item.getName())){
	  					item.getInputStream();
	  					fileName=item.getName();
	  					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1,fileName.length());
	  					File file=new File(root+fileName);
	  					item.write(file);
	  					List<String[]> list=ExcelImport.getDataFromExcel(fileName, file);
	  					int num = 0;
	  					List<String> ls = new ArrayList<String>();
	  					Date ds = new Date();
	  					for (int i = 1; i < list.size(); i++) {
							reqData reqd = new reqData("YD", list.get(i)[0]) ;
							String rjson = gson.toJson(reqd);
							String result;
							try {
								result = new commanConnet5().postForm("http://api.kdniao.cc/api/dist", rjson);
								ReturnJson retur = gson.fromJson(result, ReturnJson.class);
								if(retur.getSuccess().equals("true")){
									num++;
								}else{
									ls.add(list.get(i)[0]);
								}
							} catch (Exception e) {
								log.error("请求错误：请核对APPkey等重要信息");
								ls.add(list.get(i)[0]);
							}
						}
	  					Date de  = new Date();
	  					if(list.size()-1-num>0){
	  						log.info("t5失败的单号如下:");
	  						log.info(gson.toJson(ls));
//	  						new commonDao().insertSenderr(ls);
	  					}
	  					log.info("t5共耗时"+(de.getTime()-ds.getTime())+"毫秒");
	  					log.info("t5共导入"+(list.size()-1)+"条数据,进入接口成功"+num+"条，失败"+(list.size()-num-1)+"条");
	  					file.delete();
	  				}else {
	  					log.error("错误");
					}
	  			}
	  		}
	  	}catch(Exception e){
	  		e.printStackTrace();
	  	}
	}
}
