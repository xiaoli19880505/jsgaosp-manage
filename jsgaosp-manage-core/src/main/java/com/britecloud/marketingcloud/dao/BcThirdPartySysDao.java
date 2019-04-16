package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcThirdPartySysEntity;

import java.util.Map;

public interface BcThirdPartySysDao {

	 PageDataResult<BcThirdPartySysEntity> listThirdPartySys(Map params);
	
	void saveSysArgs(BcThirdPartySysEntity args);
	
	int existsArgsKey(BcThirdPartySysEntity args);
	
	void updateThirdPartySys(BcThirdPartySysEntity args);
	
	void deleteThirdPartySys(BcThirdPartySysEntity args);
}