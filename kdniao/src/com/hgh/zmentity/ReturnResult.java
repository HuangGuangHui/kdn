package com.hgh.zmentity;

public class ReturnResult {
	private String RequestId;
	private String Success;
	private String Reason;

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
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

	public ReturnResult(String requestId, String success, String reason) {
		super();
		RequestId = requestId;
		Success = success;
		Reason = reason;
	}

	public ReturnResult() {
		super();
	}

}
