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

import com.alibaba.fastjson.JSONArray;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
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

    @RequestMapping(value = "getRoleList",method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult<BcRole> get(HttpServletRequest request, String orgNo, String keyword, int currentPage ) throws Exception {
        Map paramMap = new HashMap();
        BcUser user = SessionUtils.getCurrentUser(request);
        paramMap.put("companyId", user.getCompanyId());
        paramMap.put("orgNo", orgNo);
        paramMap.put("keyword", keyword);
        paramMap.put("page", currentPage);
        return sysRoleMgmtService.query(paramMap);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult  save(HttpServletRequest request,BcRole role) {
    	BcUser user = SessionUtils.getCurrentUser(request);
        if(user == null){
            return ResultUtil.error("10005","未登录");
        }
    	role.setRoleType("USER");
    	role.setCreateUserId(user.getUserId());
        sysRoleMgmtService.save(role);
        return ResultUtil.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(String roleId) throws Exception {
        sysRoleMgmtService.delete(roleId);
        return roleId;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult update(HttpServletRequest request, BcRole role) throws Exception {
        BcUser user= SessionUtils.getCurrentUser(request);
        if(user == null){
            return ResultUtil.error("10005","未登录");
        }
        role.setUpdateUserId(user.getUserId());
        sysRoleMgmtService.update(role);
        return ResultUtil.success();
    }



    @RequestMapping(value = "getAuthority",method = RequestMethod.GET)
    @ResponseBody
    public String getAuthority(HttpServletRequest request) throws Exception {
        String list=" [{'stTarId':99152,'pid':-1,'name':'医疗质量','code':'ylzl','desc':null,'avg':null,'unit':null,'status':null,'type':'01','index':1,'children':[{'stTarId':99154,'pid':99152,'name':'药占比','code':'yzb','desc':'医院的药占比','avg':2.1,'unit':'%','status':'02','type':'02','index':2,'children':null,'label':'药占比','iconCls':'tree-icon tree-file '},{'stTarId':99153,'pid':99152,'name':'抗生素使用率','code':'ksssyl','desc':'医院的抗生素使用率','avg':2.3,'unit':'%','status':'02','type':'02','index':3,'children':null,'label':'抗生素使用率','iconCls':'tree-icon tree-file '}],'label':'医疗质量','iconCls':'tree-icon tree-folder tree-folder-open'},{'stTarId':98977,'pid':-1,'name':'绩效','code':'jx','desc':null,'avg':null,'unit':null,'status':'','type':'01','index':2,'children':[{'stTarId':98978,'pid':98977,'name':'岗位工作量','code':'gwgzl','desc':null,'avg':null,'unit':null,'status':'','type':'01','index':1,'children':[{'stTarId':100353,'pid':98978,'name':'医保控费','code':'ybkf','desc':'医院的医保控费用','avg':20.6,'unit':'万元','status':'01','type':'02','index':2,'children':null,'label':'医保控费','iconCls':'tree-icon tree-file '},{'stTarId':99020,'pid':98978,'name':'巡房次数','code':'xfcs','desc':'每天一个护士的巡房次数','avg':3.0,'unit':'次','status':'02','type':'02','index':2,'children':null,'label':'巡房次数','iconCls':'tree-icon tree-file '}],'label':'岗位工作量','iconCls':'tree-icon tree-folder tree-folder-open'}],'label':'绩效','iconCls':'tree-icon tree-folder tree-folder-open'},{'stTarId':100351,'pid':-1,'name':'目标','code':'mb','desc':null,'avg':null,'unit':null,'status':null,'type':'01','index':3,'children':[{'stTarId':100352,'pid':100351,'name':'手术室使用率','code':'ssssyl','desc':'医院的手术室使用率','avg':80.0,'unit':'%','status':'02','type':'02','index':1,'children':null,'label':'手术室使用率','iconCls':'tree-icon tree-file '},{'stTarId':98979,'pid':100351,'name':'注射例数','code':'zsls','desc':'打针','avg':2000.0,'unit':'例','status':'02','type':'02','index':2,'children':null,'label':'注射例数','iconCls':'tree-icon tree-file '}],'label':'目标','iconCls':'tree-icon tree-folder tree-folder-open'},{'stTarId':101863,'pid':-1,'name':'住院分析','code':'zyfx','desc':null,'avg':null,'unit':null,'status':null,'type':'01','index':4,'children':[{'stTarId':101864,'pid':101863,'name':'住院收入','code':'zysr','desc':'住院收入','avg':40.0,'unit':'万元','status':'02','type':'02','index':1,'children':null,'label':'住院收入','iconCls':'tree-icon tree-file '},{'stTarId':101865,'pid':101863,'name':'住院人次','code':'zyrc','desc':'住院人次','avg':200.0,'unit':'人','status':'02','type':'02','index':2,'children':null,'label':'住院人次','iconCls':'tree-icon tree-file '}],'label':'住院分析','iconCls':'tree-icon tree-folder tree-folder-open'}]";
        JSONArray arr=JSONArray.parseArray(list);
        return arr.toString();
    }

}
