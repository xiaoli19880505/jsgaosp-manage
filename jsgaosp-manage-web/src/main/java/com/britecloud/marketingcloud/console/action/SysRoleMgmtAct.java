/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   SysRoleMgmtAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.SysRoleMgmtService;
import com.britecloud.marketingcloud.utils.SessionUtils;

/**
 * 项目名称:医院罗盘
 * 文件名：SysRoleMgmtAct.java
 * author:ge
 * 版本信息：
 * 日期：2015年8月4日
 * Copyright 颢云科技 2015
 * 版权所有
 */
@Controller
@RequestMapping("/system/role")
public class SysRoleMgmtAct {

    @Autowired
    public SysRoleMgmtService sysRoleMgmtService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public BcRole save(HttpServletRequest request,BcRole role) {
    	BcUser user = SessionUtils.getCurrentUser(request);
    	role.setRoleType("USER");
    	role.setCompanyId(user.getCompanyId());
        sysRoleMgmtService.save(role);
        return role;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(String roleId) throws Exception {
        sysRoleMgmtService.delete(roleId);
        return roleId;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public BcRole update(BcRole role) throws Exception {
        sysRoleMgmtService.update(role);
        return role;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<BcRole> get(HttpServletRequest request) throws Exception {
    	Map paramMap = new HashMap();
    	BcUser user = SessionUtils.getCurrentUser(request);
    	paramMap.put("companyId", user.getCompanyId());
        return sysRoleMgmtService.query(paramMap);
    }

}
