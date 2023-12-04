package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.member.model.MemberDto;

//@Controller
@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class BoardController {
	private static final long serialVersionUID = 1L;

	private int pgno;
	private String key;
	private String word;
	private String queryStrig;
	
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@GetMapping("/list")
	public ResponseEntity<?> listArticle(@RequestParam Map<String, String> map, HttpSession session) throws Exception {
		// 로그인한 유저를 확인. 로그인이 되어있어야 확인할 수 있는 서비스(공지사항)
		// 관리자 계정이면 공지사항 수정 가능
//		ModelAndView mav = new ModelAndView();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo"); 
//		if (memberDto != null) {
//			List<BoardDto> list = boardService.listArticle(map);
//			PageNavigation pageNavigation = boardService.makePageNavigation(map);
//			mav.addObject("articles", list);
//			mav.addObject("navigation", pageNavigation);
//			mav.addObject("pgno", map.get("pgno"));
//			mav.addObject("key", map.get("key"));
//			mav.addObject("word", map.get("word"));
			
//			if(memberDto.getUserId().equals("admin")) {
//				mav.addObject("isAdmin", true);
//			}else mav.addObject("isAdmin", false);
//			mav.setViewName("board/list");
//		}else {
//			mav.addObject("msg", "로그인 후 이용 가능한 서비스입니다.");
//			mav.setViewName("index");
//		}

		BoardListDto list = boardService.listArticle(map);
		if (list != null && list.getArticles().size() > 0) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(list);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	
	@GetMapping("/write")
	public ModelAndView write(HttpSession session) {
		// 공지사항 글쓰기는 관리자만 가능한 기능이므로 체크 필요
		ModelAndView mav = new ModelAndView();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null && memberDto.getUserId().equals("admin")) {
			mav.setViewName("board/write");
		}else if(memberDto == null) {
			mav.addObject("msg", "로그인 후 이용 가능한 서비스입니다.");
			mav.setViewName("index");
		}else {
			mav.addObject("msg", "권한이 없습니다.");
			mav.setViewName("article/list");
		}
		return mav;
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody Map<String, String> map, HttpSession session, RedirectAttributes redirectAttributes ) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		BoardDto boardDto = new BoardDto();
//		boardDto.setUserId(memberDto.getUserId());
//		boardDto.setSubject(map.get("subject"));
//		boardDto.setContent(map.get("content"));
//		boardService.writeArticle(boardDto);
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");
//		
//		return "redirect:list";
		
		// postman 시험용 memberDto. 시험을 위해 admin으로 고정.
		MemberDto memberDto = new MemberDto();
//		memberDto.setUserId("admin");
		
		BoardDto boardDto = new BoardDto();
//		boardDto.setUserId(memberDto.getUserId());
		boardDto.setUserId(map.get("userId"));
		boardDto.setSubject(map.get("subject"));
		boardDto.setContent(map.get("content"));
	
		boardService.writeArticle(boardDto);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(boardDto);
	}
	
//	@GetMapping("/view")
//	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model) throws Exception {
	@GetMapping("/view/{articleNo}")
	public  ResponseEntity<?> view(@PathVariable int articleNo, @RequestParam Map<String, String> map, Model model) throws Exception {

		BoardDto boardDto = boardService.getArticle(articleNo);
		boardService.updateHit(articleNo);
//		model.addAttribute("article", boardDto);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "board/view";
		if (boardDto != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(boardDto);
		} else {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		BoardDto boardDto = boardService.getArticle(articleNo);
		model.addAttribute("article", boardDto);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "board/modify";
	}
	
//	@PostMapping("/modify")
//	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
//		boardService.modifyArticle(boardDto);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/article/list";
		
		boardService.modifyArticle(boardDto);
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}

//	@GetMapping("/delete")
//	public String delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
	@DeleteMapping("/delete/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
//		boardService.deleteArticle(articleNo);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/article/list";
		
		boardService.deleteArticle(articleNo);
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
