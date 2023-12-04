package com.ssafy.commerce.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.api.model.ComPropDto;
import com.ssafy.api.model.DongCodeLatlngDto;
import com.ssafy.commerce.model.DealCostAvgAndDongDto;
import com.ssafy.commerce.model.DealCostAvgDto;
import com.ssafy.commerce.model.EstateDto;
import com.ssafy.commerce.model.StoreDto;
import com.ssafy.commerce.model.SalesDto;
import com.ssafy.commerce.model.mapper.CommerceMapper;

@Service
public class CommerceServiceImpl implements CommerceService{
	
	CommerceMapper commerceMapper;
	
	public CommerceServiceImpl(CommerceMapper commerceMapper) {
		super();
		this.commerceMapper = commerceMapper;
	}

	@Override
	public List<StoreDto> getStoreList(Map<String, String> map) {
		return commerceMapper.getStoreList(map);
	}
	@Override
	public List<DongCodeLatlngDto> getDongsByCoords(Map<String, String> map) {
		return commerceMapper.getDongsByCoords(map);
	}


	@Override
	public DongCodeLatlngDto getDongsNearest(Map<String, String> map) {
		return commerceMapper.getDongsNearest(map);
	}

	@Override
	public EstateDto getEstate(String id) {
		return commerceMapper.getEstate(id);
	}

	@Override
	public List<EstateDto> getEstateList(Map<String, String> map) {
		return commerceMapper.getEstateList(map);
	}

	@Override
	public DealCostAvgDto getDealCostAvgByDong(Map<String, String> map) {
		List<ComPropDto> list = commerceMapper.getDealCostAvgByDong(map);
		
		if(list != null && list.size()>0) {
			int sum = 0;
			double costAvgPerAreaSum = 0;
			for(int i=0; i<list.size(); i++) {
				sum += list.get(i).getDealAmount();
				costAvgPerAreaSum += list.get(i).getDealAmount() / list.get(i).getBuildingArea();
			}
			
			double costAvg = sum / list.size();
			double costAvgPerArea = costAvgPerAreaSum / list.size();
			
			DealCostAvgDto dealCostAvgDto = new DealCostAvgDto(); 
			dealCostAvgDto.setDealAmountAvg(costAvg);
			dealCostAvgDto.setDealAmountPerArea(costAvgPerArea);
			
			return dealCostAvgDto;
		}else return null;		
	}

	@Override
	public List<DealCostAvgAndDongDto> getDealCostAvgList(Map<String, String> map) {
		List<DongCodeLatlngDto> dongList = getDongsByCoords(map);
		
		// dongList의 동마다 동별 평균 매매가+면적당매매가를 얻어옴
		// 그 결과를 리스트로 저장
		if(dongList != null && dongList.size() > 0) {
			List<DealCostAvgDto> calcCostList = commerceMapper.getDealCostAvgByDongList(dongList);
			if(calcCostList != null && calcCostList.size() > 0) {
				
				List<DealCostAvgAndDongDto> result = new ArrayList<DealCostAvgAndDongDto>();
				for(int i=0; i<calcCostList.size(); i++) {
					DealCostAvgDto costAvgDto = calcCostList.get(i);
					for(DongCodeLatlngDto dong : dongList) {
						
						System.out.println((dong.getDongCode().substring(0, 5)+
								dong.getDongName()));
						
						if ((dong.getDongCode().substring(0, 5)+
								dong.getDongName()).equals(
										costAvgDto.
										getRegionCodeAndDong()
										)) {
							DealCostAvgAndDongDto dealCostAvgAndDongDto = new DealCostAvgAndDongDto();
							dealCostAvgAndDongDto.setDealCostAvg(costAvgDto);
							dealCostAvgAndDongDto.setDongCodeLatlng(dong);
							result.add(dealCostAvgAndDongDto);
							break;
						}
					}			
				}
				
				return result;
			}
			
		}
		return null;
	}

	@Override
	public int writeEstate(Map<String, String> map) {
//		 double lat = map.get("lat");
//		 double lon = lonStart + Math.random() * lonRandomSize;
		DongCodeLatlngDto dongCodeLatlngDto = commerceMapper.getDongByAddress(map);
		System.out.println(dongCodeLatlngDto);
		map.put("dongCode", dongCodeLatlngDto.getDongCode());

		double latRandomSize = 0.020;
		double lonRandomSize = 0.020;

		 double lat = dongCodeLatlngDto.getLat() + (Math.random() * latRandomSize - (latRandomSize/2));
		 double lon = dongCodeLatlngDto.getLon() + (Math.random() * lonRandomSize - (lonRandomSize/2));
		
		 map.put("lat", String.valueOf(lat));
		 map.put("lon", String.valueOf(lon));
		
		return commerceMapper.writeEstate(map);
	}

	@Override
	public int writeEstateRandomly(int count) {
		List<EstateDto> list = new ArrayList<EstateDto>();
		double latStart = 37.5459;
		double lonStart = 126.9634;
		double latEnd = 37.5714;
		double lonEnd = 127.0230;
		
		double latRandomSize = latEnd - latStart;
		double lonRandomSize = lonEnd - lonStart;
		
		for(int i=0; i<count; i++) {
			 double lat = latStart + Math.random() * latRandomSize;
			 double lon = lonStart + Math.random() * lonRandomSize;
			 double area = (int)(Math.random() * 150) + 50;
			 long dealAmount = (long)(Math.random() * 400 + 500 * area);
			 int floor = (int)(Math.random() * 7) + 1;
			 String jibun = String.valueOf((int)(Math.random()*290) + 110);
			 // 매물 등록 시각은 쿼리문에서 현재 시각 등록
			 // 시군구는 현재 좌표와 가장 가까운 쿼리문을 호출
			 Map<String, String> params = new HashMap<String, String>();
			 params.put("lat", String.valueOf(lat));
			 params.put("lon", String.valueOf(lon));
			 DongCodeLatlngDto dongCodeLatlngDto = commerceMapper.getDongsNearest(params);
			 
			 EstateDto estate = new EstateDto();
			 estate.setDealClass("매매");
			 estate.setDealAmount(dealAmount);
			 estate.setArea(area);
			 estate.setFloor(floor);
			 estate.setSido(dongCodeLatlngDto.getSidoName());
			 estate.setGugun(dongCodeLatlngDto.getGugunName());
			 estate.setDong(dongCodeLatlngDto.getDongName());
			 estate.setJibun(jibun);
			 estate.setDongCode(dongCodeLatlngDto.getDongCode());
			 estate.setLat(lat);
			 estate.setLon(lon);
			 
			 list.add(estate);
		}
		
		return commerceMapper.writeEstateRandomly(list);
//		return 0;
	}

	@Override
	public List<EstateDto> getEstateListByAddress(Map<String, String> map) {
		return commerceMapper.getEstateListByAddress(map);
	}

	@Override
	public List<SalesDto> getSalesByDong(Map<String, String> map) {
		return commerceMapper.getSalesByDong(map);
	}
	
}
