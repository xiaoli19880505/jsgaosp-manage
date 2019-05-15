package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.service.BcAdviceService;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/advice")
public class BcAdviceAction {
	
	@Autowired
	private BcAdviceService bcAdviceService;

	@RequestMapping(value = "/list_advices", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult listApplications(Integer currentPage, String orgNo,String keyword) throws Exception {
		Map params = new HashMap();
		params.put("page", currentPage);
		params.put("orgNo", orgNo);
		params.put("keyword", keyword);
		PageDataResult result = bcAdviceService.listAdvice(params);
		result.setPage(currentPage);

		return ResultUtil.success(result);
	}
}
