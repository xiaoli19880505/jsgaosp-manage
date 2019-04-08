package com.britecloud.marketingcloud.api.service;

import com.britecloud.marketingcloud.model.BcAccessKey;

public interface AccessKeyService{
	
	public void createAccessKey(BcAccessKey accessKey);
	
	public BcAccessKey findByApiKey(String apiKey);

	public void deleteAccessKey(BcAccessKey accessKey); 
}
