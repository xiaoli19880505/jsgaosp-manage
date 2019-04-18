package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcCode;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.CommonService;
import com.britecloud.marketingcloud.utils.MapUtils;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 公共Action
 */
@RestController
@RequestMapping("/common")
public class CommonAction {

    @Autowired
    private BcAreaService bcAreaService;

    @Autowired
    private CommonService commonService;

    /**
     * 加载行政区划
     * @param pAreaNo 父级编号
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "根据父级编号加载行政区划")
    @RequestMapping(value = "/list_area_json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listarea(String pAreaNo) throws Exception {
        String tableName = "BC_AREA";
        String pColName = "P_AREA_NO";
        String pColValue = pAreaNo;
        String colName = "AREA_NO";

        Map<String,Object> newMap = MapUtils.transToLowerCase(bcAreaService.getAreaByAreaNo(pAreaNo));

        JSONObject root = JSONObject.parseObject(JSON.toJSONString(newMap));

        //获取树形
        JSONArray array = commonService.getJSONArray(tableName,pColName,colName,pColValue);
        root.put("children",array);
        return ResultUtil.success(root);
    }

    /**
     * 加载数据字典列表
     * @param codeSort
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "根据CodeSortKey加载数据字典详情列表")
    @RequestMapping(value = "/list_code", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listcode(BcCodeSort codeSort) throws Exception {
        List<BcCode> codeList = commonService.getCodeList(codeSort);
        return ResultUtil.success(codeList);
    }
}
