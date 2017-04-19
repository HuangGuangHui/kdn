package com.hgh.receive;

import java.sql.Timestamp;
import java.util.List;

public class ReceiveSql {
	private int Count;
	private List<Data> Data;
	private String EBusinessID;
	private Timestamp PushTime;
	private String relevanceld;
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public List<Data> getData() {
		return Data;
	}
	public void setData(List<Data> data) {
		Data = data;
	}
	public String getEBusinessID() {
		return EBusinessID;
	}
	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}
	public Timestamp getPushTime() {
		return PushTime;
	}
	public void setPushTime(Timestamp pushTime) {
		PushTime = pushTime;
	}
	public String getRelevanceld() {
		return relevanceld;
	}
	public void setRelevanceld(String relevanceld) {
		this.relevanceld = relevanceld;
	}
	
	public ReceiveSql(int count, List<com.hgh.receive.Data> data, String eBusinessID, Timestamp pushTime,
			String relevanceld) {
		super();
		Count = count;
		Data = data;
		EBusinessID = eBusinessID;
		PushTime = pushTime;
		this.relevanceld = relevanceld;
	}
	public ReceiveSql() {
		super();
	}
	
	
}
