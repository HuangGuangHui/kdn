package com.hgh.receive;

import java.sql.Timestamp;

public class signData {

	private String id;
	private String lastSite;
	private Timestamp acceptTime;
	private Timestamp createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastSite() {
		return lastSite;
	}
	public void setLastSite(String lastSite) {
		this.lastSite = lastSite;
	}
	public Timestamp getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Timestamp acceptTime) {
		this.acceptTime = acceptTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public signData(String id, String lastSite, Timestamp acceptTime, Timestamp createTime) {
		super();
		this.id = id;
		this.lastSite = lastSite;
		this.acceptTime = acceptTime;
		this.createTime = createTime;
	}
	public signData() {
		super();
	}
	@Override
	public String toString() {
		return "signData [id=" + id + ", lastSite=" + lastSite + ", acceptTime=" + acceptTime + ", createTime="
				+ createTime + "]";
	}
	
	
}
