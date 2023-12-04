package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(Map<String, String> map) throws Exception;
	List<MemberDto> memberList() throws SQLException;
	void deleteUser(String userId) throws SQLException;
	MemberDto getUserInfo(String userId) throws SQLException;
	int modify(MemberDto memberDto) throws SQLException;
	List<MemberDto> searchMemberList(Map<String, String> map) throws SQLException;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	MemberDto userInfo(String userId) throws Exception;
	
}
