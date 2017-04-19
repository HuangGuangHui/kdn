package com.hgh.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hgh.receive.Receive;
import com.hgh.receive.ReceiveSql;
import com.hgh.receive.newdata;
import com.hgh.receive.newtace;
import com.hgh.receive.signData;
import com.hgh.servlet.LinkSql;
import com.hgh.zmentity.RequestData;
import com.hgh.zmentity.RequestSystem;

public class commonDao {
	Logger log = Logger.getLogger(this.getClass());
	private LinkSql ls;
	
	public commonDao() {
		ls=new LinkSql();
	}
 
	/*-----------------------------主要信息接收------------------------------------*/
	
	public void recevieAdd(Receive re){
		for (int i = 0; i < re.getData().size(); i++) {
			String datasql ="insert into newdata values ('"+re.getData().get(i).getLogisticCode()+"','"+re.getData().get(i).getReason()+"'"
					+ ",'"+re.getData().get(i).getState()+"','"+re.getData().get(i).getSuccess()+"',to_timestamp('"+re.getPushTime()+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			ls.executeUpdate(datasql);
			for (int j = 0; j < re.getData().get(i).getTraces().size(); j++) {
				//取前半段收件信息
				String tracessql="insert into newtace values ('"+re.getData().get(i).getTraces().get(j).getAcceptStation()+"',to_timestamp('"+re.getData().get(i).getTraces().get(j).getAcceptTime()+"','yyyy-mm-dd hh24:mi:ss.ff')"
						+ ",'"+re.getData().get(i).getLogisticCode()+"')";
				ls.executeUpdate(tracessql);
			}
		}
	}
	
	
	/*-----------------------------newdata表操作------------------------------------*/
	
