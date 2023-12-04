package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {
	
//	HouseDaoImpl houseDaoImpl = new HouseDaoImpl();
	private HouseMapper houseMapper;
	

	public HouseServiceImpl(HouseMapper houseMapper) {
		super();
		this.houseMapper = houseMapper;
	}

	@Override
	public List<HouseInfoDto> searchByStringAddress(Map<String, String> map) throws SQLException {
		List<HouseInfoDto> houseInfoDtoList = null;
		
		String dongCode = houseMapper.getDongCodeByStringAddress(map);
		if(!dongCode.equals("")) {
			List<Long> aptCodeList = houseMapper.getAptCodeSetByDongCode(dongCode);
			if(aptCodeList != null && aptCodeList.size()>=1) {
				houseInfoDtoList = houseMapper.getHouseDealListByAptCode(aptCodeList);
			}
		}
		
		return houseInfoDtoList;
	}

	// < -------------- 아파트 상세 조회 -------------- >
	public List<HouseDealDto> searchAptDealByAptCode(Long aptCode) throws SQLException {
		List<HouseDealDto> aptDealDtoList = houseMapper.searchAptDealByAptCode(aptCode);
		
		return aptDealDtoList;
	}
	
	// <--------------- Functions for readability --------------->
	
}
