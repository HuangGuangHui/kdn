package com.hgh.zmentity;


public class sendSignData {

	private String SendId;
	private String Signs;
	
	
	public String getSendId() {
		return SendId;
	}
	public void setSendId(String sendId) {
		SendId = sendId;
	}
	public String getSigns() {
		return Signs;
	}
	public void setSigns(String signs) {
		Signs = signs;
	}
	
	
	public sendSignData(String sendId, String signs) {
		super();
		SendId = sendId;
		Signs = signs;
	}
	public sendSignData() {
		super();
	}
	@Override
	public String toString() {
		return "sendSignData [SendId=" + SendId + ", Signs=" + Signs + "]";
	}
	
	
	
}
