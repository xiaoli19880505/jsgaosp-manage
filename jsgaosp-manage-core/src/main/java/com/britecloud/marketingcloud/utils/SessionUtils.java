/** 
 * 项目名称:91营销云
 * 文件名：SessionUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.britecloud.marketingcloud.core.security.ClientSession;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.utils.ClientUtils;

public class SessionUtils {
	public static BcUser getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(true);
    	ClientSession cs = (ClientSession)session.getAttribute(ClientUtils.CLIENT_SESSION);
    	BcUser user = (BcUser)cs.getAttribute(ClientUtils.CURRENT_USER);
    	return user;
	}
}
