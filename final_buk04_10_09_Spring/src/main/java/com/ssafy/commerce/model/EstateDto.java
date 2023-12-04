package com.ssafy.commerce.model;

public class EstateDto {
	 private long id;
	 private String dealClass ;
	 private long dealAmount ;
	 private int floor ;
	 private double area ;
	 private String description ;
	 private String joinYear ;
	 private String joinMonth ;
	 private String joinDay ;
	 private String sido ;
	 private String gugun;
	 private String dong ;
	 private String jibun;
	 private String dongCode ;
	 private double lat;
	 private double lon;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDealClass() {
		return dealClass;
	}
	public void setDealClass(String dealClass) {
		this.dealClass = dealClass;
	}
	public long getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(long dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJoinYear() {
		return joinYear;
	}
	public void setJoinYear(String joinYear) {
		this.joinYear = joinYear;
	}
	public String getJoinMonth() {
		return joinMonth;
	}
	public void setJoinMonth(String joinMonth) {
		this.joinMonth = joinMonth;
	}
	public String getJoinDay() {
		return joinDay;
	}
	public void setJoinDay(String joinDay) {
		this.joinDay = joinDay;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
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
		return "EstateDto [id=" + id + ", dealClass=" + dealClass + ", dealAmount=" + dealAmount + ", floor=" + floor
				+ ", area=" + area + ", description=" + description + ", joinYear=" + joinYear + ", joinMonth="
				+ joinMonth + ", joinDay=" + joinDay + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong
				+ ", jibun=" + jibun + ", dongCode=" + dongCode + ", lat=" + lat + ", lon=" + lon + "]";
	}
	 
}
