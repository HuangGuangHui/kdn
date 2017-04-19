package com.hgh.zmentity;

import java.sql.Timestamp;
import java.util.List;

public class RequestSystem {

	private List<RequestData> RequestData;
	private String RequestId;
	private Timestamp RequestTime;
	
	public String getRequestId() {
		return RequestId;
	}
	public void setRequestId(String requestId) {
		RequestId = requestId;
	}
	public Timestamp getRequestTime() {
		return RequestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		RequestTime = requestTime;
	}
	public List<RequestData> getRequestData() {
		return RequestData;
	}
	public void setRequestData(List<RequestData> requestData) {
		RequestData = requestData;
	}
	public RequestSystem() {
		super();
	}
	public RequestSystem(String requestId, Timestamp requestTime, List<com.hgh.zmentity.RequestData> requestData) {
		super();
		RequestId = requestId;
		RequestTime = requestTime;
		RequestData = requestData;
	}
	
	
}
