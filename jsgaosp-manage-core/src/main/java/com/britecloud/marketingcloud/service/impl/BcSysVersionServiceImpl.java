package com.britecloud.marketingcloud.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.BcSysVersionDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.service.BcSysVersionService;

@Service
public class BcSysVersionServiceImpl implements BcSysVersionService{

	@Autowired
	private BcSysVersionDao bcSysVersionDao;

	@Override
	public PageDataResult<BcSysApplicationEntity> listSysVersions(Map params) {
		// TODO Auto-generated method stub
		return bcSysVersionDao.listSysVersions(params);
	}

	@Override
	public int existsArgsKey(BcSysApplicationEntity args) {
		// TODO Auto-generated method stub
		return bcSysVersionDao.existsArgsKey(args);
	}

	@Override
	public void insertVersion(BcSysApplicationEntity args) {
		// TODO Auto-generated method stub
		bcSysVersionDao.insertVersion(args);
	}

	@Override
	public PageDataResult<BcSysApplicationEntity> listHisSysVersions(Map params) {
		// TODO Auto-generated method stub
		return bcSysVersionDao.listHisSysVersions(params);
	}

	@Override
	public void deleteVersion(BcSysApplicationEntity args) {
		// TODO Auto-generated method stub
		bcSysVersionDao.deleteVersion(args);
	}
	
	

}
