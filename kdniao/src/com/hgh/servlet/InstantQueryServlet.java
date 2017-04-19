package com.hgh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hgh.comman.InstantQuery;
import com.hgh.comman.commanConnet1;
import com.hgh.dao.commonDao;
import com.hgh.receive.InstantQueryEntity;
import com.hgh.receive.ReceiveSql;
import com.hgh.receive.Traces;
import com.hgh.receive.newdata;
import com.hgh.receive.newtace;
import com.hgh.receive.signData;

public class InstantQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Logger log = Logger.getLogger(this.getClass());
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson g = new Gson();
		InstantQueryEntity iqe = new InstantQueryEntity("", "YD","3951820485559");
		String json =g.toJson(iqe);
		try {
			String result = new InstantQuery().postForm("http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx", json);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
