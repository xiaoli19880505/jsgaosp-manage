package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.service.BcCodeService;
import com.britecloud.marketingcloud.service.BcSysArgsService;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典主表Action
 */
@RestController
@RequestMapping("/codesort")
public class BcCodeSortAction {

    @Autowired
    private BcCodeService bcCodeService;

    /**
     * 加载数据字典主表列表
     * @param currentPage
     * @param keyword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list_codesort", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listCodeSort(Integer currentPage, String keyword) throws Exception {
        JSONObject jo = new JSONObject();
        keyword = HuStringUtils.nvl(keyword);
        Map params = new HashMap();
        params.put("keyword", keyword);
        params.put("page", currentPage);
        PageDataResult result = bcCodeService.listCodeSort(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }

    /**
     * 根据主键
     * 加载数据字典
     * @param codeSort
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_codesort", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getCodeSortById(BcCodeSort codeSort) throws Exception {
        BcCodeSort result = bcCodeService.getCodeSortById(codeSort);
        return ResultUtil.success(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveCodeSort(BcCodeSort codeSort){
        if(codeSort != null){
            if(StringUtils.isNotEmpty(codeSort.getCodeSortKey())){
                bcCodeService.saveCodeSort(codeSort);
                return ResultUtil.success();
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateCodeSort(BcCodeSort codeSort){
        if(codeSort != null){
            bcCodeService.updateCodeSort(codeSort);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    @RequestMapping( method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteCodeSort(BcCodeSort codeSort){
        if(codeSort != null && StringUtils.isNotEmpty(codeSort.getCodeSortId())){
            bcCodeService.deleteCodeSort(codeSort);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }
}
