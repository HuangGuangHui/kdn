package com.hgh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hgh.zmentity.RequestSystem;
import com.hgh.zmentity.ReturnResult;

public class receiveZmLogisticCode extends HttpServlet{
	
	private Gson gson=new Gson();
	private Logger log = Logger.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		Enumeration<String>  list = req.getParameterNames();
		String str="";
		RequestSystem reqSystem = null;
		while (list.hasMoreElements()) {
			 str= (String) list.nextElement();
			 reqSystem =  gson.fromJson(str, RequestSystem.class);
			 LinkQueueForLogisticCode.getLinkQueue().add(reqSystem);
			log.info("参数名："+str);
		}
		PrintWriter fs = resp.getWriter();
		if(reqSystem==null){
			ReturnResult rr = new ReturnResult("1", "false", "请求格式错误");
			fs.write(gson.toJson(rr));
		}else if(reqSystem.getRequestId()==null||reqSystem.getRequestId().equals("")){
			ReturnResult rr = new ReturnResult("1", "false", "推送标识不能为空！！");
			fs.write(gson.toJson(rr));
		}else if(reqSystem.getRequestTime()==null||reqSystem.getRequestTime().equals("")){
			ReturnResult rr = new ReturnResult(reqSystem.getRequestId(), "false", "请求时间错误！！");
			fs.write(gson.toJson(rr));
		}else if(reqSystem.getRequestData()==null){
			ReturnResult rr = new ReturnResult(reqSystem.getRequestId(), "false", "RequestData 格式不对");
			fs.write(gson.toJson(rr));
		}else if(reqSystem.getRequestData().get(0).getLogisticCode().equals("")){
			ReturnResult rr = new ReturnResult(reqSystem.getRequestId(), "false", "LogisticCode 不能为空");
			fs.write(gson.toJson(rr));
		}else if(reqSystem.getRequestData().get(0).getCodeType().equals("")){
			ReturnResult rr = new ReturnResult(reqSystem.getRequestId(), "false", "CodeType 不能为空");
			fs.write(gson.toJson(rr));
		}else if(reqSystem.getRequestData().get(0).getId().equals("")){
			ReturnResult rr = new ReturnResult(reqSystem.getRequestId(), "false", "Id 不能为空");
			fs.write(gson.toJson(rr));
		}else{
			ReturnResult rr = new ReturnResult(reqSystem.getRequestId(), "true", "");
			fs.write(gson.toJson(rr));
		}
		
		
	}
}
