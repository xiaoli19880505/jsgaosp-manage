package com.britecloud.marketingcloud.service;


public interface BCModelApiInterface {
	
	/**
	 * 根据user推荐item
	 * @param companyId
	 * @param modelId
	 * @param userId
	 * @return 返回json字符串，格式为model_param.xml定义
	 */
	public String predictItemAccordToUser(String companyId, String modelId, String userId);
		
}
