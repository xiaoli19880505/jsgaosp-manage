package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcSysAppDao;
import com.britecloud.marketingcloud.dao.BcSysArgsDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.service.BcSysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BcSysAppServiceImpl implements BcSysAppService {
    @Autowired
    private BcSysAppDao bcSysAppDao;

    @Override
    public PageDataResult<BcSysApplicationEntity> listAppReport(Map params) {
        return bcSysAppDao.listAppReport(params);
    }

    @Override
    public void saveApp(BcSysApplicationEntity args) {
        bcSysAppDao.saveApp(args);
    }

    @Override
    public int existsAppName(BcSysApplicationEntity args) {
        return bcSysAppDao.existsAppName(args);
    }

    @Override
    public void updateSysApp(BcSysApplicationEntity args) {
        bcSysAppDao.updateSysApp(args);
    }

    @Override
    public void deleteSysApp(BcSysApplicationEntity args) {
        bcSysAppDao.deleteSysApp(args);
    }
}
