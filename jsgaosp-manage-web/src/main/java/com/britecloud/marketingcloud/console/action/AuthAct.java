/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   AuthAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.SysRoleUserMgmtService;
import com.britecloud.marketingcloud.service.SysUserMgmtService;

/**
 * 此类描述的是： 系统用户认证
 *
 * @author: ge
 * @version: 2015年8月4日 下午2:02:57
 */
@Controller
public class AuthAct {
    @Autowired
    private SysRoleUserMgmtService sysRoleUserMgmtService;
    @Autowired
    private SysUserMgmtService sysUserMgmtService;
//    @Autowired
//    private SysUserOrgMgmtService sysUserOrgMgmtService;

    @RequestMapping(value = "/auth/roles", method = RequestMethod.GET)
    @ResponseBody
    public Map roles(String email, String password) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        BcUser user = sysUserMgmtService.login(email, password);
        if (user != null) {
            result.put("auth", true);
            result.put("user", user);
            result.put("roles", sysRoleUserMgmtService.listRoleByUserId(user.getUserId()));
        }
        return result;
    }

//    @RequestMapping(value = "/auth/{userId}/orgs", method = RequestMethod.GET)
//    @ResponseBody
//    public Map roles(@PathVariable String userId) throws Exception {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("orgs", sysUserOrgMgmtService.listOrgByUserId(userId));
//        return result;
//    }

}