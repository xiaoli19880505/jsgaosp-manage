/**
 * 项目名称:91营销云
 * 文件名：SysUserMgmtService.java
 * author:Administrator
 * 版本信息：
 * 日期：2015-12-14
 * Copyright 颢云科技 2015 版权所有
 */
package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.model.PayAppConf;

import java.util.Map;

/**
 * 项目名称:支付应用接入管理
 * 文件名：SysUserMgmtService.java
 * author:ge
 * 版本信息：
 * 日期：2015年8月4日
 * Copyright 颢云科技 2015
 * 版权所有
 */
public interface PayAppConfService {
    void createPayAppConf(PayAppConf payAppConf);

    void updatePayAppConf(PayAppConf user);

    void deletePayAppConf(String id);

    PayAppConf getPayAppConf(String id);

    Map listPayConf(Map params);
}
