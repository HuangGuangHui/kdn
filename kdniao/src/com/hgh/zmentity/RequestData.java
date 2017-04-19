package com.hgh.zmentity;

import java.sql.Timestamp;

public class RequestData {
	private String Id;
	private String LogisticCode;
	private String CodeType;
	private Timestamp RequestTime;
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getLogisticCode() {
		return LogisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}

	public String getCodeType() {
		return CodeType;
	}

	public void setCodeType(String codeType) {
		CodeType = codeType;
	}

	public Timestamp getRequestTime() {
		return RequestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		RequestTime = requestTime;
	}

	public RequestData() {
		super();
	}

	public RequestData(String id, String logisticCode, String codeType, Timestamp requestTime) {
		super();
		Id = id;
		LogisticCode = logisticCode;
		CodeType = codeType;
		RequestTime = requestTime;
	}

	
}