	/**
	 * 查询签收记录
	 * @return
	 */
	public List<newdata> selectDataReceive(){
		String sql ="select * from newdata where state = '3' and rownum <=1000 " ;
		ResultSet rs =  ls.executeQuery(sql);
		List<newdata> lists = new ArrayList<newdata>();
		try{
			while(rs.next()){
				newdata da = new newdata();
				da.setLogisticCode(rs.getString("logisticCold"));
				da.setReason(rs.getString("reasons"));
				da.setState(rs.getString("state"));
				da.setSuccess(rs.getString("success"));
				da.setPushtime(rs.getTimestamp("pushtime"));
				lists.add(da);
			}
		}catch(SQLException e){
			log.error("newdata表查询签收结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
			ls.close();
		}
		return lists;
	}
	
	public List<newdata> selectDataReceive2000(){
		String sql ="select * from (select a.*,rownum rn from newdata a where a.state='3' and rownum<=2100) where rn>1100" ;
		ResultSet rs =  ls.executeQuery(sql);
		List<newdata> lists = new ArrayList<newdata>();
		try{
			while(rs.next()){
				newdata da = new newdata();
				da.setLogisticCode(rs.getString("logisticCold"));
				da.setReason(rs.getString("reasons"));
				da.setState(rs.getString("state"));
				da.setSuccess(rs.getString("success"));
				da.setPushtime(rs.getTimestamp("pushtime"));
				lists.add(da);
			}
		}catch(SQLException e){
			log.error("newdata表查询签收结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
			ls.close();
		}
		return lists;
	}
	
	public List<newdata> selectDataReceive3000(){
		String sql ="select * from (select a.*,rownum rn from newdata a where a.state='3' and rownum<=3100) where rn>2100" ;
		ResultSet rs =  ls.executeQuery(sql);
		List<newdata> lists = new ArrayList<newdata>();
		try{
			while(rs.next()){
				newdata da = new newdata();
				da.setLogisticCode(rs.getString("logisticCold"));
				da.setReason(rs.getString("reasons"));
				da.setState(rs.getString("state"));
				da.setSuccess(rs.getString("success"));
				da.setPushtime(rs.getTimestamp("pushtime"));
				lists.add(da);
			}
		}catch(SQLException e){
			log.error("newdata表查询签收结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
			ls.close();
		}
		return lists;
	}
	
	public List<newdata> selectDataReceive4000(){
		String sql ="select * from (select a.*,rownum rn from newdata a where a.state='3' and rownum<=4100) where rn>3100" ;
		ResultSet rs =  ls.executeQuery(sql);
		List<newdata> lists = new ArrayList<newdata>();
		try{
			while(rs.next()){
				newdata da = new newdata();
				da.setLogisticCode(rs.getString("logisticCold"));
				da.setReason(rs.getString("reasons"));
				da.setState(rs.getString("state"));
				da.setSuccess(rs.getString("success"));
				da.setPushtime(rs.getTimestamp("pushtime"));
				lists.add(da);
			}
		}catch(SQLException e){
			log.error("newdata表查询签收结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
			ls.close();
		}
		return lists;
	}
	
	/**
	 * 通过单号查询tace表，返回对应单号轨迹的集合
	 * @param pageNum
	 * @return
	 */
	public List<newtace> selectTaceForPageNum(String pageNum){
		String sql ="select * from newtace where dataId ='"+pageNum+"' order by acceptTime desc" ;
		ResultSet rs =  ls.executeQuery(sql);
		List<newtace> lists = new ArrayList<newtace>();
		try{
			while(rs.next()){
				newtace t = new newtace();
				t.setAcceptStation(rs.getString("acceptStation")); 
				t.setAcceptTime(rs.getTimestamp("acceptTime"));
				t.setDataId(rs.getString("dataId"));
				lists.add(t);
			}
		}catch(SQLException e){
			log.error("newtace表通过单号查数据结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
			ls.close();
		}
		return lists;
	}
	
	/**
	 * 根据单号删除data对应数据
	 * @param num
	 */
	public void deleteDataforLogisticCold(String num){
		String sql = "delete from newdata where logisticCold = '"+num+"'";
		ls.executeUpdate(sql);
		ls.close();
	}
	
	
	/**
	 * 根据单号删除tace对应数据
	 * @param num
	 */
	public void deleteTaceforDataId(String num){
		String sql = "delete from newtace where dataId = '"+num+"'";
		ls.executeUpdate(sql);
		ls.close();
	}
	
	/*-----------------------------signdata表操作------------------------------------*/
	
	/**
	 * 通过单号查询签收表中是否有这条记录
	 * @param id
	 * @return
	 */
	public signData  selectsignDataforId(String id){
		String sql ="select * from signData where id = '"+id+"'";
		ResultSet rs =  ls.executeQuery(sql);
		signData s = new signData();
		try{
			if(rs.next()){
				s.setId(rs.getString("id"));
				s.setLastSite(rs.getString("lastSite"));
				s.setAcceptTime(rs.getTimestamp("acceptTime"));
				s.setCreateTime(rs.getTimestamp("createTime"));
			}
		}catch(SQLException e){
			log.error("signData表通过单号查数据结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
			ls.close();
		}
		return s;
	}
	
	/**
	 * 将提取出来的签收数据进行保存
	 * @param s
	 */
	public void insertsignData(signData s){
		String sql = "insert into signData values('"+s.getId()+"','"+s.getLastSite()+"',to_timestamp('"+s.getAcceptTime()+"','yyyy-mm-dd hh24:mi:ss.ff'),to_timestamp('"+s.getCreateTime()+"','yyyy-mm-dd hh24:mi:ss.ff'))";
		ls.executeUpdate(sql);
		ls.close();
	}
	
	/**
	 * 查询前10条签收数据
	 * @return
	 */
	public List<signData> selectsignDataAll(){
		String sql1 ="select * from signdata where ROWNUM <= '50' ";
		ResultSet rs =  ls.executeQuery(sql1);
		List<signData> lists = new ArrayList<signData>();		
		try{
			while(rs.next()){
				signData s = new signData();
					s.setId(rs.getString("id"));
					s.setLastSite(rs.getString("lastSite"));
					s.setAcceptTime(rs.getTimestamp("acceptTime"));
					s.setCreateTime(rs.getTimestamp("createTime"));
				lists.add(s);
			}
		}catch(SQLException e){
			log.error("signData表查前10条数据结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
		}
		return lists;
	}
	
	/**
	 * 删除签收表中已传到哲盟的数据
	 * @param id
	 */
	public void deleteSignData(String id){
		String sql ="delete from signdata where id = '"+id+"'";
		ls.executeUpdate(sql);
		ls.close();
	}
	
	/**
	 * 添加数据到历史表中
	 * @param s
	 */
	public void insertHistorySignData(signData s){
		String sql = "insert into historysignData values('"+s.getId()+"','"+s.getLastSite()+"',to_timestamp('"+s.getAcceptTime()+"','yyyy-mm-dd hh24:mi:ss.ff'),to_timestamp('"+s.getCreateTime()+"','yyyy-mm-dd hh24:mi:ss.ff'))";
		ls.executeUpdate(sql);
		ls.close();
	}
	
	/*-----------------------------历史数据表操作------------------------------------*/
	
	/**
	 * 将data数据存入历史数据
	 * @param da
	 */
	public void insertHistorydata(newdata da){
		String sql = "insert into historydata values('"+da.getLogisticCode()+"','"+da.getReason()+"','"+da.getState()+"',"
				+ "'"+da.getSuccess()+"',to_timestamp('"+da.getPushtime()+"','yyyy-mm-dd hh24:mi:ss.ff'))"; 
		ls.executeUpdate(sql);
		ls.close();
	}
	
	/**
	 * 传入一个单号的整个物流轨迹集合，对应的存储
	 * @param list
	 */
	public void insertHistorytace(List<newtace> list){
		for (int i = 0; i < list.size(); i++) {
			String sql = "insert into historytace values('"+list.get(i).getAcceptStation()+"',to_timestamp('"+list.get(i).getAcceptTime()+"','yyyy-mm-dd hh24:mi:ss.ff'),'"+list.get(i).getDataId()+"')";
			ls.executeUpdate(sql);
		}
		ls.close();
	}
	
	/**
	 * 存单号到临时表
	 * @param rd
	 */
	public void insertSendZmLogistCode(RequestSystem reqs){
		for(int i = 0 ; i<reqs.getRequestData().size() ; i++){
			String sql = "insert into tmplogisticCode values('"+reqs.getRequestData().get(i).getId()+"','"+reqs.getRequestData().get(i).getLogisticCode()+"','"+reqs.getRequestData().get(i).getCodeType()+"',to_timestamp('"+reqs.getRequestTime()+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			ls.executeUpdate(sql);
		}
		ls.close();
	}
	
	public void insertTmpLogisticCode(RequestData rd){
		String sql = "insert into tmplogisticCode values('"+rd.getId()+"','"+rd.getLogisticCode()+"','"+rd.getCodeType()+"',to_timestamp('"+rd.getRequestTime()+"','yyyy-mm-dd hh24:mi:ss.ff'))";
		ls.executeUpdate(sql);
	}
	
	/**
	 * 查询临时单号表
	 * @return
	 */
	public List<RequestData> selectTmpLogisticCode(){
		String sql ="select * from tmplogisticCode where rownum <=100";
		ResultSet rs =  ls.executeQuery(sql);
		List<RequestData> list = new ArrayList<RequestData>();
		try{
			while(rs.next()){
				RequestData rd = new RequestData();
				rd.setId(rs.getString("ID"));
				rd.setLogisticCode(rs.getString("LOGISTICCODE"));
				rd.setCodeType(rs.getString("CODETYPE"));
				rd.setRequestTime(rs.getTimestamp("REQUESTTIME"));
				list.add(rd);
			}
		}catch(SQLException e){
			log.error("临时单号结果集错误");
			e.printStackTrace();
		}finally {
			ls.preClose();
		}
		return list;
	}
	
	/**
	 * 删除临时表中已上传数据
	 * @param id
	 */
	public void deletetmplogisticCode(String id){
		String sql = "delete from tmplogisticCode where LOGISTICCODE ='"+id+"' ";
		ls.executeUpdate(sql);
	}
	
	/**
	 * 将数据存入历史表中
	 * @param num
	 */
	public void insertHistoryLogisticCode(RequestData rd){
		String sql = "insert into historylogisticCode values('"+rd.getLogisticCode()+"',to_timestamp('"+rd.getRequestTime()+"','yyyy-mm-dd hh24:mi:ss.ff'),'"+rd.getCodeType()+"','"+rd.getId()+"')";
		ls.executeUpdate(sql);
	}
	
	
	
	
	
//	/**
//	 * 将错误单号将存入数据库
//	 * 由于修改了现将错误单号存入了临时表重新二次上传
//	 * @param ls
//	 */
//	public void insertSenderr(List<RequestData> list){
//		for (int i = 0; i < list.size(); i++) {
//			String sql ="insert into senderr values('"+list.get(i).getLogisticCode()+"','"+list.get(i).getId()+"','"+list.get(i).getCodeType()+"',to_timestamp('"+list.get(i).getRequestTime()+"','yyyy-mm-dd hh24:mi:ss.ff'))";
//			ls.executeUpdate(sql);
//			ls.close();
//		}
//	}
	
	/**
	 * 关闭操作减少连接
	 */
	public void close() {
		ls.close();
	}
}
