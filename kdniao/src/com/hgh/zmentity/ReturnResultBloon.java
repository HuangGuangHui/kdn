package com.hgh.zmentity;

public class ReturnResultBloon {
	private String RequestId;
	private boolean Success;
	private String Reason;

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public ReturnResultBloon(String requestId, boolean success, String reason) {
		super();
		RequestId = requestId;
		Success = success;
		Reason = reason;
	}

	public ReturnResultBloon() {
		super();
	}

}
