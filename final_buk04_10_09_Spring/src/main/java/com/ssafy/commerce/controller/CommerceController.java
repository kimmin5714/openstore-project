package com.ssafy.commerce.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.model.ComPropDto;
import com.ssafy.api.model.DongCodeLatlngDto;
import com.ssafy.commerce.model.DealCostAvgAndDongDto;
import com.ssafy.commerce.model.DealCostAvgDto;
import com.ssafy.commerce.model.EstateDto;
import com.ssafy.commerce.model.StoreDto;
import com.ssafy.commerce.model.SalesDto;
import com.ssafy.commerce.model.service.CommerceService;
import com.ssafy.happyhouse.model.HouseInfoDto;

@RestController
@RequestMapping("/commerce")
@CrossOrigin("*")
public class CommerceController {
	
	private CommerceService commerceService;
	
	public CommerceController(CommerceService commerceService) {
		super();
		this.commerceService = commerceService;
	}

	@GetMapping("/storeList")
	public ResponseEntity<?> storeList(@RequestParam Map<String, String> map) throws SQLException{
		List<StoreDto> list = commerceService.getStoreList(map);
		
		if (list != null && list.size() > 0) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(list);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	
	@GetMapping("/dealCost")
	public ResponseEntity<?> dealCostAvgByDong(@RequestParam Map<String, String> map) throws SQLException{
		DealCostAvgDto dealCostAvgDto = commerceService.getDealCostAvgByDong(map);
		
		if (dealCostAvgDto != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(dealCostAvgDto);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	@GetMapping("/dealCostList")
	public ResponseEntity<?> dealCostAvgList(@RequestParam Map<String, String> map) throws SQLException{
		List<DealCostAvgAndDongDto> list = commerceService.getDealCostAvgList(map);
		
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(0));
		
		if (list != null && list.size()>0) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(list);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	@GetMapping("/sales")
	public ResponseEntity<?> getSalesByDong(@RequestParam Map<String, String> map) throws SQLException{
		List<SalesDto> list = commerceService.getSalesByDong(map);
		
		if (list != null && list.size()>0) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(list);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	@GetMapping("/estateList")
	public ResponseEntity<?> getEstateList(@RequestParam Map<String, String> map) throws SQLException{
		List<EstateDto> list = commerceService.getEstateList(map);
			
		if (list != null && list.size()>0) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(list);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	@GetMapping("/estateListByAddress")
	public ResponseEntity<?> getEstateListByAddress(@RequestParam Map<String, String> map) throws SQLException{
		System.out.println(map);
		List<EstateDto> list = commerceService.getEstateListByAddress(map);
			
		if (list != null && list.size()>0) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(list);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	@GetMapping("/estate/{id}")
	public ResponseEntity<?> getEstate(@PathVariable String id) throws SQLException{
		EstateDto estateDto = commerceService.getEstate(id);
			
		if (estateDto != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(estateDto);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	@PostMapping("/writeEstate")
	public ResponseEntity<?> writeEstate(@RequestBody Map<String, String> map) throws SQLException{
		int result = commerceService.writeEstate(map);
			
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}
	
	@GetMapping("/writeEstateRandomly/{count}")
	public ResponseEntity<?> writeEstateRandomly(@PathVariable int count) throws SQLException{
		int result = commerceService.writeEstateRandomly(count);
			
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(result);
	}
	
	
}
