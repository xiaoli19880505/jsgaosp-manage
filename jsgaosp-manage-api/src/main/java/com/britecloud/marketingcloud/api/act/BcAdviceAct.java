package com.britecloud.marketingcloud.api.act;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.britecloud.marketingcloud.api.utils.ResultUtil;
import com.britecloud.marketingcloud.common.ResponseResult;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.service.BcAdviceService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/api/advice")
@Api(value = "BcAdvice")
public class BcAdviceAct {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BcAdviceService bcAdviceService;
	
	@RequestMapping(value = "/list_advice", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult listAdvice(@RequestParam(required = true, value = "currentPage")Integer currentPage) throws Exception {
		Map params = new HashMap();
		params.put("page", currentPage);
		PageDataResult result = bcAdviceService.listAdvice(params);
		result.setPage(currentPage);
		return ResultUtil.success(result);
	}
}
