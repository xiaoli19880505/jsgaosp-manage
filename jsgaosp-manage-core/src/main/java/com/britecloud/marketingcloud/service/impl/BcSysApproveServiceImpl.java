package com.britecloud.marketingcloud.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.BcSysApplicatonDao;
import com.britecloud.marketingcloud.dao.BcSysApproveDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.service.BcSysApplicationService;
import com.britecloud.marketingcloud.service.BcSysApproveService;

@Service
public class BcSysApproveServiceImpl implements BcSysApproveService{

	@Autowired
	private BcSysApproveDao bcSysApproveDao;
	
	@Override
	public PageDataResult<BcSysApplicationEntity> listSysApproves(Map params) {
		// TODO Auto-generated method stub
		return bcSysApproveDao.listApproves(params);
	}

	@Override
	public void saveSysArgs(BcSysApplicationEntity args) {
		bcSysApproveDao.saveSysArgs(args);
		
	}

	@Override
	public int existsArgsKey(BcSysApplicationEntity args) {
		// TODO Auto-generated method stub
		return bcSysApproveDao.existsArgsKey(args);
	}

	@Override
	public void updateApprove(BcSysApplicationEntity args) {
		bcSysApproveDao.updateApprove(args);
		
	}

	@Override
	public void deleteSysApplication(BcSysApplicationEntity args) {

		bcSysApproveDao.deleteSysApplication(args);
	}

}
