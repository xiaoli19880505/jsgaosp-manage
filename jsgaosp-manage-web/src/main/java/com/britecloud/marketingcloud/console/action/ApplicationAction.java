package com.britecloud.marketingcloud.console.action;

import java.math.BigDecimal;
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
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.ApplicationService;
import com.britecloud.marketingcloud.utils.SessionUtils;
import com.britecloud.marketingcloud.utils.StringUtils;

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
			if ((null == args.getApproval_opinion() || "".equals(args.getApproval_opinion()))// 编辑应用
					&& !"01".equals(args.getApproval_status())) {
				ApplicationService.updateApplicationInfo(args);
			} else {// 审核
				// 更新当前；应用状态
				String infoId = args.getInfo_id();
				if ("01".equals(args.getApproval_status())) {
					// 下线之前的应用
					ApplicationService.updateInfoWorkStatus(args,"Audit");
					args.setWorking_status("01");
				}
				ApplicationService.updateAudit(args);

			}
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

	@RequestMapping(value = "/list_version", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult listVersion(Integer currentPage, String appName, String orgId, String sysType)
			throws Exception {
		Map params = new HashMap();
		params.put("page", currentPage);
		params.put("app_name", appName);
		params.put("org_id", orgId);
		params.put("sys_type", sysType);
		PageDataResult result = ApplicationService.listHisVersion(params);
		result.setPage(currentPage);

		return ResultUtil.success(result);
	}

	@OperationLogAnn(value = "删除应用")
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseResult deleteApplications(HttpServletRequest request, ApplicationEntity args) {
		BcUser user = SessionUtils.getCurrentUser(request);
		if (user == null) {
			return ResultUtil.error("10005", "未登录");
		}
		if (args != null && StringUtils.isNotEmpty(args.getId())) {
			// 修改应用主表状态
			args.setStatus("0");
			// args.setVersion_status("0");
			/*
			 * args.setApp_id(args.getId()); args.setInfo_id(info_id);
			 */
			args.setApproval_user_id(user.getUserId());
			ApplicationService.updateStatus(args);
			return ResultUtil.success();
		}
		return ResultUtil.error("10001", "删除失败！");
	}

	@OperationLogAnn(value = "版本管理")
	@RequestMapping(value = "/rollback_version", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult rollbackVersion(HttpServletRequest request, String infoId) {
		BcUser user = SessionUtils.getCurrentUser(request);
		if (user == null) {
			return ResultUtil.error("10005", "未登录");
		}
		if (StringUtils.isNotEmpty(infoId)) {
			ApplicationEntity args = new ApplicationEntity();
			args = ApplicationService.listInfoAppById(infoId);
			// 获取当前应用的最高版本
			ApplicationEntity entity = new ApplicationEntity();
			entity = ApplicationService.queryInfoList(args.getApp_id(), args.getOrg_id());
			BigDecimal num1 = new BigDecimal(entity.getVersion());
			BigDecimal num2 = new BigDecimal("1");
			BigDecimal version = num1.add(num2);
			args.setVersion(version.toString());
			ApplicationService.rollbackVersion(args);
			return ResultUtil.success();
		}
		return ResultUtil.error("10001", "失败！");
	}

	@OperationLogAnn(value = "禁用启用应用")
	@RequestMapping(value = "/disable_version", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult disableVersion(HttpServletRequest request, String info_id, String org_id, String app_id,
			String sys_type, String workstatus) {
		// workstatus 00 下架 01 上架
		BcUser user = SessionUtils.getCurrentUser(request);
		if (user == null) {
			return ResultUtil.error("10005", "未登录");
		}
		if (StringUtils.isNotEmpty(info_id)) {
		ApplicationEntity entity = new ApplicationEntity();
		entity.setInfo_id(info_id);
		entity.setOrg_id(org_id);
		entity.setApp_id(app_id);
		entity.setSys_type(sys_type);
		entity.setWorking_status(workstatus);
		ApplicationService.updateInfoWorkStatus(entity,"disable");
		return ResultUtil.success();
		}
		return ResultUtil.error("10001", "失败！");
	}

	@Test
	public void test() {
		BigDecimal num1 = new BigDecimal("2.0");
		BigDecimal num2 = new BigDecimal("1");
		BigDecimal newprice = num1.add(num2);
		System.out.println(newprice);

	}

}
