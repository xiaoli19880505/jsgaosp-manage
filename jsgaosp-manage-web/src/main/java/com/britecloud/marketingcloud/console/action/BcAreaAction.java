package com.britecloud.marketingcloud.console.action;

import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 行政区划Action
 */
@RestController
@RequestMapping("/area")
public class BcAreaAction {

    @Autowired
    private BcAreaService bcAreaService;

    @RequestMapping(value = "/list_area", method = RequestMethod.GET)
    @ResponseBody
    public List<BcArea> listarea(HttpServletRequest request) throws Exception {
        return bcAreaService.listArea(null);
    }

    @RequestMapping(value = "/get_area", method = RequestMethod.GET)
    @ResponseBody
    public BcArea getAreaById(String id){
        if(StringUtils.isNotEmpty(id)){
            return bcAreaService.getAreaById(id);
        }
        return new BcArea();
    }

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

    @RequestMapping(value = "/update_area", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult updateArea(BcArea area){
        if(area != null){
            bcAreaService.updateArea(area);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

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
