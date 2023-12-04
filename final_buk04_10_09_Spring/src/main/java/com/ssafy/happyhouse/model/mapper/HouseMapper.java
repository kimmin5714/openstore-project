package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;


@Mapper
public interface HouseMapper {
	
	// < -------------- 아파트 목록 조회 -------------- > 
	String getDongCodeByStringAddress(Map<String, String> map) throws SQLException;
	
	List<Long> getAptCodeSetByDongCode(String dongCode) throws SQLException;
	
	List<HouseInfoDto> getHouseDealListByAptCode(List<Long> aptCodeList) throws SQLException;
	
	// < -------------- 아파트 상세 조회 -------------- >
	List<HouseDealDto> searchAptDealByAptCode(Long aptCode) throws SQLException;
	
}
