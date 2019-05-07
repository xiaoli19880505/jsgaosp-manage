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
	public void updateApplicationInfo(ApplicationEntity args) {
		ApplicatonDao.updateApplicationInfo(args);
	}

	@Override
	public PageDataResult<ApplicationEntity> listSysApproves(Map params) {
		return ApplicatonDao.listSysApplications(params);
	}


	@Override
	public void updateStatus(ApplicationEntity args) {
		ApplicatonDao.updateStatus(args);
		
	}


	@Override
	public void updateAudit(ApplicationEntity args) {
		ApplicatonDao.updateAudit(args);
		
	}


	@Override
	public PageDataResult<ApplicationEntity> listHisVersion(Map params) {
		// TODO Auto-generated method stub
		return ApplicatonDao.listHisVersion(params);
	}


	@Override
	public void rollbackVersion(ApplicationEntity args) {
		ApplicatonDao.rollbackVersion(args);
	}


	@Override
	public ApplicationEntity listInfoAppById(String Id) {
		// TODO Auto-generated method stub
		return ApplicatonDao.listInfoAppById(Id);
	}


	@Override
	public ApplicationEntity queryInfoList(String appId, String orgId) {
		// TODO Auto-generated method stub
		return ApplicatonDao.queryInfoList(appId, orgId);
	}


	@Override
	public void updateInfoWorkStatus(ApplicationEntity args) {
		ApplicatonDao.updateInfoWorkStatus(args);
	}
	
	
	
}
