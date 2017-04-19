package com.hgh.receive;

import java.util.List;

public class reqParameter {

	private String EBusinessID;
	
	private String PushTime;
	
	private int Count;

	private List<String> Data;

	public String getEBusinessID() {
		return EBusinessID;
	}

	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}

	public String getPushTime() {
		return PushTime;
	}

	public void setPushTime(String pushTime) {
		PushTime = pushTime;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public List<String> getData() {
		return Data;
	}

	public void setData(List<String> data) {
		Data = data;
	}

	public reqParameter(String eBusinessID, String pushTime, int count, List<String> data) {
		super();
		EBusinessID = eBusinessID;
		PushTime = pushTime;
		Count = count;
		Data = data;
	}


	
}
