/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   SysUserMgmtAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.SysUserMgmtService;
import com.britecloud.marketingcloud.utils.IDUtils;
import com.britecloud.marketingcloud.utils.MD5Util;
import com.britecloud.marketingcloud.utils.SessionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 此类描述的是：系统用户管理
 * 
 * @author: ge
 * 
 * @version: 2015年8月4日 下午2:13:55
 */
@Controller
@RequestMapping("/system/bcuser")
public class SysUserMgmtAct {
	@Autowired
	private SysUserMgmtService sysUserMgmtService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public String geSystemList(Integer currentPage, String keyword,String orgNo,String companyId){
		JSONObject jo = new JSONObject();
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray jsonAry = null;
		if (keyword.equals(""))
			keyword = null;
		Map params = new HashMap();
		params.put("orgNo", orgNo);
//		if(keyword!=null){
//			try {
//				params.put("keyword", java.net.URLDecoder.decode(keyword, "GBK"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		params.put("keyword",keyword);
		params.put("page", currentPage);
		params.put("companyId", companyId);
		Map result = sysUserMgmtService.listSysUser(params);
		List<BcUser> list = (List<BcUser>) result.get("data");
		jo.put("totalCount", result.get("totalCount"));
		jo.put("totalPage", result.get("totalPage"));
		jo.put("page", currentPage);
		for (BcUser user : list) {
			JSONObject temp = new JSONObject();
			temp.put("userId", user.getUserId());
			temp.put("userName", user.getUserName());
			temp.put("userMobile", user.getUserMobile());
			temp.put("userPwd", user.getUserPwd());
			temp.put("userEmail", user.getUserEmail());
			temp.put("userQq", user.getUserQq());
			temp.put("userOfficePhone", user.getUserOfficePhone());
			temp.put("userType", user.getUserType());
			temp.put("orgNo", user.getOrgNo());
			temp.put("orgName", user.getOrgName());
			jsonList.add(temp);
		}
		jsonAry = new JSONArray().fromObject(jsonList);
		jo.put("sysUsers", jsonAry);
		return jo.toString();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map createUser(HttpServletRequest request, BcUser user){
		Map result=new HashMap();
		try{
			String userId = IDUtils.getId();
			user.setUserId(userId);
			//BcUser currentUser =(BcUser) ClientSessionHolder.getClientSession().getAttribute(ClientUtils.CURRENT_USER);
			BcUser currentUser = SessionUtils.getCurrentUser(request); 
			user.setUserPwd(MD5Util.string2MD5(user.getUserPwd()));
			user.setCreateBy(currentUser.getUserId());
			user.setUpdateBy(currentUser.getUserId());
			user.setRetired("1");
			Date date = new Date();
			user.setCreateDate(date);
			user.setUpdateDate(date);
			sysUserMgmtService.createUser(user);
			result.put("result", true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", false);
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map updateUser(HttpServletRequest request,BcUser user){
		Map result=new HashMap();
		try{
			user.setUpdateDate(new Date());
			user.setUpdateBy(SessionUtils.getCurrentUser(request).getUserId());
			sysUserMgmtService.updateUser(user);
			result.put("result", true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", false);
		}
		return result;
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public Map deleteUser(String userId){
		Map result=new HashMap();
		try{
			sysUserMgmtService.deleteUser(userId);
			result.put("result", true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", false);
		}
		return result;
	}
	
	@RequestMapping(value="resetPwd", method = RequestMethod.GET)
	@ResponseBody
	public Map resetPwd(String userId){
		Map result=new HashMap();
		try{
			sysUserMgmtService.resetPwd(userId, MD5Util.string2MD5(BcUser.DEFAULT_PASSWORD));
			result.put("result", true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", false);
		}
		return result;
	}
	
	
	@RequestMapping(value="/getCountByCompanyId")
	@ResponseBody
	public Map getCountByCompanyId(String companyId){
		Map result=new HashMap();
		int count = 0;
		try{
			count = sysUserMgmtService.getCountByCompanyId(companyId);
			result.put("count", count);
		}catch(Exception e){
			e.printStackTrace();
			result.put("count", count);
		}
		return result;
	}
	
	@RequestMapping(value="/checkEmail")
	@ResponseBody
	public Map checkEmail(String email){
		Map result=new HashMap();
		int count = 0;
		try{
			count = sysUserMgmtService.checkEmail(email);
			result.put("count", count);
		}catch(Exception e){
			e.printStackTrace();
			result.put("count", count);
		}
		return result;
	}
	/**
	 * 根据id获取用户信息
	 * @param
	 * @return
	 */
    @RequestMapping(value="getInfo" , method=RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(String userId){
    	if(userId!=null&&!userId.equals("")){
    		BcUser user=sysUserMgmtService.getUser(userId);
    		JSONObject temp = new JSONObject();
			temp.put("userId", user.getUserId());
			temp.put("userName", user.getUserName());
			temp.put("userMobile", user.getUserMobile());
			temp.put("userPwd", user.getUserPwd());
			temp.put("userEmail", user.getUserEmail());
			temp.put("userQq", user.getUserQq());
			temp.put("userOfficePhone", user.getUserOfficePhone());
			temp.put("userType", user.getUserType());
    		return temp.toString();
    	}
    	return "";
    }

}
