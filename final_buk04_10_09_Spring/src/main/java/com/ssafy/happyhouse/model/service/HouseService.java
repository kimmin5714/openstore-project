package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;


public interface HouseService {
	
	List<HouseInfoDto> searchByStringAddress(Map<String, String> map) throws SQLException;
	
	List<HouseDealDto> searchAptDealByAptCode(Long aptCode) throws SQLException;

}
