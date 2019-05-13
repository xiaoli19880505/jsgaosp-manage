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
	public String getCustomizeList(Integer channel, String sysType, String idcardNo, Pageable page) {
		JSONObject result = new JSONObject();
		try{
			Map params = new HashMap();
			params.put("page", page.getPage());
			params.put("pageSize", page.getSize());
			params.put("idcardNo", idcardNo);
			params.put("sys_type", sysType);
			PageDataResult<ApplicationEntity> apps = ApplicatonDao.getCustomizeList(params);
			result= JSONObject.fromObject(apps);
			result.put("code", Constants.RESPONSE_SUCCESS_CODE);
			result.put("message","成功");
		}catch(Exception e){
			result.put("code", Constants.RESPONSE_BAD_CODE);
			result.put("message","异常");
		}
		return result.toString();
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
	public void updateInfoWorkStatus(ApplicationEntity args,String method) {
		ApplicatonDao.updateInfoWorkStatus(args,method);
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


	@Override
	public void insertAppInfo(ApplicationEntity args) {
		// TODO Auto-generated method stub
		ApplicatonDao.insertAppInfo(args);
	}


	@Override
	public String addCustomize(Integer channel, String idcardNo, String appId) {
		JSONObject result = new JSONObject();
		try{
			Map params = new HashMap();
			params.put("idcardNo",idcardNo);
			params.put("appId", appId);
			Integer hasApp = ApplicatonDao.existsCustomizeApp(params);
			if(hasApp !=null && hasApp > 0){
				ApplicatonDao.updateCustomize(params);
			}else{
				ApplicatonDao.addCustomize(params);
			}

			result.put("code", Constants.RESPONSE_SUCCESS_CODE);
			result.put("message","成功");
		}catch(Exception e){
			result.put("code", Constants.RESPONSE_BAD_CODE);
			result.put("message","异常");
		}
		return result.toString();
	}

	@Override
	public String queryApplications(Integer channel, Pageable page, String sysType, String keyWord, String ywType, String xzType, String blType, String serverType, String areaNo) {
		JSONObject result = new JSONObject();
		try{
			Map params = new HashMap();
			params.put("page", page.getPage());
			params.put("pageSize", page.getSize());
			params.put("sysType", sysType);
			params.put("keyWord", keyWord);
			params.put("ywType", ywType);
			params.put("xzType", xzType);
			params.put("blType", blType);
			params.put("serverType", serverType);
			params.put("areaNo", areaNo);
			PageDataResult<ApplicationEntity> apps = ApplicatonDao.queryApplications(page, sysType, keyWord, ywType, xzType, blType, serverType, areaNo);
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
