package com.hgh.receive;

import java.util.List;

public class Data {
	private String CallBack;
	private String EBusinessID;
	private String LogisticCode;
	private String OrderCode;
	private String Reason;
	private String ShipperCode;
	private String State;
	private Boolean Success;
	private List<Traces> Traces;
	public String getCallBack() {
		return CallBack;
	}
	public void setCallBack(String callBack) {
		CallBack = callBack;
	}
	public String getEBusinessID() {
		return EBusinessID;
	}
	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}
	public String getLogisticCode() {
		return LogisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}
	public String getOrderCode() {
		return OrderCode;
	}
	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getShipperCode() {
		return ShipperCode;
	}
	public void setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public Boolean getSuccess() {
		return Success;
	}
	public void setSuccess(Boolean success) {
		Success = success;
	}
	public List<Traces> getTraces() {
		return Traces;
	}
	public void setTraces(List<Traces> traces) {
		Traces = traces;
	}
	@Override
	public String toString() {
		return "Data [CallBack=" + CallBack + ", EBusinessID=" + EBusinessID + ", LogisticCode=" + LogisticCode
				+ ", OrderCode=" + OrderCode + ", Reason=" + Reason + ", ShipperCode=" + ShipperCode + ", State="
				+ State + ", Success=" + Success + ", Traces=" + Traces + "]";
	}
	
}
