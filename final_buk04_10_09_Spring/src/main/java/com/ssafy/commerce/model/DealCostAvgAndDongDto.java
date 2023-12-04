package com.ssafy.commerce.model;

import com.ssafy.api.model.DongCodeLatlngDto;

public class DealCostAvgAndDongDto {
	private DealCostAvgDto dealCostAvg;
	private DongCodeLatlngDto dongCodeLatlng;
	public DealCostAvgDto getDealCostAvg() {
		return dealCostAvg;
	}
	public void setDealCostAvg(DealCostAvgDto dealCostAvg) {
		this.dealCostAvg = dealCostAvg;
	}
	public DongCodeLatlngDto getDongCodeLatlng() {
		return dongCodeLatlng;
	}
	public void setDongCodeLatlng(DongCodeLatlngDto dongCodeLatlng) {
		this.dongCodeLatlng = dongCodeLatlng;
	}
	@Override
	public String toString() {
		return "DealCostAvgAndDongDto [dealCostAvg=" + dealCostAvg + ", dongCodeLatlng=" + dongCodeLatlng + "]";
	} 
}
