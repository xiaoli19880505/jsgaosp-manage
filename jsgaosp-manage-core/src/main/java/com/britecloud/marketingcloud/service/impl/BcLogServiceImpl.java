package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcLogDao;
import com.britecloud.marketingcloud.model.BcLoginLog;
import com.britecloud.marketingcloud.model.OperationLog;
import com.britecloud.marketingcloud.service.BcLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称:省公安
 * 文件名：BcLogServiceImpl.java
 * author:ge
 * 版本信息：
 * 日期：2019年4月16日
 * Copyright 紫金数云 2019
 * 版权所有
 */
@Service
public class BcLogServiceImpl implements BcLogService{

    @Autowired
    BcLogDao bcLogDao;

    @Override
    public void saveLoginLog(BcLoginLog loginLog) {
        bcLogDao.saveLoginLog(loginLog);
    }

    @Override
    public void saveOperationLog(OperationLog operationLog) {
        bcLogDao.saveOperationLog(operationLog);
    }
}
