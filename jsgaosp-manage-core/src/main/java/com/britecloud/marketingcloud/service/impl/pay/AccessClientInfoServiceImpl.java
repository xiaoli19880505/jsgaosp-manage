/**
 * 项目名称:91营销云
 * 文件名：SysUserMgmtServiceImpl.java
 * author:Administrator
 * 版本信息：
 * 日期：2015-12-14
 * Copyright 颢云科技 2015 版权所有
 */
package com.britecloud.marketingcloud.service.impl.pay;

import com.britecloud.marketingcloud.dao.pay.AccessClientInfoDao;
import com.britecloud.marketingcloud.model.pay.AccessClientInfoPO;
import com.britecloud.marketingcloud.model.pay.AccessConfPO;
import com.britecloud.marketingcloud.service.pay.AccessClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 项目名称:医院罗盘 文件名：SysUserMgmtServiceImpl.java author:ge 版本信息： 日期：2015年8月4日
 * Copyright 颢云科技 2015 版权所有
 */
@Service
public class AccessClientInfoServiceImpl implements AccessClientInfoService {

    @Autowired
    private AccessClientInfoDao clientInfoDao;


    public void create(AccessClientInfoPO clientInfoPO) {
        clientInfoDao.create(clientInfoPO);
    }

    public void update(AccessClientInfoPO clientInfoPO) {
        clientInfoDao.update(clientInfoPO);
    }

    public void delete(String id) {
        clientInfoDao.delete(id);
    }

    public AccessClientInfoPO get(String id) {
        return clientInfoDao.get(id);
    }

    @Override
    public Map list(Map params) {
        return clientInfoDao.list(params);
    }


}
