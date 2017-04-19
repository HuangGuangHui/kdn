package com.hgh.receive;

public class ReturnJson {

	private String EBusinessID;
	private String UpdateTime;
	private String Success;
	private String Reason;
	public String getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
	public String getSuccess() {
		return Success;
	}
	public void setSuccess(String success) {
		Success = success;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getEBusinessID() {
		return EBusinessID;
	}
	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}
	public ReturnJson(String eBusinessID, String updateTime, String success, String reason) {
		super();
		EBusinessID = eBusinessID;
		UpdateTime = updateTime;
		Success = success;
		Reason = reason;
	}
	
}
