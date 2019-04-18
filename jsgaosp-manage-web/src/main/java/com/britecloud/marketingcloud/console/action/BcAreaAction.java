package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.ObjectUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.CommonService;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.apache.commons.collections.map.HashedMap;
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

/**
 * 行政区划Action
 */
@RestController
@RequestMapping("/area")
public class BcAreaAction {

    @Autowired
    private BcAreaService bcAreaService;

    @Autowired
    private CommonService commonService;

    /**
     * 加载行政区划
     * @param request
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "加载行政区划")
    @RequestMapping(value = "/list_area", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listarea(HttpServletRequest request) throws Exception {
        String tableName = "BC_AREA";
        String pColName = "P_AREA_NO";
        String pColValue = "0";
        String colName = "AREA_NO";

        //获取树形
        JSONArray array = commonService.getJSONArray(tableName,pColName,colName,pColValue);

        return ResultUtil.success(array);
    }

    @OperationLogAnn(value = "根据主键获取行政区划")
    @RequestMapping(value = "/get_area", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getAreaById(String id){
        if(StringUtils.isNotEmpty(id)){
            return ResultUtil.success(bcAreaService.getAreaById(id));
        }
        return ResultUtil.success(null);
    }

    /**
     * 保存行政区划
     * @param area
     * @return
     */
    @OperationLogAnn(value = "保存行政区划")
    @RequestMapping(value = "/save_area", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult saveArea(BcArea area){
        if(area != null){
            if(StringUtils.isNotEmpty(area.getAreaNo())){
                //判断areaNo是否存在
                int num = bcAreaService.existsAreaNo(area);
                if(num>0){
                    return ResultUtil.error("10002","行政区划编号已存在!");
                }else {
                    bcAreaService.saveArea(area);
                    return ResultUtil.success();
                }
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    /**
     * 修改行政区划
     * @param area
     * @return
     */
    @OperationLogAnn(value = "修改行政区划")
    @RequestMapping(value = "/update_area", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult updateArea(BcArea area){
        if(area != null){
            bcAreaService.updateArea(area);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    /**
     * 删除行政区划
     * @param area
     * @return
     */
    @OperationLogAnn(value = "删除行政区划")
    @RequestMapping(value = "/delete_area", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult deleteArea(BcArea area){
        if(area != null){
            bcAreaService.deleteArea(area);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }
}
