package com.hgh.receive;

public class PageNum {
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PageNum(String number) {
		super();
		this.number = number;
	}

	@Override
	public String toString() {
		return "PageNum [number=" + number + "]";
	}
	
}
