/**
 * 项目名称:91营销云
 * 文件名：SysUserMgmtServiceImpl.java
 * author:Administrator
 * 版本信息：
 * 日期：2015-12-14
 * Copyright 颢云科技 2015 版权所有
 */
package com.britecloud.marketingcloud.service.impl.pay;

import com.britecloud.marketingcloud.consants.pay.CommonConstants;
import com.britecloud.marketingcloud.dao.impl.pay.AccessConfDaoImpl;
import com.britecloud.marketingcloud.dao.pay.AccessApprovalDao;
import com.britecloud.marketingcloud.dao.pay.AccessConfDao;
import com.britecloud.marketingcloud.model.pay.AccessApprovalPO;
import com.britecloud.marketingcloud.model.pay.AccessConfPO;
import com.britecloud.marketingcloud.service.pay.AccessConfService;
import com.britecloud.marketingcloud.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 项目名称:医院罗盘 文件名：SysUserMgmtServiceImpl.java author:ge 版本信息： 日期：2015年8月4日
 * Copyright 颢云科技 2015 版权所有
 */
@Service
public class AccessConfServiceImpl implements AccessConfService {

    @Autowired
    private AccessConfDao accessConfDao;

    @Autowired
    private AccessApprovalDao accessApprovalDao;

    public void create(AccessConfPO payAppConf) {
        accessConfDao.create(payAppConf);
    }

    @Override
    public void approval(AccessApprovalPO approvalPO) {
        //1.增加审理记录
        accessApprovalDao.create(approvalPO);
        //2.修改接入记录状态和生成平台分配公钥
        AccessConfPO accessConfPO = new AccessConfPO();
        accessConfPO.setId(approvalPO.getApplyId());
        accessConfPO.setStatus(CommonConstants.ACCESS_STATUS_NOPASS);
        if (CommonConstants.APPROVAL_STATUS_PASS.equals(approvalPO.getApprovalStatus())) {
            //审批通过生成平台公公私钥
            accessConfPO.setServerPublicKey(RSAUtil.getPublicKey());
            accessConfPO.setServerPrivateKey(RSAUtil.getPrivateKey());
            accessConfPO.setStatus(CommonConstants.ACCESS_STATUS_PASS);
        }
        accessConfDao.update(accessConfPO);
    }

    public void update(AccessConfPO payAppConf) {
        accessConfDao.update(payAppConf);
    }

    public void delete(String id) {
        accessConfDao.delete(id);
    }

    public AccessConfPO get(String id) {
        return accessConfDao.get(id);
    }

    @Override
    public Map list(Map params) {
        return accessConfDao.list(params);
    }

}
