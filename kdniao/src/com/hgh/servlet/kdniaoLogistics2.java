package com.hgh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hgh.dao.commonDao;
import com.hgh.receive.Receive;
import com.hgh.receive.ReturnJson;

public class kdniaoLogistics2 extends HttpServlet{
	
	private Gson gson=new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Enumeration<String>  list = req.getParameterNames();
		Receive re = null;
		while (list.hasMoreElements()) {
			String str = (String) list.nextElement();
			String str1=req.getParameter(str);
			if(str.equals("RequestData")){
				re = gson.fromJson(str1, Receive.class);
				LinkQueue.getLinkQueue().add(re);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PrintWriter fs = resp.getWriter();
		ReturnJson returnjson;
//		commonDao dao = new commonDao();
		if(re!=null){
			 returnjson = new ReturnJson("1282148",sdf.format(new Date()), "true", "");
//			 dao.addCount("succ");
			 System.out.println(gson.toJson(returnjson));
		}else{
			 returnjson = new ReturnJson("1282148",sdf.format(new Date()), "false", "未能有效接收到数据");
//			 dao.addCount("false");
			 System.out.println(gson.toJson(returnjson));
		}
		fs.write(gson.toJson(returnjson));
	}
}
