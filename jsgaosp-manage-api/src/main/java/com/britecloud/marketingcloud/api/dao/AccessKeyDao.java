package com.britecloud.marketingcloud.api.dao;

import com.britecloud.marketingcloud.model.BcAccessKey;

public interface AccessKeyDao {
	public void createAccessKey(BcAccessKey accessKey);

	public BcAccessKey findByApiKey(String apiKey);

	public void deleteAccessKey(BcAccessKey accessKey);
}
