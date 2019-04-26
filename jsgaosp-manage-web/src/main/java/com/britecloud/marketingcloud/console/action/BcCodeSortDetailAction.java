package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
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
@RequestMapping("/code_sort_detail")
public class BcCodeSortDetailAction {

    @Autowired
    private BcCodeService bcCodeService;

    /**
     * 加载数据字典列表
     * @param currentPage
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "加载数据字典列表")
    @RequestMapping(value = "/list_code", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listCodeSort(Integer currentPage,String keyword) throws Exception {
        Map params = new HashMap();
        params.put("page", currentPage);
        if(currentPage==null){
            currentPage=1;
        }
        params.put("keyword", HuStringUtils.nvl(keyword));
        params.put("hasChild","1");
        PageDataResult result = bcCodeService.listCodeSort(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }



    @OperationLogAnn(value = "加载数据字典详情列表")
    @RequestMapping(value = "/list_code_detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listCodeSortDetail(Integer currentPage,String pCodeSortId,String keyword) throws Exception {
        JSONObject jo = new JSONObject();
        Map params = new HashMap();
        params.put("page", currentPage);
        params.put("pCodeSortId",pCodeSortId);
        params.put("hasChild","0");
        params.put("keyword","");
        if(currentPage==null){
            currentPage=1;
        }
        PageDataResult result = bcCodeService.listCodeSortDetailBypCodeSortId(params);
        result.setPage(currentPage);

        return ResultUtil.success(result);
    }

    @OperationLogAnn(value = "保存数据字典")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveCode(BcCodeSort codeSort){
        if(codeSort != null){
            codeSort.setStatus("1");
            if(StringUtils.isNotEmpty(codeSort.getCodeSortKey())){
                bcCodeService.saveCodeSort(codeSort);
                return ResultUtil.success();
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    @OperationLogAnn(value = "修改数据字典")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateCode(BcCodeSort codeSort){
        if(codeSort != null){
            bcCodeService.updateCodeSort(codeSort);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    @OperationLogAnn(value = "删除数据字典")
    @RequestMapping( method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteCode(BcCodeSort codeSort){
        if(codeSort != null && StringUtils.isNotEmpty(codeSort.getCodeSortId())){
            bcCodeService.deleteCodeSort(codeSort);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }
}
