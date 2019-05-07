package com.britecloud.marketingcloud.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.model.Pageable;
import net.sf.json.JSONObject;
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
	public String getApplicationList(Integer channel, String sysType,Pageable page) {
		JSONObject result = new JSONObject();
		try{
			Map params = new HashMap();
			params.put("page", page.getPage());
			params.put("pageSize", page.getSize());
			params.put("areaNo", "324");
			params.put("sys_type", sysType);
			PageDataResult<ApplicationEntity> apps = ApplicatonDao.getApplicationsByAreaNo(params);
			result= JSONObject.fromObject(apps);
			result.put("code", Constants.RESPONSE_SUCCESS_CODE);
			result.put("message","成功");
		}catch(Exception e){
			result.put("code", Constants.RESPONSE_BAD_CODE);
			result.put("message","异常");
		}
		return result.toString();
	}


}
