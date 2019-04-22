package com.britecloud.marketingcloud.console.action;

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
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.BcThirdPartySysService;
import com.britecloud.marketingcloud.service.CommonService;
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
    private BcThirdPartySysService bcThirdPartySysService;

    @Autowired
    private CommonService commonService;


    @OperationLogAnn(value = "查询申报列表")
    @RequestMapping(value = "/list_report",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listSysApplicantbyOwner(Integer currentPage,BcSysApplicationEntity param) throws Exception {
        JSONObject jo = new JSONObject();

        //查询参数
        Map params = new HashMap();
        params.put("status",HuStringUtils.nvl(param.getStatus())); //应用状态
        params.put("appName",HuStringUtils.nvl(param.getAppName()));//应用名称
        params.put("sysType",HuStringUtils.nvl(param.getSysType()));//系统类型 编码
        if(StringUtils.isNotEmpty(param.getCreateDate())){
            params.put("startDate",param.getCreateDate().split("-")[0]);
            params.put("endDate",param.getCreateDate().split("-")[1]);
        }else {
            params.put("startDate","");
            params.put("endDate","");
        }
        params.put("sysId",param.getSysId());
        params.put("page", currentPage);
        PageDataResult result = bcThirdPartySysService.listThirdPartySys(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }



    @OperationLogAnn(value = "审核人员获得待审核的应用系统列表")
    @RequestMapping(value = "listThirdPartySysForApproval",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listSysApplicantForApproval(Integer currentPage,String sysName,String status) throws Exception {
        JSONObject jo = new JSONObject();
        sysName = HuStringUtils.nvl(sysName);
        status = HuStringUtils.nvl(status);
        Map params = new HashMap();
        params.put("sysName", sysName);
        if("".equals(status)){
            status=null;
        }
        params.put("status",status);
        params.put("page", currentPage);
        PageDataResult result = bcThirdPartySysService.listThirdPartySys(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }


    @OperationLogAnn(value = "业务人员应用系统申报")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveSysApplications(BcThirdPartySysEntity args){
        if(args != null){
            if(StringUtils.isNotEmpty(args.getSysName())){
                //判断argsKey是否存在
                int num = bcThirdPartySysService.existsArgsKey(args);
                if(num>0){
                    return ResultUtil.error("10002","系统参数键已存在!");
                }else {
                    bcThirdPartySysService.saveSysArgs(args);
                    return ResultUtil.success();
                }
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    @OperationLogAnn(value = "业务人员修改应用系统申报")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateSysargs(BcThirdPartySysEntity args){
        if(args != null){
            bcThirdPartySysService.updateBcThirdPartySysEntity(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }
    @OperationLogAnn(value = "业务人员删除应用系统申报")
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteSysargs(BcThirdPartySysEntity args){
        if(args != null && StringUtils.isNotEmpty(args.getId())){
            bcThirdPartySysService.deleteBcThirdPartySysEntity(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }

    @OperationLogAnn(value = "审核人员审核应用系统申报")
    @RequestMapping(value = "approveSysApplicant",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult approveSysApplicant(BcThirdPartySysEntity args){
        if(args != null){
            bcThirdPartySysService.approveSysApplicant(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }
}
