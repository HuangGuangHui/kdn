package com.hgh.receive;

import java.sql.Timestamp;

public class newdata {

	private String LogisticCode;
	private String Reason;
	private String State;
	private String Success;
	private Timestamp pushtime;
	public String getLogisticCode() {
		return LogisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getSuccess() {
		return Success;
	}
	public void setSuccess(String success) {
		Success = success;
	}
	public Timestamp getPushtime() {
		return pushtime;
	}
	public void setPushtime(Timestamp pushtime) {
		this.pushtime = pushtime;
	}
	
	public newdata(String logisticCode, String reason, String state, String success, Timestamp pushtime) {
		super();
		LogisticCode = logisticCode;
		Reason = reason;
		State = state;
		Success = success;
		this.pushtime = pushtime;
	}
	
	public newdata() {
		super();
	}
	
	
}
