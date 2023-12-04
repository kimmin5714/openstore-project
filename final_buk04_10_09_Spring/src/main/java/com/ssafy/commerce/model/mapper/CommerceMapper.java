package com.ssafy.commerce.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.api.model.ComPropDto;
import com.ssafy.api.model.DongCodeLatlngDto;
import com.ssafy.commerce.model.DealCostAvgDto;
import com.ssafy.commerce.model.EstateDto;
import com.ssafy.commerce.model.StoreDto;
import com.ssafy.commerce.model.SalesDto;

@Mapper
public interface CommerceMapper {

	List<StoreDto> getStoreList(Map<String, String> map);


	EstateDto getEstate(String id);

	List<EstateDto> getEstateList(Map<String, String> map);

	List<ComPropDto> getDealCostAvgByDong(Map<String, String> map);

	List<DealCostAvgDto> getDealCostAvgByDongList(List<DongCodeLatlngDto> dongList);

	int writeEstate(Map<String, String> map);

	List<DongCodeLatlngDto> getDongsByCoords(Map<String, String> map);

	DongCodeLatlngDto getDongsNearest(Map<String, String> map);

	int writeEstateRandomly(List<EstateDto> list);

	DongCodeLatlngDto getDongByAddress(Map<String, String> params);

	List<EstateDto> getEstateListByAddress(Map<String, String> map);


	List<SalesDto> getSalesByDong(Map<String, String> map);
}
