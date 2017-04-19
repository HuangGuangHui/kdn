package com.hgh.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
import com.hgh.dao.commonDao;
import com.hgh.receive.ReturnJson;
import com.hgh.receive.reqData;
import com.hgh.tools.ExcelImport;
import com.hgh.zmentity.RequestData;

public class sendkdniao extends HttpServlet{
	 Logger log = Logger.getLogger(this.getClass());
	private Gson gson=new Gson();
	
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
	  					Date ds = new Date();
	  					commonDao dao = new commonDao();
	  					for (int i = 1; i < list.size(); i++) {
	  						RequestData rd = new RequestData(""+i, list.get(i)[0], list.get(i)[1],new Timestamp(new Date().getTime()));
	  						dao.insertTmpLogisticCode(rd);
	  						dao.close();
						}
	  					Date de  = new Date();
	  					log.info("共导入"+(list.size()-1)+"条数据,共耗时"+(de.getTime()-ds.getTime())+"毫秒");
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
