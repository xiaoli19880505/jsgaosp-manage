package com.britecloud.marketingcloud.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.BcSysApplicatonDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.service.BcSysApplicationService;

@Service
public class BcSysApplicationServiceImpl implements BcSysApplicationService{

	@Autowired
	private BcSysApplicatonDao BcSysApplicatonDao;
	
	@Override
	public PageDataResult<BcSysApplicationEntity> listSysApplications(Map params) {
		// TODO Auto-generated method stub
		return BcSysApplicatonDao.listSysApplications(params);
	}

	@Override
	public void saveSysArgs(BcSysApplicationEntity args) {
		BcSysApplicatonDao.saveSysArgs(args);
		
	}

	@Override
	public int existsArgsKey(BcSysApplicationEntity args) {
		// TODO Auto-generated method stub
		return BcSysApplicatonDao.existsArgsKey(args);
	}

}
