package com.ssafy.commerce.model;

public class DealCostAvgDto {
	private double dealAmountAvg;
	private double dealAmountPerArea;
	private String regionCodeAndDong;
	
	public double getDealAmountAvg() {
		return dealAmountAvg;
	}
	public void setDealAmountAvg(double dealAmountAvg) {
		this.dealAmountAvg = dealAmountAvg;
	}
	public double getDealAmountPerArea() {
		return dealAmountPerArea;
	}
	public void setDealAmountPerArea(double dealAmountPerArea) {
		this.dealAmountPerArea = dealAmountPerArea;
	}
	public String getRegionCodeAndDong() {
		return regionCodeAndDong;
	}
	public void setRegionCodeAndDong(String regionCodeAndDong) {
		this.regionCodeAndDong = regionCodeAndDong;
	}
	@Override
	public String toString() {
		return "DealCostAvgDto [dealAmountAvg=" + dealAmountAvg + ", dealAmountPerArea=" + dealAmountPerArea
				+ ", regionCodeAndDong=" + regionCodeAndDong + "]";
	}
	
	
	
}
