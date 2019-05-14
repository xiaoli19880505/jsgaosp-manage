/** 
 * 项目名称:91营销云
 * 文件名：SysUserMgmtServiceImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service.impl.pay;

import com.britecloud.marketingcloud.dao.pay.AccessApprovalDao;
import com.britecloud.marketingcloud.model.pay.AccessApprovalPO;
import com.britecloud.marketingcloud.model.pay.AccessConfPO;
import com.britecloud.marketingcloud.service.pay.AccessApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 项目名称:医院罗盘 文件名：SysUserMgmtServiceImpl.java author:ge 版本信息： 日期：2015年8月4日
 * Copyright 颢云科技 2015 版权所有
 */
@Service
public class AccessApprovalServiceImpl implements AccessApprovalService {

    @Autowired
    private AccessApprovalDao accessApprovalDao;


    public void create(AccessApprovalPO approvalPO) {
        accessApprovalDao.create(approvalPO);
    }

    public void update(AccessApprovalPO approvalPO) {
        accessApprovalDao.update(approvalPO);
    }

    public void delete(String id) {
        accessApprovalDao.delete(id);
    }

    public AccessApprovalPO get(String id) {
        return accessApprovalDao.get(id);
    }

    @Override
    public Map list(Map params) {
        return accessApprovalDao.list(params);
    }


}
