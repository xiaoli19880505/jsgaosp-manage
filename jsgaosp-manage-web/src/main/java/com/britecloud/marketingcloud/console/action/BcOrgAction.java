package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSONArray;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcOrg;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.BcOrgService;
import com.britecloud.marketingcloud.service.CommonService;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 组织Action
 */
@RestController
@RequestMapping("/org")
public class BcOrgAction {

    @Autowired
    private BcOrgService bcOrgService;

    @Autowired
    private CommonService commonService;

    /**
     * 加载组织树
     * @param request
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "加载组织树")
    @RequestMapping(value = "/list_org", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listOrg(HttpServletRequest request) throws Exception {
        String tableName = "BC_ORG";
        String pColName = "P_ORG_NO";
        String pColValue = null;
        String colName = "ORG_NO";

        //获取树形
        JSONArray array = commonService.getJSONArray(tableName,pColName,colName,pColValue);

        return ResultUtil.success(array);
    }

    @OperationLogAnn(value = "根据主键获取组织")
    @RequestMapping(value = "/get_org", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getOrgById(String id){
        if(StringUtils.isNotEmpty(id)){
            return ResultUtil.success(bcOrgService.getOrgById(id));
        }
        return ResultUtil.success(null);
    }

    /**
     * 保存组织
     * @param org
     * @return
     */
    @OperationLogAnn(value = "保存组织")
    @RequestMapping(value = "/save_org", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult saveOrg(BcOrg org){
        if(org != null){
            if(StringUtils.isNotEmpty(org.getOrgNo())){
                //判断areaName是否存在
                int num = bcOrgService.existsOrgName(org);
                if(num>0){
                    return ResultUtil.error("10002","组织名称已存在!");
                }else {
                    bcOrgService.saveOrg(org);
                    return ResultUtil.success();
                }
            }
        }
        return ResultUtil.error("10001","保存失败！");
    }

    /**
     * 修改组织
     * @param org
     * @return
     */
    @OperationLogAnn(value = "修改组织")
    @RequestMapping(value = "/update_org", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult updateOrg(BcOrg org){
        if(org != null){
            bcOrgService.updateOrg(org);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","更新失败！");
    }

    /**
     * 删除组织
     * @param org
     * @return
     */
    @OperationLogAnn(value = "删除组织")
    @RequestMapping(value = "/delete_org", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult deleteOrg(BcOrg org){
        if(org != null){
            bcOrgService.deleteOrg(org);
            return ResultUtil.success();
        }
        return ResultUtil.error("10001","删除失败！");
    }
}
