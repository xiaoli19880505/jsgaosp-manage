package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.ObjectUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.service.BcAreaService;
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

    /**
     * 加载行政区划
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list_area", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listarea(HttpServletRequest request) throws Exception {
        JSONArray array = new JSONArray();

        List<BcArea> areaList = bcAreaService.listArea("0");
        for (BcArea bcArea : areaList){
            JSONObject object = JSONObject.parseObject(JSON.toJSONString(bcArea));
            object.put("children",getAreaArrayByPAreaNo(bcArea.getAreaNo()));
            array.add(object);
        }

        return ResultUtil.success(array);
    }

    List<Map<String,Object>> getAreaArrayByPAreaNo(String pAreaNo) throws IllegalAccessException{
        JSONObject object = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        JSONArray tmp = new JSONArray();
        List<BcArea> resultList = bcAreaService.listArea(pAreaNo);
        if(resultList != null && resultList.size()>0){
            for (BcArea bcArea : resultList){
                Map<String,Object> map = ObjectUtils.objectToMap(bcArea);

                List<Map<String, Object>> nodeList = getAreaArrayByPAreaNo(bcArea.getAreaNo());
                if (nodeList != null && nodeList.size()>0) {
                    map.put("children", nodeList);
                }
                list.add(map);
            }
        }

        return list;
    }

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
