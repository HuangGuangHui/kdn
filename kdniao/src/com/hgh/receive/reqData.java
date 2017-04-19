package com.hgh.receive;

public class reqData {
	private String ShipperCode;
	private String LogisticCode;
	public String getShipperCode() {
		return ShipperCode;
	}
	public void setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
	}
	public String getLogisticCode() {
		return LogisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}
	public reqData(String shipperCode, String logisticCode) {
		super();
		ShipperCode = shipperCode;
		LogisticCode = logisticCode;
	}
	
}
