package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	//	private static MemberService memberService = new MemberServiceImpl();
	
	private MemberMapper memberMapper;

	//	private MemberServiceImpl() {
	//		memberDao = MemberDaoImpl.getMemberDao();
	//	}
	//	public static MemberService getMemberService() {
	//		return memberService;
	//	}
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	
	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws Exception {
		return memberMapper.loginMember(map);
	}

	@Override
	public List<MemberDto> memberList() throws SQLException {
		return memberMapper.memberList();
	}

	@Override
	public MemberDto getUserInfo(String userId) throws SQLException {
		return memberMapper.getUserInfo(userId);
	}

	@Override
	public int modify(MemberDto memberDto) throws SQLException {
		return memberMapper.modify(memberDto);
	}

	@Override
	public void deleteUser(String userId) throws SQLException {
		memberMapper.deleteUser(userId);
	}
	
	@Override
	public List<MemberDto> searchMemberList(Map<String, String> map) throws SQLException {
		return memberMapper.searchMemberList(map);
	}
	

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}

}
