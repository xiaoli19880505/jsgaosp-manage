/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   SysRoleUserMgmtAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;
import com.britecloud.marketingcloud.service.SysRoleUserMgmtService;
import com.britecloud.marketingcloud.utils.SessionUtils;

@Controller
@RequestMapping("/system/role_user")
public class SysRoleUserMgmtAct {

    @Autowired
    public SysRoleUserMgmtService sysRoleUserMgmtService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String add(String roleId, String ids) {
        sysRoleUserMgmtService.add(roleId, ids);
        return ids;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String remove(String roleId, String userId) throws Exception {
        sysRoleUserMgmtService.remove(roleId, userId);
        return userId;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<BcUser> get(HttpServletRequest request,String roleType,String roleId, String query, String sign, Pageable pageable) throws Exception {
    	BcUser user = SessionUtils.getCurrentUser(request);
    	Map paramMap = new HashMap();
    	paramMap.put("roleId",roleId);
    	paramMap.put("roleType",roleType);
    	paramMap.put("query", "".equals(query) ? null : query);
    	paramMap.put("companyId", user.getCompanyId());
        return sysRoleUserMgmtService.get(paramMap, sign, pageable);
    }

}
