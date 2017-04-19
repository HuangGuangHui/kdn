package com.hgh.receive;

import java.sql.Timestamp;

public class newtace {

	private String acceptStation;
	private Timestamp acceptTime;
	private String dataId;

	public String getAcceptStation() {
		return acceptStation;
	}

	public void setAcceptStation(String acceptStation) {
		this.acceptStation = acceptStation;
	}

	public Timestamp getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Timestamp acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public newtace(String acceptStation, Timestamp acceptTime, String dataId) {
		super();
		this.acceptStation = acceptStation;
		this.acceptTime = acceptTime;
		this.dataId = dataId;
	}

	public newtace() {
		super();
	}

}
