package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.BcSysApproveService;
import com.britecloud.marketingcloud.utils.SessionUtils;
import com.britecloud.marketingcloud.utils.StringUtils;

@RestController
@RequestMapping("/approves")
public class BcSysApproveAction {


    
    @Autowired
    private BcSysApproveService bcSysApproveService;

    
    @RequestMapping(value = "/list_approves", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult listApplications(Integer currentPage, String orgNo,String keyword) throws Exception {
		Map params = new HashMap();
		params.put("page", currentPage);
		params.put("orgNo", orgNo);
		params.put("keyword", keyword);
		PageDataResult result = bcSysApproveService.listSysApproves(params);
		result.setPage(currentPage);

		return ResultUtil.success(result);
	}
    
    @OperationLogAnn(value = "修改应用申报")
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseResult updateApplications(HttpServletRequest request, ApplicationEntity args) {
		BcUser user = SessionUtils.getCurrentUser(request);
		if (user == null) {
			return ResultUtil.error("10005", "未登录");
		}
		if (args != null) {
			// 更新当前；应用状态
			String infoId = args.getInfo_id();
			if ("01".equals(args.getApproval_status())) {
				// 下线之前的应用
				args.setWorking_status("01");
			}
			bcSysApproveService.updateAudit(args);
			return ResultUtil.success();
		}
		return ResultUtil.error("10001", "更新失败！");
	}
}
