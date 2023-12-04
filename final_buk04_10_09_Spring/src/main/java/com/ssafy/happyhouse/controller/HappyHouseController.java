package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.service.HouseService;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HappyHouseController {
	
	HouseService houseService;
	
	public HappyHouseController(HouseService houseService) {
		super();
		this.houseService = houseService;
	}

	@GetMapping("/search-dong")
	public ResponseEntity<?> searchDong(@RequestParam Map<String, String> map) throws SQLException{
		map.put("sido", map.get("sido").substring(11));
			map.put("gugun", map.get("gugun").substring(11));
			map.put("dong", map.get("dong").substring(11));
		List<HouseInfoDto> houseInfoDtoList = houseService.searchByStringAddress(map);
		
		if(houseInfoDtoList != null && houseInfoDtoList.size()>=1) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(houseInfoDtoList);
		}else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	
	@GetMapping("/search-apt")
	public ResponseEntity<?> searchApt(@RequestParam Map<String, String> map) throws SQLException{

		Long aptCode = Long.parseLong(map.get("aptCode"));
		
		List<HouseDealDto>  aptDealDtoList = houseService.searchAptDealByAptCode(aptCode);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(aptDealDtoList);
	}
	
}
