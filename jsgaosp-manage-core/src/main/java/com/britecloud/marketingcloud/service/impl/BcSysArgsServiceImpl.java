package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcSysArgsDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.service.BcSysArgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BcSysArgsServiceImpl implements BcSysArgsService {

    @Autowired
    private BcSysArgsDao bcSysArgsDao;

    @Override
    public PageDataResult<BcSysArgs> listSysArgs(Map params) {
        return bcSysArgsDao.listSysArgs(params);
    }

    @Override
    public void saveSysArgs(BcSysArgs args) {
        bcSysArgsDao.saveSysArgs(args);
    }

    @Override
    public void updateSysArgs(BcSysArgs args) {
        bcSysArgsDao.updateSysArgs(args);
    }

    @Override
    public void deleteSysArgs(BcSysArgs args) {
        bcSysArgsDao.deleteSysArgs(args);
    }

    @Override
    public BcSysArgs getSysArgsById(BcSysArgs args) {
        return bcSysArgsDao.getSysArgsById(args);
    }

    @Override
    public int existsArgsKey(BcSysArgs args) {
        return bcSysArgsDao.existsArgsKey(args);
    }
}
