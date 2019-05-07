/** 
 * 项目名称:91营销云
 * 文件名：SysUserMgmtServiceImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.PayAppConfDao;
import com.britecloud.marketingcloud.model.PayAppConf;
import com.britecloud.marketingcloud.service.PayAppConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 项目名称:医院罗盘 文件名：SysUserMgmtServiceImpl.java author:ge 版本信息： 日期：2015年8月4日
 * Copyright 颢云科技 2015 版权所有
 */
@Service
public class PayAppConfServiceImpl implements PayAppConfService {

    @Autowired
    private PayAppConfDao payAppConfDao;


    public void createPayAppConf(PayAppConf payAppConf) {
        payAppConfDao.createPayAppConf(payAppConf);
    }

    public void updatePayAppConf(PayAppConf payAppConf) {
        payAppConfDao.updatePayAppConf(payAppConf);
    }

    public void deletePayAppConf(String id) {
        payAppConfDao.deletePayAppConf(id);
    }

    public PayAppConf getPayAppConf(String id) {
        return payAppConfDao.getPayAppConf(id);
    }

    @Override
    public Map listPayConf(Map params) {
        return payAppConfDao.listPayConf(params);
    }


}
