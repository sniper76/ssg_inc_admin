package com.shinsegae.admin.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class JsoupCrawlerExample {
	public static void main(String[] args) throws Exception {
		
		JsoupCrawlerExample jce = new JsoupCrawlerExample();
//		jce.crawler("000890", "보해양조");
		jce.crawler("068870", "LG생명과학");
	}

	// 크롤러
	public Map<String, String> crawler(String stockCd, String compNm) {

		Document doc = null; // Document에는 페이지의 전체 소스가 저장된다
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("STOCK_CD", stockCd);
		returnMap.put("COMP_NM", compNm);
		try {
			String url = String.format("http://comp.fnguide.com/SVO2/ASP/SVD_Main.asp?pGB=1&gicode=A%s&cID=&MenuYn=Y&ReportGB=&NewMenuID=11&stkGb=701", stockCd);

			doc = Jsoup.connect(url).get();
		} catch (IOException e) {

			e.printStackTrace();
		}

		Elements currPriceElem = doc.select("span#svdMainChartTxt11");
		Elements stockTotalElem = doc.select("div#svdMainGrid1 table tbody tr");// 발생주식수
		Elements stockCompElem = doc.select("div#svdMainGrid5 table tbody tr");// 자기주식(자사주)
		Elements moneyElem = doc.select("div#highlight_D_A table tbody tr");// 지배주주지분
		
//		System.out.println(currPriceElem);
//		System.out.println(stockTotalElem);
//		System.out.println(stockCompElem);
//		System.out.println(moneyElem);
//		System.out.println(roeElem);
		
		returnMap.put("현재가", replaceStr(currPriceElem.text(), ","));
		
		for (Element element : stockTotalElem) {
			
			Elements tds = element.children();
			for (Element elem : tds) {
				
				if(elem.text().trim().indexOf("발행주식수") > -1) {
					Element e = elem.nextElementSibling();
//					System.out.printf("total 발행주식수 text : %s%n", e.text());
					returnMap.put("발행주식수", replaceStr(getIssuedStocks(e.text()), ","));
					break;
				}
			}
		}
		returnMap.put("자기주식수", "0");
		for (Element element : stockCompElem) {
			
			Elements tds = element.children();
			for (Element elem : tds) {
				
				if(elem.text().trim().indexOf("자기주식") > -1) {
					Element e = elem.nextElementSibling();
					Element ee = e.nextElementSibling();
//					System.out.printf("total 자기주식수 text : %s%n", ee.text());
					returnMap.put("자기주식수", replaceStr(ee.text(), ","));
					break;
				}
			}
		}
		for (Element element : moneyElem) {

			Elements tds = element.children();
			for (Element elem : tds) {
				
				if("지배주주지분".equals(elem.text().trim())) {
					Element e = elem.nextElementSibling();
					Element ee = e.nextElementSibling();
					Element eee = ee.nextElementSibling();
//					System.out.printf("total 지배주주지분금액 text : %s%n", eee.text());
					returnMap.put("지배주주지분금액", makeMillionWon(replaceStr(eee.text(), ","), "00000000"));
					break;
				}
			}
		}
		returnMap.put("ROE", "0");
		for (Element element : moneyElem) {

			Elements tds = element.children();
			for (Element elem : tds) {
				
				if(elem.text().trim().indexOf("ROA(%)") > -1) {
					Element e = elem.nextElementSibling();
					Element ee = e.nextElementSibling();
					Element eee = ee.nextElementSibling();
					Element eeee = eee.nextElementSibling();
//					System.out.printf("total ROE text : %s%n", eeee.text());
					returnMap.put("ROE", makeRoe(eeee.text()));
					break;
				}
			}
		}
		
//		System.out.println(returnMap);
		return returnMap;
	}
	
	private String getIssuedStocks(String text) {
		try {
			if(text.indexOf("/") > -1) {
				return text.split("/")[0];
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	private String replaceStr(String text, String delimt) {
		return text.isEmpty() || "&nbsp;".equals(text) ? "0" : text.replaceAll(delimt, "");
	}
	
	private String makeMillionWon(String text, String won) {
		return new StringBuilder().append(text).append(won).toString();
	}
	
	private String makeRoe(String text) {
		return text.isEmpty() || "&nbsp;".equals(text) ? "0" : text;
	}

}
