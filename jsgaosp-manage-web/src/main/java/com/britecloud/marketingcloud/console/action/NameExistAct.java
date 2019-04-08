/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   NameExistAct.java
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
import com.britecloud.marketingcloud.service.NameService;
import com.britecloud.marketingcloud.utils.SessionUtils;

@Controller
@RequestMapping("/nameValidate")
public class NameExistAct {

	@Autowired
	private NameService nameService;

	@RequestMapping(value="/nameExist",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> NameExist(HttpServletRequest request,char sign,String name){
		BcUser currentUser = SessionUtils.getCurrentUser(request);
		Map<String, String> map = new HashMap<String, String>();
		int count = nameService.NameExist(currentUser.getCompanyId(), sign, name);
		map.put("count",String.valueOf(count));
		return map;
	}
}
