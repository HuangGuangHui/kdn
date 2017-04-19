package com.hgh.receive;

import java.sql.Timestamp;
import java.util.List;

public class Receive {
	private int Count;
	private List<Data> Data;
	private String EBusinessID;
	private Timestamp PushTime;
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
	@Override
	public String toString() {
		return "Receive [Count=" + Count + ", Data=" + Data + ", EBusinessID=" + EBusinessID + ", PushTime=" + PushTime
				+ "]";
	}
	
}
