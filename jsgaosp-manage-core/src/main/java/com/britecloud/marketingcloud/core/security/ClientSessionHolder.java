/** 
 * 项目名称:91营销云
 * 文件名：ClientSessionHolder.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.security;

public class ClientSessionHolder {

	static ThreadLocal clientSessionList = new ThreadLocal();

	public static void setClientSession(ClientSession clientSession) {
		clientSessionList.set(clientSession);
	}

	public static ClientSession getClientSession() {
		return (ClientSession) clientSessionList.get();
	}

}
