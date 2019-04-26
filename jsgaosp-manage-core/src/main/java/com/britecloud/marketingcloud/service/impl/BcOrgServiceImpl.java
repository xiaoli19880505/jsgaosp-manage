package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.dao.BcOrgDao;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcOrg;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.BcOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BcOrgServiceImpl implements BcOrgService {

    @Autowired
    private BcOrgDao bcOrgDao;

    @Override
    public List<BcOrg> listOrg(String pOrgNo) {
        return bcOrgDao.listOrg(pOrgNo);
    }

    @Override
    public void saveOrg(BcOrg org) {
       bcOrgDao.saveOrg(org);
    }

    @Override
    public void updateOrg(BcOrg org) {
        bcOrgDao.updateOrg(org);
    }

    @Override
    public void deleteOrg(BcOrg org) {
        bcOrgDao.deleteOrg(org);
    }

    @Override
    public BcOrg getOrgById(String id) {
        return bcOrgDao.getOrgById(id);
    }

    @Override
    public Map<String, Object> getOrgByOrgNo(String orgNo) {
        return bcOrgDao.getOrgByOrgNo(orgNo);
    }

    @Override
    public int existsOrgName(BcOrg org) {
        return bcOrgDao.existsOrgName(org);
    }
}
