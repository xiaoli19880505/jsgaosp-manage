package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.BcSysArgsService;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数Action
 */
@RestController
@RequestMapping("/sysargs")
public class BcSysArgsAction {

    @Autowired
    private BcSysArgsService bcSysArgsService;

    /**
     * 加载系统参数列表
     * @param currentPage
     * @param keyword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list_sysargs", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listSysargs(Integer currentPage, String keyword) throws Exception {
        JSONObject jo = new JSONObject();
        keyword = HuStringUtils.nvl(keyword);
        Map params = new HashMap();
        params.put("keyword", keyword);
        params.put("page", currentPage);
        PageDataResult result = bcSysArgsService.listSysArgs(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }

    @RequestMapping(value = "/get_sysargs", method = RequestMethod.GET)
    @ResponseBody
    public BcSysArgs getSysargsById(BcSysArgs args){
        if(args != null && StringUtils.isNotEmpty(args.getId())){
            return bcSysArgsService.getSysArgsById(args);
        }
        return new BcSysArgs();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveSysargs(BcSysArgs args){
        if(args != null){
            if(StringUtils.isNotEmpty(args.getArgsKey())){
                //判断argsKey是否存在
                int num = bcSysArgsService.existsArgsKey(args);
                if(num>0){
                    return ResultUtil.error("10002","系统参数键已存在!");
                }else {
                    bcSysArgsService.saveSysArgs(args);
                    return ResultUtil.success();
                }
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateSysargs(BcSysArgs args){
        if(args != null){
            bcSysArgsService.updateSysArgs(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    @RequestMapping( method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteSysargs(BcSysArgs args){
        if(args != null && StringUtils.isNotEmpty(args.getId())){
            bcSysArgsService.deleteSysArgs(args);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }
}
