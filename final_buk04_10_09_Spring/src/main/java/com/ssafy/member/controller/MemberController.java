package com.ssafy.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

//@Controller
//@RequestMapping("/user")
public class MemberController {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;
	private HashUtill hashUtill;
	public MemberController(MemberService memberService, HashUtill hashUtill) {
		super();
		this.memberService = memberService;
		this.hashUtill = hashUtill;
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String,String> map, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String userId = map.get("userid");
		String userPwd = map.get("userpwd");

		String pass = map.get("userpwd");
		String salt = map.get("userid");
		//		String hashedPassword = hashUtill.Hashing(pass, salt);
		String hashedPassword = userPwd;

		MemberDto memberDto = memberService.loginMember(map);

		if(memberDto != null) {
			//			session 설정
			session.setAttribute("userinfo", memberDto);

			//			cookie 설정
			String idsave = map.get("saveid");
			if("ok".equals(idsave)) { //아이디 저장을 체크 했다면.
				Cookie cookie = new Cookie("ssafy_id", userId);
				cookie.setPath(request.getContextPath());
				//				크롬의 경우 400일이 최대
				//				https://developer.chrome.com/blog/cookie-max-age-expires/
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
		} else
			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");

		return "index";
	}

	@GetMapping("/join")
	public String join() {
		return "user/register";
	}

	@PostMapping("/join") 
	public String joinMember(@RequestParam Map<String,String> map) throws Exception { 
		MemberDto memberDto = new MemberDto();
		memberDto.setUserName(map.get("username"));
		memberDto.setUserId(map.get("useridRegister"));
		memberDto.setEmailId(map.get("emailid"));
		memberDto.setEmailDomain(map.get("emaildomain"));
		memberDto.setUserPwd(map.get("userpwdRegister"));
		memberService.joinMember(memberDto); 
		return "/index"; 
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@GetMapping("/adminUserList")
	private ModelAndView adminUserList(@RequestParam Map<String,String> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberDto user = (MemberDto) session.getAttribute("userinfo");
		if(user.getUserId().equals("admin")) {
			try {
				List<MemberDto> users = memberService.memberList();
				mav.addObject("users",users);
				mav.setViewName("user/adminUserList");
			}catch (Exception e) {
				e.printStackTrace();
				//request.setAttribute("msg", "에러 발생!!!");
				mav.setViewName("/error/error.jsp");
			}

		}else {
			mav.addObject("msg", "잘못된 접근입니다.");
			mav.setViewName("/error/error.jsp");
		}
		return mav;
	}
}

