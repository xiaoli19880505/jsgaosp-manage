/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   AccessKeyDao.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.BcAccessKey;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;

/**
 * The Interface AccessKeyDao.
 */
public interface AccessKeyDao {
	
	/**
	 * Creates the access key.
	 *
	 * @param accessKey the access key
	 */
	public void createAccessKey(BcAccessKey accessKey);

	/**
	 * Find by api key.
	 *
	 * @param apiKey the api key
	 * @return the bc access key
	 */
	public BcAccessKey findByApiKey(String companyId,String apiKey);

	/**
	 * Find by companyId.
	 * @param companyId
	 * @return
	 */
	public BcAccessKey findByCompanyId(String companyId);
	/**
	 * Delete access key.
	 *
	 * @param apiKeys the api keys
	 */
	public void deleteAccessKey(String companyId,String apiKeys);
	
	/**
	 * Find all access key.
	 *
	 * @param companyId the company id
	 * @param query the query
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<BcAccessKey> findAllAccessKey(String companyId,String query, Pageable pageable);
	
	/**
	 * Update accesskey.
	 *
	 * @param accessKey the access key
	 */
	public void updateAccesskey(String companyId,BcAccessKey accessKey);
}
