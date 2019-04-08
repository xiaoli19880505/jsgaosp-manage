/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   SysCompanyMgmtAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action;

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

import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.model.BcCompany;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.SysCompanyMgmtService;
import com.britecloud.marketingcloud.utils.IDUtils;
import com.britecloud.marketingcloud.utils.SessionUtils;

@Controller
@RequestMapping("/compaign/company")
public class SysCompanyMgmtAct {
	@Autowired
	private SysCompanyMgmtService sysCompanyService;;
	
	/**
     * 加载组织Tree
     *
     * @return
     */
    @RequestMapping(value = "loadCompany", method = RequestMethod.GET)
    @ResponseBody
    public String loadCompany() {
        List<BcCompany> listCompany = sysCompanyService.loadCompany();
        return JSONObject.toJSONString(listCompany);
    }
    /**
     * 根据组织id获取组织信息
     * @param companyId
     * @return
     */
    @RequestMapping(value="getInfo" , method=RequestMethod.GET)
    @ResponseBody
    public String getCompanyInfo(String companyId){
    	if(companyId!=null&&!companyId.equals("")){
    		BcCompany company=sysCompanyService.getCompanyInfo(companyId);
    		JSONObject jo=new JSONObject();
    		jo.put("name", company.getName());
    		jo.put("companyId", company.getCompanyId());
    		jo.put("description", company.getDescription());
    		return jo.toString();
    	}
    	return "";
    }
    /**
     * 添加一个组织
     * @param name
     * @return
     */
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String addCompany(HttpServletRequest request,String name){
    	BcCompany company = new BcCompany();
    	//BcUser user = (BcUser) ClientSessionHolder.getClientSession().getAttribute(ClientUtils.CURRENT_USER);
    	BcUser user = SessionUtils.getCurrentUser(request);
    	Date date = new Date();
    	
    	company.setCompanyId(IDUtils.getId());
    	company.setCreateBy(user.getUserId());
    	company.setCreateDate(date);
    	company.setUpdateBy(user.getUserId());
    	company.setUpdateDate(date);
    	company.setName(name);
    	
    	sysCompanyService.addCompany(company);
    	return "";
    }
    /**
     * 删除一个组织
     * @param companyId
     * @return
     */
    @RequestMapping(method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteCompany(String companyId){
    	sysCompanyService.deleteCompany(companyId);
    	return "";
    }
    /**
     * 修改组织信息
     * @param company
     * @return
     */
    @RequestMapping(method=RequestMethod.PUT)
    @ResponseBody
    public Map saveCompany(HttpServletRequest request,BcCompany company){
    	Map result=new HashMap();
    	try{
    		//BcUser user = (BcUser) ClientSessionHolder.getClientSession().getAttribute(ClientUtils.CURRENT_USER);
    		BcUser user= SessionUtils.getCurrentUser(request);
    		company.setUpdateDate(new Date());
    		company.setUpdateBy(user.getUserId());
    		sysCompanyService.saveCompany(company);
    		result.put("result", true);
    	}catch(Exception e){
    		e.printStackTrace();
    		result.put("result", false);
    	}
    	return result;
    }
}
