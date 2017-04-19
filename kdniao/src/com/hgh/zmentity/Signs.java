package com.hgh.zmentity;


public class Signs {
	private String LogisticCode;
	private String SignSite;
	private String AcceptTime;
	public String getLogisticCode() {
		return LogisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}
	public String getSignSite() {
		return SignSite;
	}
	public void setSignSite(String signSite) {
		SignSite = signSite;
	}
	
	public String getAcceptTime() {
		return AcceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
	}
	
	
	public Signs(String logisticCode, String signSite, String acceptTime) {
		super();
		LogisticCode = logisticCode;
		SignSite = signSite;
		AcceptTime = acceptTime;
	}
	public Signs() {
		super();
	}
	
}
