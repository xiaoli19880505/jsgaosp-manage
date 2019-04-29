package com.britecloud.marketingcloud.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.ApplicatonDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	private ApplicatonDao ApplicatonDao;

	@Override
	public void saveApplication(ApplicationEntity args) {
		ApplicatonDao.saveApplication(args);
		
	}


	@Override
	public boolean existsArgsKey(ApplicationEntity args) {
		// TODO Auto-generated method stub
		int num = ApplicatonDao.existsArgsKey(args);
		if (num > 0) {
			return  false;
		}else {
			return true;
		}
	}

	@Override
	public void updateApplication(ApplicationEntity args) {
		ApplicatonDao.updateApplication(args);
	}

	@Override
	public PageDataResult<ApplicationEntity> listSysApproves(Map params) {
		return ApplicatonDao.listSysApplications(params);
	}
	
	
}
