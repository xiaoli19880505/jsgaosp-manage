package com.britecloud.marketingcloud.console.action.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.DateUtils;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcThirdPartySysEntity;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.BcAreaService;
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
 * 应用系统申报
 * 主要负责应用的申报提交，查询待审核的和审核未通过的应用
 */
@RestController
@RequestMapping("/app/report/")
public class BcSysAppReportAction {

    @Autowired
    private BcSysAppService bcSysAppService;

    @Autowired
    private CommonService commonService;


    @OperationLogAnn(value = "查询申报列表")
    @RequestMapping(value = "/list_report",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listSysApplicantbyOwner(HttpServletRequest request,Integer currentPage,BcSysApplicationEntity app) throws Exception {
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

    @OperationLogAnn(value = "业务人员保存应用系统")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveSysApp(BcSysApplicationEntity args){
        if(args != null){
            if(StringUtils.isNotEmpty(args.getSysName())){
                //判断argsKey是否存在
                int num = bcSysAppService.existsAppName(args);
                if(num>0){
                    return ResultUtil.error("10002","应用名称已存在!");
                }else {
                    bcSysAppService.saveApp(args);
                    return ResultUtil.success();
                }
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    @OperationLogAnn(value = "业务人员修改应用系统")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateSysApp(BcSysApplicationEntity args){
        if(args != null){
            bcSysAppService.updateSysApp(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    @OperationLogAnn(value = "业务人员删除应用系统")
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteSysApp(BcSysApplicationEntity args){
        if(args != null && StringUtils.isNotEmpty(args.getId())){
            bcSysAppService.deleteSysApp(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }


}
