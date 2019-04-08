/** 
 * 项目名称:91营销云
 * 文件名：SysUserMgmtService.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service;

import java.util.Map;

import com.britecloud.marketingcloud.model.BcUser;

/**  
 * 项目名称:医院罗盘
 * 文件名：SysUserMgmtService.java    
 * author:ge  
 * 版本信息：    
 * 日期：2015年8月4日    
 * Copyright 颢云科技 2015    
 * 版权所有    
*/
public interface SysUserMgmtService {
	public void createUser(BcUser user);

	public void updateUser(BcUser user);

	public void deleteUser(String userId);

	public BcUser getUser(String userId);
	
	public Map listSysUser(Map params);
	
	public void resetPwd(String userId,String pwd);
	
////public List<SysRole> queryRoles(String userId);

	public BcUser login(String email,String passwd);

	public int getCountByCompanyId(String companyId);
	
	public int checkEmail(String email);
}
