/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   SysUserMgmtAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action.pay;

import com.britecloud.marketingcloud.console.util.DateUtils;
import com.britecloud.marketingcloud.model.pay.AccessApprovalPO;
import com.britecloud.marketingcloud.model.pay.AccessClientInfoPO;
import com.britecloud.marketingcloud.model.pay.AccessConfPO;
import com.britecloud.marketingcloud.model.pay.AccessConfVO;
import com.britecloud.marketingcloud.service.pay.AccessClientInfoService;
import com.britecloud.marketingcloud.service.pay.AccessConfService;
import com.britecloud.marketingcloud.utils.IDUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付应用接入管理
 *
 * @author: ge
 * @version: 2015年8月4日 下午2:13:55
 */
@Controller
@RequestMapping("/pay/app")
public class AccessConfAct {
    @Autowired
    private AccessConfService accessConfService;
    @Autowired
    private AccessClientInfoService accessClientInfoService;
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public String list(Integer currentPage, String businessName) {
        JSONObject jo = new JSONObject();
        List<JSONObject> jsonList = new ArrayList<JSONObject>();
        JSONArray jsonAry = null;
        Map params = new HashMap();
        params.put("page", currentPage);
        params.put("businessName",businessName);

        Map result = accessConfService.list(params);
        List<AccessConfVO> list = (List<AccessConfVO>) result.get("data");
        jo.put("totalCount", result.get("totalCount"));
        jo.put("totalPage", result.get("totalPage"));
        jo.put("page", currentPage);
        for (AccessConfVO user : list) {
            jsonList.add(JSONObject.fromObject(user));
        }
        jsonAry = new JSONArray().fromObject(jsonList);
        jo.put("list", jsonAry);
        return jo.toString();
    }

    /**
     * 审批
     * @param request
     * @param approvalPO
     * @return
     */
    @RequestMapping(value = "approval", method = RequestMethod.GET)
    @ResponseBody
    public Map approval(HttpServletRequest request, AccessApprovalPO approvalPO) {
        Map result = new HashMap();
        try {
            approvalPO.setId(IDUtils.getId());
            approvalPO.setApprovalTime(DateUtils.getDateTime());
            accessConfService.approval(approvalPO);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map create(HttpServletRequest request, AccessConfPO payAccessPO, AccessClientInfoPO accessClientInfoPO) {
        Map result = new HashMap();
        try {
            String id = IDUtils.getId();
            payAccessPO.setId(id);
            payAccessPO.setAppId(UUIDUtils.generateUUID());
            accessConfService.create(payAccessPO);
            accessClientInfoPO.setId(IDUtils.getId());
            accessClientInfoPO.setAccessId(id);
            accessClientInfoService.create(accessClientInfoPO);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Map update(HttpServletRequest request, AccessConfPO payAppConf) {
        Map result = new HashMap();
        try {
            accessConfService.update(payAppConf);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Map delete(String id) {
        Map result = new HashMap();
        try {
            accessConfService.delete(id);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }

    /**
     * 根据id获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getInfo(String id) {
        if (StringUtils.isNotBlank(id)) {
            AccessConfPO payAppConf = accessConfService.get(id);
            return payAppConf.toString();
        }
        return "";
    }
}
