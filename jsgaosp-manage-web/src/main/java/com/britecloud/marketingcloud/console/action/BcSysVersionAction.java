package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.service.BcSysApproveService;
import com.britecloud.marketingcloud.service.BcSysVersionService;

@RestController
@RequestMapping("/version")
public class BcSysVersionAction {
	
	 @Autowired
	    private BcSysVersionService bcSysVersionService;

	@RequestMapping(value = "/list_versions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listVersions(Integer currentPage, String keyword) throws Exception {
		JSONObject jo = new JSONObject();
        keyword = HuStringUtils.nvl(keyword);
        Map params = new HashMap();
        params.put("keyword", keyword);
        params.put("page", currentPage);
        PageDataResult result = bcSysVersionService.listSysVersions(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }
	
	 @RequestMapping(method = RequestMethod.PUT)
	    @ResponseBody
	    public ResponseResult updateSysargs(BcSysApplicationEntity args){
	        if(args != null){
	        	bcSysVersionService.updateVersion(args);
	            return ResultUtil.success();
	        }
	        return ResultUtil.error("10001","更新失败！");
	    }
}
