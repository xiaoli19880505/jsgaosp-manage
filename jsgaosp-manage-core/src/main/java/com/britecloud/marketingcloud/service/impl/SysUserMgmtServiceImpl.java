/** 
 * 项目名称:91营销云
 * 文件名：SysUserMgmtServiceImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service.impl;

import java.util.Map;

import com.britecloud.marketingcloud.dao.BcLogDao;
import com.britecloud.marketingcloud.model.BcLoginLog;
import com.britecloud.marketingcloud.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.SysUserJdbcDao;
import com.britecloud.marketingcloud.dao.impl.SysUserJdbcDaoImpl;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.service.SysUserMgmtService;

/**
 * 项目名称:医院罗盘 文件名：SysUserMgmtServiceImpl.java author:ge 版本信息： 日期：2015年8月4日
 * Copyright 颢云科技 2015 版权所有
 */
@Service
public class SysUserMgmtServiceImpl implements SysUserMgmtService {

    @Autowired
    private SysUserJdbcDao sysUserJdbcDao;

    @Autowired
    private BcLogDao bcLogDao;

    public void setUserJdbcDao(SysUserJdbcDaoImpl sysUserJdbcDao) {
        this.sysUserJdbcDao = sysUserJdbcDao;
    }

    public void createUser(BcUser user) {
        sysUserJdbcDao.createUser(user);
    }

    public void updateUser(BcUser user) {
        sysUserJdbcDao.updateUser(user);
    }

    public void deleteUser(String userId) {
        sysUserJdbcDao.deleteUser(userId);
    }

    public BcUser getUser(String userId) {
        return sysUserJdbcDao.getUser(userId);
    }

    @Override
    public Map listSysUser(Map params) {
        return sysUserJdbcDao.listSysUser(params);
    }



    @Override
    public BcUser login(String email, String passwd) {
        if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(passwd)) {
        	BcUser user = sysUserJdbcDao.getUserByEmail(email);
            if (user != null)
                if (user.getUserPwd().equals(passwd)) {
                    //保存登录日志
                    BcLoginLog loginLog = new BcLoginLog();
                    loginLog.setType("System");
                    loginLog.setTitle("用户登录");
                    loginLog.setContent(String.format("登录用户名：%s,userid:%s", user.getUserEmail(),user.getUserId()));
                    String time = DateUtils.currentDateToString(DateUtils.DEFAULT_DATETIME_FORMAT);
                    loginLog.setTriggerTime(time);
                    loginLog.setSource(user.getUserEmail());
                    bcLogDao.saveLoginLog(loginLog);
                    return user;
                }
        }
        return null;
    }

    @Override
    public void resetPwd(String userId, String pwd) {
        sysUserJdbcDao.resetPwd(userId, pwd);
    }

	@Override
	public int getCountByCompanyId(String companyId) {
		return sysUserJdbcDao.getCountByCompanyId(companyId);
	}
	@Override
	public int checkEmail(String email) {
		return sysUserJdbcDao.checkEmail(email);
	}

}
