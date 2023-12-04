package com.ssafy.member.model.mapper;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;
	int joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(Map<String, String> map) throws SQLException;
	List<MemberDto> memberList() throws SQLException;
	void deleteUser(String userId) throws SQLException;
	MemberDto getUserInfo(String userId) throws SQLException;
	int modify(MemberDto memberDto)  throws SQLException;
	List<MemberDto> searchMemberList(Map<String, String> map) throws SQLException;
	void saveRefreshToken(Map<String, String> map);
	Object getRefreshToken(String userId);
	void deleteRefreshToken(Map<String, String> map);
	MemberDto userInfo(String userId);
}
