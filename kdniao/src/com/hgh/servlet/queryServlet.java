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
import com.hgh.dao.commonDao;
import com.hgh.receive.ReceiveSql;
import com.hgh.receive.Traces;
import com.hgh.receive.newdata;
import com.hgh.receive.newtace;
import com.hgh.receive.signData;

/**
 * Servlet implementation class queryServlet
 */
@WebServlet("/queryServlet")
public class queryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Logger log = Logger.getLogger(this.getClass());
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson g = new Gson();
		commonDao dao = new commonDao();
		List<newdata> dlist = dao.selectDataReceive();
		log.info("------------"+dlist.size()+"---------------");
		for (int i = 0; i < dlist.size(); i++) {
			List<newtace> tlist = dao.selectTaceForPageNum(dlist.get(i).getLogisticCode());
			int num=0;
			Boolean state = true;
			//取第一条数据的时间
			//循环判断第一条数据和第二条是否是同一条  --解决重复问题
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
							signData s = dao.selectsignDataforId(dlist.get(i).getLogisticCode());
							//生成签收数据，有签收数据的不在重新生成；
							if(s.getId()==null){
								signData ns = new signData(dlist.get(i).getLogisticCode(), zd, tlist.get(num).getAcceptTime(), new Timestamp(new Date().getTime()));
								dao.insertsignData(ns);
							}
							//生成签收数据后对该条数据进行删除和存入历史表中
							//先存data表再存tace表
							dao.insertHistorydata(dlist.get(i));
							dao.insertHistorytace(tlist);
							dao.deleteDataforLogisticCold(dlist.get(i).getLogisticCode());
							dao.deleteTaceforDataId(dlist.get(i).getLogisticCode());
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
		PrintWriter p =  resp.getWriter();
//		List<ReceiveSql> res = dao.recevieCount(50872, 50880);
//		log.info(g.toJson(res));
//		p.write(g.toJson(res));
		p.flush();
		p.close();
	}

}
