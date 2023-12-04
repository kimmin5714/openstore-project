package com.ssafy.api.model;

public class DongCodeLatlngDto {
	private String dongCode;
	private long addressCount;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private double lat;
	private double lon;
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public long getAddressCount() {
		return addressCount;
	}
	public void setAddressCount(long addressCount) {
		this.addressCount = addressCount;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	@Override
	public String toString() {
		return "DongCodeLatlngDto [dongCode=" + dongCode + ", addressCount=" + addressCount + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", dongName=" + dongName + ", lat=" + lat + ", lon=" + lon + "]";
	}
	
}
