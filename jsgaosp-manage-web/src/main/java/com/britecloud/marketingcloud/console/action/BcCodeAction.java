package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCode;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.service.BcCodeService;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典明细Action
 */
@RestController
@RequestMapping("/code")
public class BcCodeAction {

    @Autowired
    private BcCodeService bcCodeService;

    /**
     * 加载数据字典列表
     * @param currentPage
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "加载数据字典详情列表")
    @RequestMapping(value = "/list_code", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listCode(Integer currentPage,String codeSortId) throws Exception {
        JSONObject jo = new JSONObject();
        Map params = new HashMap();
        params.put("page", currentPage);
        params.put("codeSortId",codeSortId);
        PageDataResult result = bcCodeService.listCode(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }

    @OperationLogAnn(value = "保存数据字典详情")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveCode(BcCode code){
        if(code != null){
            if(StringUtils.isNotEmpty(code.getCodeKey())){
                bcCodeService.saveCode(code);
                return ResultUtil.success();
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    @OperationLogAnn(value = "修改数据字典详情")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateCode(BcCode code){
        if(code != null){
            bcCodeService.updateCode(code);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    @OperationLogAnn(value = "删除数据字典详情")
    @RequestMapping( method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteCode(BcCode code){
        if(code != null && StringUtils.isNotEmpty(code.getCodeId())){
            bcCodeService.deleteCode(code);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }
}
