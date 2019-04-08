/** 
 * 项目名称:91营销云
 * 文件名：SecurityFilter.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;

import com.alibaba.fastjson.JSONObject;

public class SecurityFilter implements Filter {
	

	private String	unfilteredURIs;

	private String	loginURL;
	
	

	public String getUnfilteredURIs() {
		return unfilteredURIs;
	}

	public void setUnfilteredURIs(String unfilteredURIs) {
		this.unfilteredURIs = unfilteredURIs;
	}

	public String getLoginURL() {
		return loginURL;
	}

	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		
		
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		String requestURI = hrequest.getRequestURI();
		
		String contextPath = hrequest.getContextPath();
		
		requestURI = StringUtils.remove(requestURI, contextPath);
		
		HttpSession session = hrequest.getSession(false);
		ClientSession cs = null;
		if (session != null) {
			cs = (ClientSession) session.getAttribute("CLIENT_SESSION");
			if ((cs != null && cs.isLogined())) {
				ClientSessionHolder.setClientSession(cs);
				chain.doFilter(request, response);
				return;
			}
		}

		if (requestURI.matches(unfilteredURIs) || ((cs != null && cs.isLogined()))) {
			chain.doFilter(request, response);
			return;
		}

		showLoginPage(hrequest, hresponse);
	}

	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
	}

	protected String jointString(String str) {
		StringBuffer buf = new StringBuffer();
		for (StringTokenizer st = new StringTokenizer(str != null ? str : "", "\n", false); st.hasMoreTokens(); buf
				.append(st.nextToken().trim()))
			;
		return buf.toString();
	}

	private void showLoginPage(HttpServletRequest hrequest, HttpServletResponse response)
			throws UnsupportedEncodingException, ServletException, IOException {

//		String destination;
//
//		String serverURL = hrequest.getScheme() + "://" + hrequest.getServerName() + ":" + hrequest.getServerPort()
//				+ hrequest.getContextPath();
//
//		destination = (serverURL + loginURL);
//
//		response.sendRedirect(destination);
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}

}