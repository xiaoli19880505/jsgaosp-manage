package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.ApplicationService;
import com.britecloud.marketingcloud.utils.SessionUtils;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/application")
public class ApplicationAction {
	@Autowired
	private ApplicationService ApplicationService;

	// 数据录入
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult inserApplications(HttpServletRequest request, ApplicationEntity application) {
		BcUser user = SessionUtils.getCurrentUser(request);
		if (user == null) {
			return ResultUtil.error("10005", "未登录");
		}
		if (application != null) {
			application.setCreate_user_id(user.getUserId());
			ApplicationService.saveApplication(application);
			return ResultUtil.success();
		}
		return ResultUtil.error("10001", "保存失败！");
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
			args.setApproval_user_id(user.getUserId());
			ApplicationService.updateApplication(args);
			return ResultUtil.success();
		}
		return ResultUtil.error("10001", "更新失败！");
	}

	@RequestMapping(value = "/list_applications", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult listApplications(Integer currentPage, String orgNo) throws Exception {
		Map params = new HashMap();
		params.put("page", currentPage);
		params.put("orgNo", orgNo);
		PageDataResult result = ApplicationService.listSysApproves(params);
		result.setPage(currentPage);

		return ResultUtil.success(result);
	}
}
