/** 
 * 项目名称:91营销云
 * 文件名：ClientUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.britecloud.marketingcloud.core.security.ClientSession;
import com.britecloud.marketingcloud.core.security.ClientSessionHolder;
import com.britecloud.marketingcloud.model.BcCompany;
import com.britecloud.marketingcloud.model.BcUser;

public class ClientUtils {
	public static String CLIENT_SESSION = "CLIENT_SESSION";
	public static String ACCESS_TOKEN = "ACCESS_TOKEN";
	public static String CURRENT_COMPANY = "CURRENT_COMPANY";
	public static String CURRENT_ROLE = "CURRENT_ROLE";
	public static String CURRENT_USER = "CURRENT_USER";

	public static ClientSession getCS() {
		return ClientSessionHolder.getClientSession();
	}

	public static Object get(String key) {
		if (getCS() == null) {
			return null;
		}
		return getCS().getAttribute(key);
	}
	
	public static String getCurrentCompanyId(){
		BcCompany company = (BcCompany)ClientUtils.getCS().getAttribute(ClientUtils.CURRENT_COMPANY);
		return company.getCompanyId();
	}

	public static String getCurrentUserId(){
		BcUser user = (BcUser)ClientUtils.getCS().getAttribute(ClientUtils.CURRENT_USER);
		return user.getUserId();
	}


	public static void set(String key, Object value) {
		getCS().addAttribute(key, value);
	}

	public static void remove(String key) {
		getCS().removeAttribute(key);
	}


	/**
	 * 抹去客户端登录信息
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param resp
	 */
	public static void forgetme(HttpServletRequest request, HttpServletResponse resp) {
		request.getSession().invalidate();
	}

}
