package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.JWTUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class MemberRestController {

	private MemberService memberService;
	private JWTUtil jwtUtil;

	public MemberRestController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String userId = map.get("userid");
			String userPwd = map.get("userpwd");
			//String pass = map.get("userpwd");
			//String salt = map.get("userid");
			//String hashedPassword = hashUtill.Hashing(pass, salt);
			//String hashedPassword = userPwd;

			MemberDto memberDto = memberService.loginMember(map);


			if(memberDto != null) {
				//			session 설정
//				session.setAttribute("userinfo", memberDto);
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(memberDto.getUserId());
				

//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
				
				//			cookie 설정
				String idsave = map.get("saveid");
				
				if("true".equals(idsave)) { //아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setPath(request.getContextPath());
					//크롬의 경우 400일이 최대
					//https://developer.chrome.com/blog/cookie-max-age-expires/
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); //40년간 저장.
					response.addCookie(cookie);
				} else { //아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("ssafy_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
//			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/join")
	public String join() {
		return "user/register";
	}

	@PostMapping("/join") 
	public ResponseEntity<?> joinMember(@RequestBody Map<String,String> map) throws Exception { 
		try {
			MemberDto memberDto = new MemberDto();
			memberDto.setUserName(map.get("username"));
			memberDto.setUserId(map.get("useridRegister"));
			memberDto.setEmailId(map.get("emailid"));
			memberDto.setEmailDomain(map.get("emaildomain"));
			memberDto.setUserPwd(map.get("userpwdRegister"));
			memberService.joinMember(memberDto); 
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(memberDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	@GetMapping("/logout")
//	public ResponseEntity<?> logout(HttpSession session) {
//		session.invalidate();
//		return ResponseEntity
//				.status(HttpStatus.CREATED)
//				.body("로그아웃 성공");
//	}
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/adminUserList")
	private ResponseEntity<?> adminUserList(HttpSession session) {
		//ModelAndView mav = new ModelAndView();

		//session 검사
		//MemberDto user = (MemberDto) session.getAttribute("userinfo");
		MemberDto user = new MemberDto();
		user.setUserId("admin");
		user.setUserPwd("1234");

		if(user.getUserId().equals("admin")) {
			try {
				List<MemberDto> users = memberService.memberList();
				if(users!=null && users.size() > 0) {
					return ResponseEntity
							.status(HttpStatus.OK)
							.body(users);
				} else {
					return ResponseEntity
							.status(HttpStatus.NO_CONTENT)
							.build();
				}
				//				mav.addObject("users",users);
				//				mav.setViewName("user/adminUserList");
			}catch (Exception e) {
				return exceptionHandling(e);
				//request.setAttribute("msg", "에러 발생!!!");
				//mav.setViewName("/error/error.jsp");
			}
		}else {
			//			mav.addObject("msg", "잘못된 접근입니다.");
			//			mav.setViewName("/error/error.jsp");
			return new ResponseEntity<String>("관리자 계정이 아닙니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("mvModifyUserInfo")
	private ResponseEntity<?> mvModifyUserInfo(@RequestBody Map<String,String> map, HttpSession session) {		
		//MemberDto user = (MemberDto) session.getAttribute("userinfo");

		MemberDto user = new MemberDto();
		user.setUserId("admin");
		user.setUserPwd("1234");

		if(user == null) {
			//return "/index.jsp";
		}
		String userId =user.getUserId(); 

		if(userId.equals("admin")){
			try {
				String selectedUserId = map.get("userId");
				MemberDto memberDto = memberService.getUserInfo(selectedUserId);

				return ResponseEntity
						.status(HttpStatus.OK)
						.body("수정할 사용자 정보 불러오기 성공");
			} catch (SQLException e) {
				//				e.printStackTrace();
				//				request.setAttribute("msg", "잘못된 접근입니다.");
				return exceptionHandling(e);
			}
		} else {
			return new ResponseEntity<String>("관리자 계정이 아닙니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modify")
	private ResponseEntity<?> modify(@RequestBody Map<String,String> map, HttpSession session) {
		//MemberDto user = (MemberDto) session.getAttribute("userinfo");
		MemberDto user = new MemberDto();
		user.setUserId("admin");
		user.setUserPwd("1234");

		try { //관리자 계정이면 adminUserList페이지로, 아니면 index페이지로
			MemberDto memberDto = new MemberDto();

			memberDto.setUserId(map.get("userid"));
			memberDto.setUserName(map.get("username"));	
			memberDto.setUserPwd(map.get("userpwd"));		
			memberDto.setEmailId(map.get("emailid"));
			memberDto.setEmailDomain(map.get("emaildomain"));

			memberService.modify(memberDto);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(memberDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete")
	private ResponseEntity<?> deleteUser(@RequestBody Map<String,String> map, HttpSession session) {

		try { //관리자 계정이면 adminUserList페이지로, 아니면 index페이지로
			String userId = map.get("userId");
			memberService.deleteUser(userId);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("사용자 삭제 성공");
		}catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/searchMemberList")
	private ResponseEntity<?> searchMemberList(@RequestParam Map<String,String> map, HttpSession session) {
		//MemberDto user = (MemberDto) session.getAttribute("userinfo");
		MemberDto user = new MemberDto();
		user.setUserId("admin");
		user.setUserPwd("1234");

		try { //관리자 계정이면 adminUserList페이지로, 아니면 index페이지로
			List<MemberDto> users = memberService.searchMemberList(map);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(users);
		}catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userId);
				System.out.println(memberDto);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
