/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   GlobalAct.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.britecloud.marketingcloud.core.security.ClientSession;
import com.britecloud.marketingcloud.core.security.ClientSessionHolder;
import com.britecloud.marketingcloud.model.BcCompany;
import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.SysCompanyMgmtService;
import com.britecloud.marketingcloud.service.SysRoleMgmtService;
import com.britecloud.marketingcloud.service.SysRoleUserMgmtService;
import com.britecloud.marketingcloud.service.SysUserMgmtService;
import com.britecloud.marketingcloud.utils.ClientUtils;
import com.britecloud.marketingcloud.utils.SessionUtils;
import com.britecloud.marketingcloud.utils.TokenProcessor;

import net.sf.json.JSONObject;

@Controller
public class GlobalAct {
	@Autowired
	private SysUserMgmtService sysUserMgmtService;
	@Autowired
	private SysRoleMgmtService sysRoleMgmtService;
	@Autowired
	private SysCompanyMgmtService sysCompanyService;
    @Autowired
    private SysRoleUserMgmtService sysRoleUserMgmtService;

	@RequestMapping(value = "/global/in", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) throws Exception {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		JSONObject o = new JSONObject();
		BcUser user = sysUserMgmtService.login(email, password);
		if (user != null) {
			o.put("auth", true);
			String token = TokenProcessor.getInstance().saveToken(request);
			o.put("token", token);

			HttpSession session = request.getSession(true);
			ClientSession cs = (ClientSession) session.getAttribute(ClientUtils.CLIENT_SESSION);
			if (null == cs) {
				cs = new ClientSession();
			}
			BcCompany bcCompany=sysCompanyService.getCompanyInfo(user.getCompanyId());
			BcRole bcRole=sysRoleMgmtService.getRoleByUserId(user.getUserId());
			o.put("role", bcRole);
			o.put("user", user);
			o.put("company", bcCompany);
			
			cs.addAttribute(ClientUtils.CURRENT_COMPANY, bcCompany);
			cs.addAttribute(ClientUtils.CURRENT_ROLE, bcRole);
			cs.addAttribute(ClientUtils.CURRENT_USER, user);
			cs.setLogined(true);
			ClientSessionHolder.setClientSession(cs);
			session.setAttribute(ClientUtils.CLIENT_SESSION, cs);
		} else {
			o.put("auth", false);
		}
		return o.toString();
	}

	@RequestMapping(value = "/global/out", method = RequestMethod.GET)
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		ClientUtils.forgetme(request, resp);
		JSONObject o = new JSONObject();
		return o.toString();
	}

	@RequestMapping(value = "/global/info", method = RequestMethod.GET)
	@ResponseBody
	public Map getinfo(HttpServletRequest request) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession(true);
		ClientSession cs = (ClientSession) session.getAttribute(ClientUtils.CLIENT_SESSION);
		if (cs != null) {
			result.put("role",(BcRole) cs.getAttribute(ClientUtils.CURRENT_ROLE));
			result.put("user",(BcUser) cs.getAttribute(ClientUtils.CURRENT_USER));
			result.put("company", (BcCompany) cs.getAttribute(ClientUtils.CURRENT_COMPANY));
			result.put("roles", sysRoleUserMgmtService.listRoleByUserId(((BcUser) cs.getAttribute(ClientUtils.CURRENT_USER)).getUserId()));
		} else {
			result.put("error", "no session!");
		}
		return result;
	}

	@RequestMapping(value = "/global/user", method = RequestMethod.PUT)
	@ResponseBody
	public BcUser update(BcUser user, HttpServletRequest request) throws Exception {
		BcUser currentUser = SessionUtils.getCurrentUser(request);
		user.setUpdateBy(currentUser.getUserId());
		user.setUpdateDate(new Date());
		sysUserMgmtService.updateUser(user);
		HttpSession session = request.getSession(true);
		ClientSession cs = (ClientSession) session.getAttribute(ClientUtils.CLIENT_SESSION);
		if (cs != null) {
			cs.addAttribute(ClientUtils.CURRENT_USER, user);
		}
		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		return "forward:/index.html";
	}
}
