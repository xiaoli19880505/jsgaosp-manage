/** 
 * 项目名称:91营销云
 * 文件名：SecurityInterceptor.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.LoggerFactory;
import com.britecloud.marketingcloud.core.security.ClientSession;
import com.britecloud.marketingcloud.core.security.ClientSessionHolder;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class);
	
	
	
	private String unfilteredURIs;
	private Pattern regex = null;

	
	public SecurityInterceptor() {
	}
	
	public void setUnfilteredURIs(String unfilteredURIs) {
		this.unfilteredURIs = unfilteredURIs;
		regex = Pattern.compile(unfilteredURIs);
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (LOG.isDebugEnabled()){
			LOG.debug(request.getRequestURI());
		}
		
		String requestURI = request.getRequestURI();
		
		if ( requestURI.equals("/") ){
			//response.sendRedirect("/app/index.html/#login");
			return true;
		}
		
		if (regex.matcher(requestURI).matches()) {
			return true;
		}

		HttpSession session = request.getSession(false);
		ClientSession cs = null;
		if (session != null) {
			cs = (ClientSession) session.getAttribute("CLIENT_SESSION");
			if ((cs != null && cs.isLogined())) {
				ClientSessionHolder.setClientSession(cs);
				return true;
			}
		}

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return false;
	}
	
	
}