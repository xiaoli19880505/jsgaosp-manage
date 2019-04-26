package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.Map;
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
import com.britecloud.marketingcloud.service.ApplicationService;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/application")
public class ApplicationAction {
	@Autowired
	private ApplicationService ApplicationService;
	
	//数据录入
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult inserApplications(ApplicationEntity args) {
		if (args != null) {
			ApplicationService.saveApplication(args);
			ApplicationService.saveApplicationInfo(args);
			return ResultUtil.success();
		}
		return ResultUtil.error("10001", "保存失败！");
	}
	
	
	@OperationLogAnn(value = "修改应用申报")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateApplications(ApplicationEntity args){
      if(args != null){
    	  ApplicationService.updateApplication(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }
	@RequestMapping(value = "/list_applications", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult listApplications(Integer currentPage) throws Exception {
		Map params = new HashMap();
		params.put("page", currentPage);
		PageDataResult result = ApplicationService.listSysApproves(params);
		result.setPage(currentPage);

		return ResultUtil.success(result);
	}
}
