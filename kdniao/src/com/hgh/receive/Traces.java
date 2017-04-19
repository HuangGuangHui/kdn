package com.hgh.receive;

import java.sql.Timestamp;

public class Traces {
	private String AcceptStation;
	private Timestamp AcceptTime;
	
	public String getAcceptStation() {
		return AcceptStation;
	}
	public void setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
	}
	public Timestamp getAcceptTime() {
		return AcceptTime;
	}
	public void setAcceptTime(Timestamp acceptTime) {
		AcceptTime = acceptTime;
	}
	public Traces(String acceptStation, Timestamp acceptTime) {
		super();
		AcceptStation = acceptStation;
		AcceptTime = acceptTime;
	}
	
	@Override
	public String toString() {
		return "Traces [AcceptStation=" + AcceptStation + ", AcceptTime=" + AcceptTime + "]";
	}
	public Traces() {
		super();
	}
	
	
}
