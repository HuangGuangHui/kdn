package com.hgh.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.hgh.pool.DBConnectionManager;

public class LinkSql {
	
	Logger log = Logger.getLogger(LinkSql.class);
	DBConnectionManager  connectionMan=null;
	String name="oracle";
	Connection  con=null;
	PreparedStatement pre=null;
	
	
	public LinkSql() {
		try {
			connectionMan=DBConnectionManager.getInstance();
			con=connectionMan.getConnection(name);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("--------初始化数据库连接出错---------");
		}	
	}
	
	
	public void executeUpdate(String sql){
		PreparedStatement upre=null;
		if (con!=null) {
			try {
				upre = con.prepareStatement(sql);
				upre.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("数据存储失败,错误sql为："+sql);
			}finally {
				try {
					if(upre!=null){
						upre.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public ResultSet executeQuery(String sql){
		ResultSet rs=null;
		if (con!=null) {
			try {
				pre = con.prepareStatement(sql);
				rs = pre.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("数据查询失败,错误查询sql为："+sql);
			}
		}
		return rs;
	}
	
	public void preClose(){
		if(pre!=null){
			try {
				pre.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(){
		if (con!=null) {
			connectionMan.freeConnection(name,con);
		}
	}
	
}
