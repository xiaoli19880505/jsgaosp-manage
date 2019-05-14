package com.britecloud.marketingcloud.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.BcSysApplicatonDao;
import com.britecloud.marketingcloud.dao.BcSysApproveDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.service.BcSysApplicationService;
import com.britecloud.marketingcloud.service.BcSysApproveService;

@Service
public class BcSysApproveServiceImpl implements BcSysApproveService{

	@Autowired
	private BcSysApproveDao bcSysApproveDao;
	
	@Override
	public PageDataResult<ApplicationEntity> listSysApproves(Map params) {
		// TODO Auto-generated method stub
		return bcSysApproveDao.listSysApproves(params);
	}

	

	@Override
	public void updateAudit(ApplicationEntity args) {
		// TODO Auto-generated method stub
		bcSysApproveDao.updateAudit(args);
	}

}
