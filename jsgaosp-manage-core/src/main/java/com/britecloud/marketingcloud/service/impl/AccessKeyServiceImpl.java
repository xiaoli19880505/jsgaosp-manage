/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   AccessKeyServiceImpl.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.AccessKeyDao;
import com.britecloud.marketingcloud.model.BcAccessKey;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;
import com.britecloud.marketingcloud.service.AccessKeyService;
import com.britecloud.marketingcloud.utils.ClientUtils;
import com.britecloud.marketingcloud.utils.IDUtils;

@Service
public class AccessKeyServiceImpl implements AccessKeyService {

	@Autowired
	private AccessKeyDao accessKeyDao; 

	/**
	 * Instantiates a new access key service impl.
	 */
	public AccessKeyServiceImpl() {

	}

	public void createAccessKey(BcAccessKey accessKey) {
		accessKey.setApiKey(IDUtils.getId());
		accessKey.setPrivateKey(IDUtils.getObjectId());
		accessKey.setCompanyId(ClientUtils.getCurrentCompanyId());

		accessKeyDao.createAccessKey(accessKey);

	}

	public BcAccessKey findByApiKey(String companyId,String apiKey) {

		return accessKeyDao.findByApiKey(companyId,apiKey);
	}
	
	@Override
	public BcAccessKey findByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return accessKeyDao.findByCompanyId(companyId);
	}
	@Override
	public void deleteAccessKey(String companyId,String apiKeys) {
		accessKeyDao.deleteAccessKey(companyId,apiKeys);
	}

	@Override
	public Page<BcAccessKey> findAllAccessKey(String companyId,String query,Pageable pageable) {
		return accessKeyDao.findAllAccessKey(companyId,query,pageable);
	}

	@Override
	public void updateAccessKey(String companyId,BcAccessKey accessKey) {
		accessKeyDao.updateAccesskey(companyId,accessKey);
	}

	

}
