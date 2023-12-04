package com.ssafy.api.model;

public class ComPropDto {
	private long dealAmount;
	private double buildingArea;
	private String buildingUse;
	private String buildYear;
	private String dealYear;
	private double plottage;
	private String dong;
	private String sigungu;
	private String landUse;
	private String dealMonth;
	private String buildingType;
	private String dealDay;
	private String regionalCode;
	public long getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(long dealAmount) {
		this.dealAmount = dealAmount;
	}
	public double getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(double buildingArea) {
		this.buildingArea = buildingArea;
	}
	public String getBuildingUse() {
		return buildingUse;
	}
	public void setBuildingUse(String buildingUse) {
		this.buildingUse = buildingUse;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public double getPlottage() {
		return plottage;
	}
	public void setPlottage(double plottage) {
		this.plottage = plottage;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getLandUse() {
		return landUse;
	}
	public void setLandUse(String landUse) {
		this.landUse = landUse;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getDealDay() {
		return dealDay;
	}
	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}
	public String getRegionalCode() {
		return regionalCode;
	}
	public void setRegionalCode(String regionalCode) {
		this.regionalCode = regionalCode;
	}
	@Override
	public String toString() {
		return "ComPropDto [dealAmount=" + dealAmount + ", buildingArea=" + buildingArea + ", buildingUse="
				+ buildingUse + ", buildYear=" + buildYear + ", dealYear=" + dealYear + ", plottage=" + plottage + ", dong=" + dong
				+ ", sigungu=" + sigungu + ", landUse=" + landUse + ", dealMonth=" + dealMonth + ", buildingType="
				+ buildingType + ", dealDay=" + dealDay + ", regionalCode=" + regionalCode + "]";
	}
}
