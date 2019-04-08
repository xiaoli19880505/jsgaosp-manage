package com.britecloud.marketingcloud.model.api;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import com.britecloud.marketingcloud.utils.DateUtils;
import com.britecloud.marketingcloud.utils.SignatureHelper;

public class EventApi {
	private static String API_KEY ;
	private  static String API_SECRET ;
	private static final String HOST="http://127.0.0.1:10090";

	public static String getAPI_KEY() {
		return API_KEY;
	}

	public static void setAPI_KEY(String aPI_KEY) {
		API_KEY = aPI_KEY;
	}

	public static String getAPI_SECRET() {
		return API_SECRET;
	}

	public static void setAPI_SECRET(String aPI_SECRET) {
		API_SECRET = aPI_SECRET;
	}

	public static String getHost() {
		return HOST;
	}


	//    private static final String[] items = new String[] { "卡通组合装体温计", "88个睡前故事松鼠卷", "软头电子体温计", "多彩旋转画笔", "多彩防撞条1个/袋",
	//            "儿童训练餐具必备套装(2岁以上)6件/套", "12色可洗涂画彩笔", "保温分体不锈钢碗（企鹅）(2岁以上)300ml/盒", "洗头帽", "不断裂安全门卡", "卡通软头电子体温计(憨厚牛)",
	//            "高弹防撞台角(无痕型)"
	//
	//    };
	//    

	//    private static final double[] prices = new double[] { 12.3, 12.4, 2.3, 45.0, 22, 45, 78, 99.8, 66.8, 12.8, 34.5,
	//            23 };

	public static ApiResponse trackUpdateCart(String userId, String id, String jsonBody) {
		String urlApp = "/api/events/trackUpdateCart";
		String fullUrl = HOST + urlApp;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add(SignatureHelper.APIKEY_HEADER, API_KEY);
		headers.add(SignatureHelper.APISECRET_HEADER, API_SECRET);
		headers.add(SignatureHelper.TIMESTAMP_HEADER, "" + System.currentTimeMillis());
		UpdateCartRequest request = new UpdateCartRequest();
		request.setCreatedAt(DateUtils.curDateTimeStr());
		List<JSONObject> list = new LinkedList<>();
		if(jsonBody!=""){
			JSONObject jsonobj = JSON.parseObject(jsonBody);
			list.add(jsonobj);
		}
		request.setItems(list);
		request.setTranscationId(id);
		request.setUserId(userId);
		String json = JSONObject.toJSONString(request);
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		// RestTemplate rest = new RestTemplate();
		// reInitMessageConverter(rest);
		//
		// ResponseEntity<ApiResponse> response = rest
		ResponseEntity<ApiResponse> response=getRestTemplate().postForEntity(fullUrl, requestEntity, ApiResponse.class);
		return response.getBody();
		
	}
	
	
	 public static ApiResponse trackViewItem(String userId, String transactionId, String jsonBody) {
	        String urltrackApp = "/api/events/trackViewItem";
	        String fulltrackUrl = HOST + urltrackApp;
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        // headers.set("charset", "UTF-8");
	        headers.add(SignatureHelper.APIKEY_HEADER, API_KEY);
	        headers.add(SignatureHelper.APISECRET_HEADER, API_SECRET);
	        headers.add(SignatureHelper.TIMESTAMP_HEADER, "" + System.currentTimeMillis());
	        TrackViewItemRequest request = new TrackViewItemRequest();
	        request.setCreatedAt(DateUtils.curDateTimeStr());
	        List<JSONObject> list = new LinkedList<>();
	        if(jsonBody!=""){
				JSONObject jsonobj = JSON.parseObject(jsonBody);
				list.add(jsonobj);
			}
	        request.setItems(list);
	        request.setTranscationId(transactionId);
	        request.setUserId(userId);
	        request.setTotal(BigDecimal.valueOf(123456789));

	        String json = JSONObject.toJSONString(request);

	        // StringEntity stringEntity=new StringEntity(json,"UTF-8");
	        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
	        //
	        // RestTemplate rest = new RestTemplate();
	        // reInitMessageConverter(rest);
	        //
	        // ResponseEntity<ApiResponse> response = rest
	        ResponseEntity<ApiResponse> res= getRestTemplate().postForEntity(fulltrackUrl, requestEntity, ApiResponse.class);
	        return res.getBody();
	}
	
	
	public static ApiResponse trackUpdatePurchase(String userId, String id, String jsonBody) {
		String urlApp = "/api/events/trackPurchase";
		String fullUrl = HOST + urlApp;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add(SignatureHelper.APIKEY_HEADER, API_KEY);
		headers.add(SignatureHelper.APISECRET_HEADER, API_SECRET);
		headers.add(SignatureHelper.TIMESTAMP_HEADER, "" + System.currentTimeMillis());
		TrackPurchaseRequest request = new TrackPurchaseRequest();
		request.setCreatedAt(DateUtils.curDateTimeStr());
		List<JSONObject> list = new LinkedList<>();
		if(jsonBody!=""){
			JSONObject jsonobj = JSON.parseObject(jsonBody);
			list.add(jsonobj);
		}
		request.setItems(list);
		request.setTranscationId(id);
		request.setUserId(userId);
		String json = JSONObject.toJSONString(request);
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		// RestTemplate rest = new RestTemplate();
		// reInitMessageConverter(rest);
		//
		// ResponseEntity<ApiResponse> response = rest
		ResponseEntity<ApiResponse> response=getRestTemplate().postForEntity(fullUrl, requestEntity, ApiResponse.class);
		return response.getBody();
	}

	private static RestTemplate getRestTemplate() {// 手动添加
		List<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<Source>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		return new RestTemplate(messageConverters);
	}

}
