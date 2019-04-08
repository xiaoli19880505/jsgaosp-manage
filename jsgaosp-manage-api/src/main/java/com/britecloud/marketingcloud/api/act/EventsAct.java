package com.britecloud.marketingcloud.api.act;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.britecloud.marketingcloud.api.request.UpdateUserRequest;
import com.britecloud.marketingcloud.api.response.ApiResponse;
import com.britecloud.marketingcloud.utils.SignatureHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/events")
@Api(value = "Event")
public class EventsAct {

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ApiOperation(value = "update", httpMethod = "POST", notes = "", produces = "application/json", response = ApiResponse.class)
	@ResponseBody
	public ApiResponse update(
			@ApiParam(required = true, name = "apiKey", value = "接口调用凭证") @RequestHeader(SignatureHelper.APIKEY_HEADER) String apiKey,
			@ApiParam(required = true, name = "userUpdateRequest", value = "用户信息更新请求") @RequestBody UpdateUserRequest request) {
		return new ApiResponse();
	}

	@RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public String getUserInfo() {
		return "UserInfo";
	}
}
