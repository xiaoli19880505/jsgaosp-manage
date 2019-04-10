package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.dao.BcSysArgsDao;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.BcSysArgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BcSysArgsServiceImpl implements BcSysArgsService {

    @Autowired
    private BcSysArgsDao bcSysArgsDao;

    @Override
    public List<BcSysArgs> listSysArgs(BcSysArgs args) {
        return bcSysArgsDao.listSysArgs(args);
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
