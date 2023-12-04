package com.ssafy.api;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssafy.api.model.ComPropDto;
import com.ssafy.api.model.DongCodeLatlngDto;
import com.ssafy.api.model.MarketsInfoDto;
import com.ssafy.api.model.mapper.ApiMapper;


@Controller
@RequestMapping("/api")
public class XmlParser {
	
	private ApiMapper apiMapper;
	
	public XmlParser(ApiMapper apiMapper) {
		super();
		this.apiMapper = apiMapper;
	}

	@GetMapping("/cprop")
	private void commercialProperties() {
        //페이지 생략
        try {
        	
        	// DB에서 모든 법정동코드(5자리) 가져오기
        	List<String> dongCodeList = apiMapper.getDongCodeBy5();
        	
    		for(String dongCode : dongCodeList) {
    			System.out.println("===== 동 코드 : "+dongCode);
        		for(int month = 1; month<=12; month++) {
        			
        			String yearMonth = "2023";
        			if(month<10) {
        				yearMonth+="0"+String.valueOf(month);
        			}else {
        				yearMonth+=String.valueOf(month);
        			}
		        	// ======
		        	
		            String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcNrgTrade?serviceKey=7C%2BxWt7OqCU8ncUPvV4pgjVRC5dhQSNGhNaJln3ouoK4URf6jDkAunQIc7mCCnW3JtC5%2Bo7pjwWaQmh1FCpOUg%3D%3D&LAWD_CD="+dongCode+"&DEAL_YMD="+yearMonth;
		
		            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();        
		            Document doc = dBuilder.parse(url);
		
		            doc.getDocumentElement().normalize();
		
		            NodeList nList = doc.getElementsByTagName("item");
		
		            List<ComPropDto> cPropList = new ArrayList<ComPropDto>();
		            for(int tmp=0;tmp<nList.getLength();tmp++) {
		                Node nNode = nList.item(tmp);
		                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
		                    Element eElement = (Element) nNode;
		                    
		                    ComPropDto comPropDto = new ComPropDto(); 
		                    comPropDto.setDealAmount(getTagValue("거래금액",eElement)!=null ?
		                    		Integer.parseInt(getTagValue("거래금액",eElement).replaceAll(",", "")) : 0);
		                    comPropDto.setBuildingArea(getTagValue("건물면적",eElement)!=null ?
		                    		Double.parseDouble(getTagValue("건물면적",eElement)) : 0);
		                    comPropDto.setBuildingUse(getTagValue("건물주용도",eElement));
		                    comPropDto.setBuildYear(getTagValue("건축년도",eElement));
		                    comPropDto.setDealYear(getTagValue("년",eElement));
		                    comPropDto.setPlottage(
		                    		getTagValue("대지면적",eElement)!=null ?
		                    				Double.parseDouble(getTagValue("대지면적",eElement)) : 0);
		                    comPropDto.setDong(getTagValue("법정동",eElement));
		                    comPropDto.setSigungu(getTagValue("시군구",eElement));
		                    comPropDto.setLandUse(getTagValue("용도지역",eElement));
		                    comPropDto.setDealMonth(getTagValue("월",eElement));
		                    comPropDto.setBuildingType(getTagValue("유형",eElement));
		                    comPropDto.setDealDay(getTagValue("일",eElement));
		                    comPropDto.setRegionalCode(getTagValue("지역코드",eElement));
		                    
		                    cPropList.add(comPropDto);
		                }
		            }
		            if(cPropList.size()>0)
		            	apiMapper.insertComProps(cPropList);
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@GetMapping("/market")
	private void marketsInfo() {

        String encServiceKey = "7C%2BxWt7OqCU8ncUPvV4pgjVRC5dhQSNGhNaJln3ouoK4URf6jDkAunQIc7mCCnW3JtC5%2Bo7pjwWaQmh1FCpOUg%3D%3D";
//        String divId = "ctprvnCd";
        String divId = "signguCd";
        String numOfRows = "1000";		// 최대 1000
        // 서울에서 제주까지
//        String[] sidoCodes = {"11","26","27","28","29","30","31","41","42","43","44","45","46","47","48","50"};
//        String[] sidoCodes = {"42","43","44","45","46","47","48","50"};
//        String[] sidoCodes = {"50"};
//        String[] sidoCodes = {"11"};
        String[] sidoCodes = {"11140"};
		
        //페이지 생략
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            
            for(String sidoCode : sidoCodes) {
//    			Thread.sleep(10000);
            	System.out.println("==== 시도 코드 : "+sidoCode);
            	
            	String indsLclsCdList[] = {"P1","K1","Q1","G2","S2","I1","R1","I2"};
            	
            	for(String indsLclsCd : indsLclsCdList) {
//        			Thread.sleep(10000);
            		System.out.println("======== 대분류 코드 "+indsLclsCd);
	            	for(int page=1; ;page++) {
	            		if(page%5==0)
//	            			Thread.sleep(10000);
	            			System.out.println("============== "+page+"번째 페이지 저장 중");
	            		
	            		
		                String pageNo = String.valueOf(page);
			            String url = "https://apis.data.go.kr/B553077/api/open/sdsc2/storeListInDong?serviceKey="+encServiceKey+"&pageNo="+pageNo+"&numOfRows="+numOfRows+"&divId="+divId+"&key="+sidoCode+"&indsLclsCd="+indsLclsCd+"&indsMclsCd=&indsSclsCd=&type=xml";
			       
			            Document doc = dBuilder.parse(url);
			
			            // ===페이지 오버시 break 코드===
			            Element header = (Element) doc.getElementsByTagName("header").item(0);
			            if(header==null) continue;
			            String resultMsg = header.getElementsByTagName("resultMsg").item(0).getTextContent();
			            
			            if("NODATA_ERROR".equals(resultMsg)) {
		            		break;		// 마지막 페이지를 지났다면 현재 sidoCode에서 종료	
			            }
			            // ========================
			            
			            doc.getDocumentElement().normalize();
			            
			            NodeList nList = doc.getElementsByTagName("item");
			            List<MarketsInfoDto> list = new ArrayList<MarketsInfoDto>();
			            
			
			            for(int tmp=0;tmp<nList.getLength();tmp++) {
			                Node nNode = nList.item(tmp);
			                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			                    Element eElement = (Element) nNode;
			                    MarketsInfoDto marketsInfoDto = new MarketsInfoDto();
			
								marketsInfoDto.setBizesId(getTagValue("bizesId",eElement));
								marketsInfoDto.setBizesNm(getTagValue("bizesNm",eElement));
								marketsInfoDto.setBrchNm(getTagValue("brchNm",eElement));
								marketsInfoDto.setIndsLclsCd(getTagValue("indsLclsCd",eElement));
								marketsInfoDto.setIndsLclsNm(getTagValue("indsLclsNm",eElement));
								marketsInfoDto.setIndsMclsCd(getTagValue("indsMclsCd",eElement));
								marketsInfoDto.setIndsMclsNm(getTagValue("indsMclsNm",eElement));
								marketsInfoDto.setIndsSclsCd(getTagValue("indsSclsCd",eElement));
								marketsInfoDto.setIndsSclsNm(getTagValue("indsSclsNm",eElement));
								marketsInfoDto.setKsicCd(getTagValue("ksicCd",eElement));
								marketsInfoDto.setKsicNm(getTagValue("ksicNm",eElement));
								marketsInfoDto.setCtprvnCd(getTagValue("ctprvnCd",eElement));
								marketsInfoDto.setCtprvnNm(getTagValue("ctprvnNm",eElement));
								marketsInfoDto.setSignguCd(getTagValue("signguCd",eElement));
								marketsInfoDto.setSignguNm(getTagValue("signguNm",eElement));
								marketsInfoDto.setAdongCd(getTagValue("adongCd",eElement));
								marketsInfoDto.setAdongNm(getTagValue("adongNm",eElement));
								marketsInfoDto.setLdongCd(getTagValue("ldongCd",eElement));
								marketsInfoDto.setLdongNm(getTagValue("ldongNm",eElement));
								marketsInfoDto.setLnoCd(getTagValue("lnoCd",eElement));
								marketsInfoDto.setPlotSctCd(getTagValue("plotSctCd",eElement));
								marketsInfoDto.setPlotSctNm(getTagValue("plotSctNm",eElement));
								marketsInfoDto.setLnoMnno(getTagValue("lnoMnno",eElement));
								marketsInfoDto.setLnoSlno(getTagValue("lnoSlno",eElement));
								marketsInfoDto.setLnoAdr(getTagValue("lnoAdr",eElement));
								marketsInfoDto.setRdnmCd(getTagValue("rdnmCd",eElement));
								marketsInfoDto.setRdnm(getTagValue("rdnm",eElement));
								marketsInfoDto.setBldMnno(getTagValue("bldMnno",eElement));
								marketsInfoDto.setBldSlno(getTagValue("bldSlno",eElement));
								marketsInfoDto.setBldMngNo(getTagValue("bldMngNo",eElement));
								marketsInfoDto.setBldNm(getTagValue("bldNm",eElement));
								marketsInfoDto.setRdnmAdr(getTagValue("rdnmAdr",eElement));
								marketsInfoDto.setOldZipcd(getTagValue("oldZipcd",eElement));
								marketsInfoDto.setNewZipcd(getTagValue("newZipcd",eElement));
								marketsInfoDto.setDongNo(getTagValue("dongNo",eElement));
								marketsInfoDto.setFlrNo(getTagValue("flrNo",eElement));
								marketsInfoDto.setHoNo(getTagValue("hoNo",eElement));
								marketsInfoDto.setLon(Double.parseDouble(getTagValue("lon",eElement)));
								marketsInfoDto.setLat(Double.parseDouble(getTagValue("lat",eElement)));
			                
								
								list.add(marketsInfoDto);
			                }
			            }
			            
			            apiMapper.insertMarketsInfo(list);
	            	}
	            }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    private static String getTagValue(String string, Element eElement) {
    	Node parent = eElement.getElementsByTagName(string).item(0);
    	if(parent == null) return null;
    	
        NodeList nList = parent.getChildNodes();
        
        Node nValue = (Node)nList.item(0);
        if(nValue == null)
            return null;
        
        String result = nValue.getNodeValue().trim();
        if(result==null || result.length()==0) return null;
        else return result;
    }
}
