package com.ssafy.api.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.api.model.ComPropDto;
import com.ssafy.api.model.DongCodeLatlngDto;
import com.ssafy.api.model.MarketsInfoDto;

@Mapper
public interface ApiMapper {

	int insertMarketsInfo(List<MarketsInfoDto> list);

	List<String> getDongCodeBy5();

	void insertComProps(List<ComPropDto> cPropList);



}
