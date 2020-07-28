package com.shinsegae.admin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DartApi {

	public static void main(String[] args) {

		try {
			// sendGet();

			DartApi p = new DartApi();

			File file = new File("C:/app/CORPCODE.xml");
//			List<Map<String, String>> lst = p.parseCompanyXmlByList(file);
			List<Map<String, String>> lst = p.parseCompanyXmlByDummy();

			JsoupCrawlerExample jce = new JsoupCrawlerExample();
			
			String rate = "5.55";
			
			for (Map<String, String> map : lst) {
				
				Thread.sleep(2000);
				
				map.putAll(jce.crawler(map.get("STOCK_CD"), map.get("COMP_NM")));
				
				String valueTotal = p.calculatorStock(map.get("지배주주지분금액"), map.get("ROE"), rate);
//				System.out.println(valueTotal);
				
				map.put("주식가치", valueTotal);
				map.put("적정가치", p.calculatorPrice(map.get("발행주식수"), map.get("자기주식수"), valueTotal));
								
				System.out.println(map);
			}
			
//			p.parseCompanyXml(file);
//			
//			p.printCompanyData("00123541");
//			
//			p.printStockData("00123541");
//			
//			p.printStockIncreaseData("00123541");

			// Map<String, String> params = new HashMap<String, String>();
			// params.put("crtfc_key", API_KEY);
			//
			// System.out.println(p.get(GET_URL_CORP, params));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final String USER_AGENT = "Mozila/5.0";
	private static final String API_KEY = "ae2986eda0920c9fe76c879d466093786db27d8a";
	private static final String GET_URL_CORP = "/corpCode.xml";
	private static final String GET_URL = "https://opendart.fss.or.kr/api";
	
	private String calculatorStock(String value1, String value2, String value3) {
		//=C14+C14*(D14-E14)/E14
		
		double d1 = Double.parseDouble(isEmptyDouble(value1));
		double d2 = Double.parseDouble(isEmptyDouble(value2));//roe
		double d3 = Double.parseDouble(isEmptyDouble(value3));
		
		double cal = d1+d1*(d2-d3)/d3;
//		System.out.println(d1);
//		System.out.println(d2);
//		System.out.println(d3);
//		System.out.println(cal);
		
		return Double.toString(cal);
	}
	
	private String calculatorPrice(String value1, String value2, String valueTotal) {
		//=F14/(G14-H14)
		
		double dTotal = Double.parseDouble(valueTotal);
		double d1 = Double.parseDouble(isEmptyDouble(value1));
		double d2 = Double.parseDouble(isEmptyDouble(value2));
		
		return Double.toString(dTotal/(d1-d2));
	}
	
	private String isEmptyDouble(String value) {
		return value == null || value.isEmpty() ? "0.0" : value;
	}
	
	/**
	 * 자기주식 취득현황
	 * @param companyCode
	 * @throws Exception
	 */
	private void printStockData(String companyCode) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(GET_URL);
		sb.append("/tesstkAcqsDspsSttus.json");
		sb.append("?crtfc_key=" + API_KEY);
		sb.append("&corp_code=" + companyCode);
		sb.append("&bsns_year=2019");
		sb.append("&reprt_code=11011");//사업보고서
		
		sendGetJson(sb.toString(), 2, companyCode);
	}
	
	/**
	 * 증자감자 현황
	 * @param companyCode
	 * @throws Exception
	 */
	private void printStockIncreaseData(String companyCode) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(GET_URL);
		sb.append("/irdsSttus.json");
		sb.append("?crtfc_key=" + API_KEY);
		sb.append("&corp_code=" + companyCode);
		sb.append("&bsns_year=2019");
		sb.append("&reprt_code=11011");//사업보고서
		
		sendGetJson(sb.toString(), 3, companyCode);
	}
	
	/**
	 * 재무재표 상세
	 * @param companyCode
	 * @throws Exception
	 */
	private void printCompanyData(String companyCode) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(GET_URL);
		sb.append("/fnlttSinglAcntAll.json");
		sb.append("?crtfc_key=" + API_KEY);
		sb.append("&corp_code=" + companyCode);
		sb.append("&bsns_year=2019");
		sb.append("&reprt_code=11011");
		sb.append("&fs_div=OFS");//CFS:연결재무제표, OFS:재무제표
		
		sendGetJson(sb.toString(), 1, companyCode);
	}

	private void parseCompanyXml(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(file);

		doc.getDocumentElement().normalize();

		// String rootName = doc.getDocumentElement().getNodeName(); //루트 엘리먼트의
		// 이름을 리턴 합니다.

		NodeList nList = doc.getElementsByTagName("list");
		
		int result = 0;
		
		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				if(!"".equals(eElement.getElementsByTagName("stock_code").item(0).getTextContent().trim())) {
					
					System.out.printf("stock : %s, code : %s, name : %s %n", eElement.getElementsByTagName("stock_code").item(0).getTextContent()
							, eElement.getElementsByTagName("corp_code").item(0).getTextContent()
							, eElement.getElementsByTagName("corp_name").item(0).getTextContent());
					result++;
				}
			}
		}
		System.out.println(result);
	}
	
	private List<Map<String, String>> parseCompanyXmlByDummy() throws Exception {
		
		List<Map<String, String>> lst = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("STOCK_CD", "000890");
		map.put("COMP_NM", "보해양조");
		lst.add(map);
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("STOCK_CD", "009200");
		map1.put("COMP_NM", "무림페이퍼");
		lst.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("STOCK_CD", "051910");
		map2.put("COMP_NM", "LG화학");
		lst.add(map2);
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("STOCK_CD", "005930");
		map3.put("COMP_NM", "삼성전자");
		lst.add(map3);
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("STOCK_CD", "003670");
		map4.put("COMP_NM", "포스코케미칼");
		lst.add(map4);
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("STOCK_CD", "247540");
		map5.put("COMP_NM", "에코프로비엠");
		lst.add(map5);
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("STOCK_CD", "036830");
		map6.put("COMP_NM", "솔브레인");
		lst.add(map6);
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("STOCK_CD", "002210");
		map7.put("COMP_NM", "동성제약");
		lst.add(map7);
		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("STOCK_CD", "011500");
		map8.put("COMP_NM", "한농화성");
		lst.add(map8);
		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("STOCK_CD", "020150");
		map9.put("COMP_NM", "일진머티리얼즈");
		lst.add(map9);
		Map<String, String> map10 = new HashMap<String, String>();
		map10.put("STOCK_CD", "054050");
		map10.put("COMP_NM", "농우바이오");
		lst.add(map10);
		return lst;
	}
	
	private List<Map<String, String>> parseCompanyXmlByList(File file) throws ParserConfigurationException, SAXException, IOException {
		
		List<Map<String, String>> lst = new ArrayList<Map<String, String>>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.parse(file);
		
		doc.getDocumentElement().normalize();
		
		// String rootName = doc.getDocumentElement().getNodeName(); //루트 엘리먼트의
		// 이름을 리턴 합니다.
		
		NodeList nList = doc.getElementsByTagName("list");
		
		int result = 0;
		
		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				if(!"".equals(eElement.getElementsByTagName("stock_code").item(0).getTextContent().trim())) {
					
					System.out.printf("stock : %s, code : %s, name : %s %n", eElement.getElementsByTagName("stock_code").item(0).getTextContent()
							, eElement.getElementsByTagName("corp_code").item(0).getTextContent()
							, eElement.getElementsByTagName("corp_name").item(0).getTextContent());
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("STOCK_CD", eElement.getElementsByTagName("stock_code").item(0).getTextContent().trim());
					map.put("COMP_NM", eElement.getElementsByTagName("corp_name").item(0).getTextContent().trim());
					
					result++;
					
					lst.add(map);
				}
			}
		}
		System.out.println(result);
		return lst;
	}

	public static void sendGet() throws ClientProtocolException, IOException {
		// http client 생성
		CloseableHttpClient httpClient = HttpClients.createDefault();

		StringBuilder sb = new StringBuilder();
		sb.append(GET_URL_CORP);
		sb.append("?crtfc_key=" + API_KEY);

		// get 메서드와 URL 설정
		HttpGet httpGet = new HttpGet(sb.toString());

		// agent 정보 설정
		httpGet.addHeader("User-Agent", USER_AGENT);
		httpGet.addHeader("Content-type", "application/zip");

		// get 요청
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		System.out.println("::GET Response Status::");

		// response의 status 코드 출력
		System.out.println(httpResponse.getStatusLine().getStatusCode());

		// opens input stream from the HTTP connection
		InputStream inputStream = httpResponse.getEntity().getContent();
		String saveFilePath = "C:/app/test.zip";

		// opens an output stream to save into file
		FileOutputStream outputStream = new FileOutputStream(saveFilePath);

		int bytesRead = -1;
		byte[] buffer = new byte[4096];
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		outputStream.close();
		inputStream.close();

		// Print result
		httpClient.close();
	}

	private void sendGetJson(String url, int caseInt, String companyCode) throws ClientProtocolException, IOException {

		// http client 생성
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// get 메서드와 URL 설정
		HttpGet httpGet = new HttpGet(url);

		// agent 정보 설정
		httpGet.addHeader("User-Agent", USER_AGENT);
		httpGet.addHeader("Content-type", "application/json");

		// get 요청
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		System.out.println("GET Response Status");
		System.out.println(httpResponse.getStatusLine().getStatusCode());
		String jsonStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

		System.out.println(jsonStr);
		
		if(jsonStr != null) {
			try {
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonStr);
	            JSONArray memberArray = (JSONArray) jsonObj.get("list");

	            switch (caseInt) {
				case 1:
					System.out.println("=====재무정보=====");
					for(int i=0 ; i<memberArray.size() ; i++){
						JSONObject tempObj = (JSONObject) memberArray.get(i);
						System.out.printf("재무재표명: %s, 당기명: %s, 당기금액: %s, 당기누적금액: %s, 전기명: %s, 전기금액: %s, 전기누적금액: %s %n"
								, tempObj.get("sj_nm")
								, tempObj.get("thstrm_nm")
								, tempObj.get("thstrm_amount")
								, tempObj.get("thstrm_add_amount")
								, tempObj.get("frmtrm_nm")
								, tempObj.get("frmtrm_amount")
								, tempObj.get("frmtrm_add_amount"));
					}
					
					break;
				case 2:
					System.out.println("=====주식정보=====");
					for(int i=0 ; i<memberArray.size() ; i++){
						JSONObject tempObj = (JSONObject) memberArray.get(i);
						System.out.printf("주식종류: %s, %s, 기초수량: %s, 기말수량: %s %n"
								, tempObj.get("stock_knd")
								, tempObj.get("acqs_mth1")
								, tempObj.get("bsis_qy")
								, tempObj.get("trmend_qy"));
					}
					
					break;
				case 3:
					System.out.println("=====주식 증자 감자 정보=====");
					for(int i=0 ; i<memberArray.size() ; i++){
						JSONObject tempObj = (JSONObject) memberArray.get(i);
						System.out.printf("법인명: %s, 발행 감소 형태: %s, 발행 감소 수량: %s, 발행 감소 주당 액면 가액: %s, 발행 감소 주당 가액: %s %n"
								, tempObj.get("corp_name")
								, tempObj.get("isu_dcrs_stle")
								, tempObj.get("isu_dcrs_qy")
								, tempObj.get("isu_dcrs_mstvdv_fval_amount")
								, tempObj.get("isu_dcrs_mstvdv_amount"));
					}
					
					break;

				default:
					break;
				}

	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		httpClient.close();
	}

	/**
	 * POST 요청
	 * 
	 * @param url
	 *            요청할 url
	 * @param params
	 *            파라메터
	 * @param encoding
	 *            파라메터 Encoding
	 * @return 서버 응답결과 문자열
	 */
	public String post(String url, Map params, String encoding) {
		HttpClient client = HttpClients.createDefault();

		try {
			HttpPost post = new HttpPost(url);
			System.out.println("POST : " + post.getURI());

			List<NameValuePair> paramList = convertParam(params);
			post.setEntity(new UrlEncodedFormEntity(paramList, encoding));

			ResponseHandler<String> rh = new BasicResponseHandler();

			return client.execute(post, rh);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "error";
	}

	public String post(String url, Map params) {
		return post(url, params, "UTF-8");
	}

	/**
	 * GET 요청 POST 와 동일
	 */
	public String get(String url, Map<String, String> params, String encoding) {
		HttpClient client = HttpClients.createDefault();

		try {
			List<NameValuePair> paramList = convertParam(params);
			HttpGet get = new HttpGet(url + "?" + URLEncodedUtils.format(paramList, encoding));
			System.out.println("GET : " + get.getURI());

			ResponseHandler<String> rh = new BasicResponseHandler();

			return client.execute(get, rh);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "error";
	}

	public String get(String url, Map<String, String> params) {
		return get(url, params, "UTF-8");
	}

	private List<NameValuePair> convertParam(Map<String, String> params) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		Iterator<String> keys = params.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			paramList.add(new BasicNameValuePair(key, params.get(key).toString()));
		}

		return paramList;
	}

}
