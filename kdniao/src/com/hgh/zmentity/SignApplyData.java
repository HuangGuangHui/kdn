package com.hgh.zmentity;

import java.util.List;

public class SignApplyData {
	private String PushTime;
	private List<Signs> Signs;
	public String getPushTime() {
		return PushTime;
	}
	public void setPushTime(String pushTime) {
		PushTime = pushTime;
	}
	public List<Signs> getSigns() {
		return Signs;
	}
	public void setSigns(List<Signs> signs) {
		Signs = signs;
	}
	public SignApplyData(String pushTime, List<com.hgh.zmentity.Signs> signs) {
		super();
		PushTime = pushTime;
		Signs = signs;
	}
	public SignApplyData() {
		super();
	}
	
	
}
