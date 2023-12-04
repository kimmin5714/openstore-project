package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.happyhouse.model.service.HouseServiceImpl;

//@WebServlet("/house")	// ${root}/house?~
public class OldHappyHouseController extends HttpServlet {
//	
////	HouseServiceImpl houseService = new HouseServiceImpl(); 
//	HouseServiceImpl houseService;
//
//	public OldHappyHouseController(HouseServiceImpl houseService) {
//		super();
//		this.houseService = houseService;
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		String mode = req.getParameter("mode");	// 동 별 아파트 검색 / 아파트 상세 조회
//		switch(mode) {
//		
//		}
//	}
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String mode = req.getParameter("mode");	// 동 별 아파트 검색 / 아파트 상세 조회
//		switch(mode) {
//		case "search-dong":	// 동 별 아파트 검색, 동만 받아오면 이름 같은 동들 다 받아오므로, 시도, 구군, 동 다 받아옴
//			System.out.println("요청 들어옴");
//			String sidoName = req.getParameter("sido").substring(11);
//			String gugunName = req.getParameter("gugun").substring(11);
//			String dongName = req.getParameter("dong").substring(11);
//			System.out.println(sidoName + " " + gugunName + " " + dongName);
//			Set<HouseInfoDto> houseInfoDtoList = null;
//			try {
//				houseInfoDtoList = houseService.searchByStringAddress(sidoName, gugunName, dongName);
//				req.setAttribute("houseList", houseInfoDtoList);
//				RequestDispatcher dispatcher = req.getRequestDispatcher("/apart.jsp");
//				dispatcher.forward(req, resp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println(houseInfoDtoList);	// 확인용
//			break;
//		case "search-apt":	// 아파트 상세 조회
//			Long aptCode = Long.parseLong(req.getParameter("aptCode"));
////			int dealYear = Integer.parseInt(req.getParameter("dealYear"));
////			int dealMonth = Integer.parseInt(req.getParameter("dealMonth"));
//			List<HouseDealDto> aptDealDtoList = null;	// 아파트 별 매매건 상세 조회 결과
//			try {
//				aptDealDtoList = houseService.searchAptDealByAptCode(aptCode);
//				req.setAttribute("houseDealList", aptDealDtoList);
//				RequestDispatcher dispatcher = req.getRequestDispatcher("/apart.jsp");
//				dispatcher.forward(req, resp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			System.out.println(aptDealDtoList);
//			break;
//		}
//	}
	
}
