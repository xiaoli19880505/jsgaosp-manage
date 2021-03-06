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

import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import org.apache.struts.util.ResponseUtils;
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

    @OperationLogAnn("查询对应角色下的用户数量")
    @RequestMapping(value = "countUserByRoleId",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult countUserByRoleId(String roleId) throws Exception {

        int count= sysRoleUserMgmtService.CountUserByRoleId(roleId);

        return ResultUtil.success(count);
    }


    @OperationLogAnn("获得对应角色的人员page")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult<BcUser> get( String roleId, String keyword, int  page,String orgNo) throws Exception {
        Map paramMap = new HashMap();
        paramMap.put("roleId",roleId);
        paramMap.put("keyword",keyword);
        paramMap.put("page",page);
        paramMap.put("orgNo",orgNo);
        return sysRoleUserMgmtService.getUserListByRoleId(paramMap);
    }

    @OperationLogAnn("获得非该角色的人员page")
    @RequestMapping(value = "getrUserListNotInThisRole",method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult<BcUser> getrUserListNotInThisRole( String roleId, String keyword, int  page,String orgNo) throws Exception {
        Map paramMap = new HashMap();
        paramMap.put("roleId",roleId);
        paramMap.put("keyword",keyword);
        paramMap.put("page",page);
        paramMap.put("orgNo",orgNo);
        return sysRoleUserMgmtService.getrUserListNotInThisRole(paramMap);
    }

}
