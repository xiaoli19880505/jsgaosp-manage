package com.britecloud.marketingcloud.console.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.HuStringUtils;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcOrg;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.BcOrgService;
import com.britecloud.marketingcloud.service.CommonService;
import com.britecloud.marketingcloud.utils.MapUtils;
import com.britecloud.marketingcloud.utils.SessionUtils;
import com.britecloud.marketingcloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
     * @param orgNo
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "加载组织树")
    @RequestMapping(value = "/list_org", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listOrg(String  orgNo) throws Exception {
        String tableName = "BC_ORG";
        String pColName = "P_ORG_NO";
        String pColValue =orgNo ;
        String colName = "ORG_NO";
        Map<String,Object> newMap = MapUtils.transToLowerCase(bcOrgService.getOrgByOrgNo(orgNo));
        JSONObject root = JSONObject.parseObject(JSON.toJSONString(newMap));
        //获取树形
        JSONArray array = commonService.getJSONArray(tableName,pColName,colName,pColValue);
        root.put("children",array);
        JSONArray res=new JSONArray();
        res.add(root);
        return ResultUtil.success(res);
    }


    /**
     * 获取登录者所能查看的组织树
     * @param
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "获取登录者所能查看的组织树")
    @RequestMapping(value = "/get_org_tree_by_user", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getOrgTree(HttpServletRequest request) throws Exception {
        BcUser user= SessionUtils.getCurrentUser(request);
        if(user == null){
            return ResultUtil.error("10005","未登录");
        }
        return listOrg(user.getOrgNo());

    }
    /**
     * 获取组织下属
     * @param pOrgNo
     * @return
     * @throws Exception
     */
    @OperationLogAnn(value = "获取组织下属")
    @RequestMapping(value = "/listByOrgId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listByOrgId(Integer currentPage,String pOrgNo) throws Exception {

        Map params = new HashMap();
        params.put("page", currentPage);
        if(currentPage==null){
            currentPage=1;
        }
        params.put("pOrgNo", HuStringUtils.nvl(pOrgNo));
        PageDataResult result = bcOrgService.listDepartmentByOrgId(params);
        result.setPage(currentPage);
        ResponseResult   resp=     ResultUtil.success(result);
//        JSONObject obj=new JSONObject();
        String jsonStr=JSONObject.toJSONString(resp);
        return ResultUtil.success(result);

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
            //判断areaName是否存在
            int num = bcOrgService.existsOrgName(org);
            if(num>0){
                return ResultUtil.error("10002","组织名称已存在!");
            }else {
                org.setStatus("1");
                bcOrgService.saveOrg(org);
                return ResultUtil.success();
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
