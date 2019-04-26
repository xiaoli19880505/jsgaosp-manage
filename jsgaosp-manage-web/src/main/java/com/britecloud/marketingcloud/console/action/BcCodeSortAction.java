package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.BcCodeService;
import com.britecloud.marketingcloud.service.BcSysArgsService;
import com.britecloud.marketingcloud.utils.MapUtils;
import com.britecloud.marketingcloud.utils.SessionUtils;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 数据字典主表Action
 */
@RestController
@RequestMapping("/code_sort")
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
    @OperationLogAnn(value = "加载数据字典主表列表")
    @RequestMapping(value = "/list_code_sort", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listCodeSort(HttpServletRequest request,Integer currentPage, String keyword) throws Exception {
        JSONObject jo = new JSONObject();
        keyword = HuStringUtils.nvl(keyword);

        BcUser user= SessionUtils.getCurrentUser(request);
        if(user == null){
            return ResultUtil.error("10005","未登录");
        }
        Map params = new HashMap();
        params.put("keyword", keyword);
        params.put("page", currentPage);
        params.put("orgId",user.getOrgNo());
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
    @OperationLogAnn(value = "根据主键加载数据字典")
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

    @OperationLogAnn(value = "更新数据字典")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateCodeSort(BcCodeSort codeSort){
        if(codeSort != null){
            bcCodeService.updateCodeSort(codeSort);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    @OperationLogAnn(value = "删除数据字典")
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
