package com.britecloud.marketingcloud.console.action.app;

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcThirdPartySysEntity;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.BcSysAppService;
import com.britecloud.marketingcloud.service.BcThirdPartySysService;
import com.britecloud.marketingcloud.service.CommonService;
import com.britecloud.marketingcloud.utils.SessionUtils;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用系统审核
 * 主要负责应用的审核
 */
@RestController
@RequestMapping("/app/approve/")
public class BcSysAppApproveAction {

    @Autowired
    private BcSysAppService bcSysAppService;

    @Autowired
    private CommonService commonService;


    @OperationLogAnn(value = "查询申报列表")
    @RequestMapping(value = "/list_approve",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listSysAppApprove(HttpServletRequest request,Integer currentPage,BcSysApplicationEntity app) throws Exception {
        JSONObject jo = new JSONObject();
        BcUser user= SessionUtils.getCurrentUser(request);

        //查询参数
        Map params = new HashMap();
        params.put("status",HuStringUtils.nvl(app.getStatus())); //应用状态
        params.put("appName",HuStringUtils.nvl(app.getAppName()));//应用名称
        params.put("sysType",HuStringUtils.nvl(app.getSysType()));//系统类型 编码
        if(StringUtils.isNotEmpty(app.getCreateDate())){
            params.put("startDate",app.getCreateDate().split("-")[0]);
            params.put("endDate",app.getCreateDate().split("-")[1]);
        }else {
            params.put("startDate","");
            params.put("endDate","");
        }
        params.put("sysId",app.getSysId());
        params.put("page", currentPage);
        params.put("areaNo",user.getAreaNo());
        PageDataResult result = bcSysAppService.listAppReport(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }

    @OperationLogAnn(value = "审核人员审核应用系统申报")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateSysApp(BcSysApplicationEntity args){
        if(args != null){
            bcSysAppService.updateSysApp(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

}
