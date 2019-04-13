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
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.service.BcSysApplicationService;
import com.britecloud.marketingcloud.service.BcSysArgsService;
import com.britecloud.marketingcloud.utils.StringUtils;

@RestController
@RequestMapping("/applications")
public class BcSysApplicationAction {


    
    @Autowired
    private BcSysApplicationService BcSysApplicationService;

/*    *//**
     * 加载系统参数列表
     * @param currentPage
     * @param keyword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list_applications", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listSysargs(Integer currentPage, String keyword) throws Exception {
        JSONObject jo = new JSONObject();
        keyword = HuStringUtils.nvl(keyword);
        Map params = new HashMap();
        params.put("keyword", keyword);
        params.put("page", currentPage);
        PageDataResult result = BcSysApplicationService.listSysApplications(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }

/*    @RequestMapping(value = "/get_sysargs", method = RequestMethod.GET)
    @ResponseBody
    public BcSysArgs getSysargsById(BcSysArgs args){
        if(args != null && StringUtils.isNotEmpty(args.getId())){
            return bcSysArgsService.getSysArgsById(args);
        }
        return new BcSysArgs();
    }*/

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveSysApplications(BcSysApplicationEntity args){
        if(args != null){
            if(StringUtils.isNotEmpty(args.getAppName())){
                //判断argsKey是否存在
                int num = BcSysApplicationService.existsArgsKey(args);
                if(num>0){
                    return ResultUtil.error("10002","系统参数键已存在!");
                }else {
                	BcSysApplicationService.saveSysArgs(args);
                    return ResultUtil.success();
                }
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateSysargs(BcSysApplicationEntity args){
        if(args != null){
        	BcSysApplicationService.updateSysApplication(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    @RequestMapping( method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteSysargs(BcSysApplicationEntity args){
        if(args != null && StringUtils.isNotEmpty(args.getId())){
        	BcSysApplicationService.deleteSysApplication(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }
}
