package com.britecloud.marketingcloud.model.api;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;




public class ApiResponse {
	public final static String SUCCESS = "Success";
	public final static String BAD_API_KEY = "BadApiKey";
	public final static String BAD_PARAMS = "BadParams";
	public final static String BAD_JSON_BODY = "BadJsonBody";
	public final static String GENERIC_ERROR = "GenericError";
	public final static String DATABAS_EERROR = "DatabaseError";

	private String message;
	private String code;
	private Map params;
	
	public ApiResponse() {
	}
	
	public ApiResponse(String msg, String code) {
		super();
		this.message = msg;
		this.code = code;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	
	@JsonProperty(required=true)
	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
