package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.CommonService;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping(value = "/list_area_json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listarea(String pAreaNo) throws Exception {
        String tableName = "BC_AREA";
        String pColName = "P_AREA_NO";
        String pColValue = pAreaNo;
        String colName = "AREA_NO";

        JSONObject root = JSONObject.parseObject(JSON.toJSONString(bcAreaService.getAreaByAreaNo(pAreaNo)));

        //获取树形
        JSONArray array = commonService.getJSONArray(tableName,pColName,colName,pColValue);
        root.put("children",array);
        return ResultUtil.success(root);
    }
}