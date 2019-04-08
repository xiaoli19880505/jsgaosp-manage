package com.britecloud.marketingcloud.api.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
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

	@ApiModelProperty(value="响应描述",required=true)
	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	@ApiModelProperty(value="附加的信息,Map<String,Object>")
	@JsonProperty(required=true)
	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
	
	@ApiModelProperty(value="响应码只能是：'Success','BadApiKey',' BadParams',' BadJsonBody',' GenericError',' DatabaseError",required=true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
