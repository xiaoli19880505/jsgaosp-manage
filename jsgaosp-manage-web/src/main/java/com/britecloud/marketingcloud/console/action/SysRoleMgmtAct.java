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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.console.common.ResponseResult;
import com.britecloud.marketingcloud.console.configuration.OperationLogAnn;
import com.britecloud.marketingcloud.console.util.ResultUtil;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcRoleOp;
import com.britecloud.marketingcloud.service.BcRoleOpService;
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

    @Autowired
    public BcRoleOpService bcRoleOpService;

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

    @RequestMapping(value = "getRoleOpList",method = RequestMethod.GET)
    @ResponseBody
    public List<BcRoleOp> getRoleOpList(String roleId ) throws Exception {
        return bcRoleOpService.getRoleOpList(roleId);
    }



    public void  setRoleOp(String data ,String  role )  {
        Map map=new HashMap();
        JSONObject obj= JSON.parseObject(role);
        map.put("role",role);
        map.put("data",data);
         bcRoleOpService.setRoleOp(map);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult  save(HttpServletRequest request,BcRole bcRole) {
        ResponseResult res=null;
        String type=request.getParameter("type");
        String data=request.getParameter("data");
        String roleStr=request.getParameter("role");
        if("setRoleOp".equals(type)){
            setRoleOp(data,roleStr);
        }else{
//            net.sf.json.JSONObject obj=  net.sf.json.JSONObject.fromObject(roleStr);
//            BcRole role= (BcRole)net.sf.json.JSONObject.toBean(obj,BcRole.class);
            res= saveRole(request,bcRole);
        }
        return  res;
    }




    public ResponseResult  saveRole(HttpServletRequest request,BcRole role) {

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


    @OperationLogAnn("根据角色编号获得对应的按钮权限")
    @RequestMapping(value = "getPermByRoleId",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getAuthority(HttpServletRequest request,String roleIds) throws Exception {
        BcUser user = SessionUtils.getCurrentUser(request);
//        if(user == null){
//            return ResultUtil.error("10005","未登录");
//        }

//            BcRole bcRole=sysRoleMgmtService.getRoleByUserId(roleId);


            String [] roleList=roleIds.split(",");
        JSONArray arr=new JSONArray();
        for(int i=0;i<roleList.length;i++){
            List<BcRoleOp> list=bcRoleOpService.getRoleOpList(roleList[i]);
            for(int j=0;j<list.size();j++){
                arr.add(list.get(j).getOpCode());
            }
        }


        return arr;

    }

}
