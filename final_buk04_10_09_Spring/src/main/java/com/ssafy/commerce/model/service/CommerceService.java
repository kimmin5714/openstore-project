package com.ssafy.commerce.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.api.model.ComPropDto;
import com.ssafy.api.model.DongCodeLatlngDto;
import com.ssafy.commerce.model.DealCostAvgAndDongDto;
import com.ssafy.commerce.model.DealCostAvgDto;
import com.ssafy.commerce.model.EstateDto;
import com.ssafy.commerce.model.StoreDto;
import com.ssafy.commerce.model.SalesDto;

public interface CommerceService {

	List<StoreDto> getStoreList(Map<String, String> map);

	DongCodeLatlngDto getDongsNearest(Map<String, String> map);
	
	List<DongCodeLatlngDto> getDongsByCoords(Map<String, String> map);

	EstateDto getEstate(String id);

	List<EstateDto> getEstateList(Map<String, String> map);

	DealCostAvgDto getDealCostAvgByDong(Map<String, String> map);

	List<DealCostAvgAndDongDto> getDealCostAvgList(Map<String, String> map);

	int writeEstate(Map<String, String> map);

	int writeEstateRandomly(int count);

	List<EstateDto> getEstateListByAddress(Map<String, String> map);

	List<SalesDto> getSalesByDong(Map<String, String> map);
}
