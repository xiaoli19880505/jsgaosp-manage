/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   SysUserMgmtAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action;

import com.britecloud.marketingcloud.console.util.DateUtils;
import com.britecloud.marketingcloud.model.PayAppConf;
import com.britecloud.marketingcloud.service.PayAppConfService;
import com.britecloud.marketingcloud.utils.IDUtils;
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
 * 此类描述的是：支付应用接入管理
 *
 * @author: ge
 * @version: 2015年8月4日 下午2:13:55
 */
@Controller
@RequestMapping("/pay/app")
public class PayAppConfAct {
    @Autowired
    private PayAppConfService payAppConfService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public String listPayConf(Integer currentPage) {
        JSONObject jo = new JSONObject();
        List<JSONObject> jsonList = new ArrayList<JSONObject>();
        JSONArray jsonAry = null;
        Map params = new HashMap();
        params.put("page", currentPage);
        Map result = payAppConfService.listPayConf(params);
        List<PayAppConf> list = (List<PayAppConf>) result.get("data");
        jo.put("totalCount", result.get("totalCount"));
        jo.put("totalPage", result.get("totalPage"));
        jo.put("page", currentPage);
        for (PayAppConf user : list) {
            JSONObject temp = new JSONObject();
            temp.put("id", user.getId());
            temp.put("appId", user.getAppId());
            temp.put("businessName", user.getBusinessName());
            temp.put("clientPublicKey", user.getClientPublicKey());
            temp.put("clientPrivateKey", user.getClientPrivateKey());
            temp.put("serverPublicKey", user.getServerPublicKey());
            temp.put("serverPrivateKey", user.getServerPrivateKey());
            temp.put("description", user.getDescription());
            temp.put("createTime", user.getCreateTime());
            jsonList.add(temp);
        }
        jsonAry = new JSONArray().fromObject(jsonList);
        jo.put("listPayConf", jsonAry);
        return jo.toString();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map createPayAppConf(HttpServletRequest request, PayAppConf payAppConf) {
        Map result = new HashMap();
        try {
            String userId = IDUtils.getId();
            payAppConf.setId(userId);
            payAppConf.setCreateTime(DateUtils.getDateTime());
            payAppConf.setValid("Y");
            payAppConfService.createPayAppConf(payAppConf);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Map updatePayAppConf(HttpServletRequest request, PayAppConf payAppConf) {
        Map result = new HashMap();
        try {
            payAppConfService.updatePayAppConf(payAppConf);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Map deletePayAppConf(String id) {
        Map result = new HashMap();
        try {
            payAppConfService.deletePayAppConf(id);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(String id) {
        if (StringUtils.isNotBlank(id)) {
            PayAppConf payAppConf = payAppConfService.getPayAppConf(id);
            return payAppConf.toString();
        }
        return "";
    }
}
