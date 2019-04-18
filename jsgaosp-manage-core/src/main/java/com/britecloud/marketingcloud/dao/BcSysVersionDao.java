package com.britecloud.marketingcloud.dao;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysArgs;

public interface BcSysVersionDao {

	public PageDataResult<BcSysApplicationEntity> listSysVersions(Map params);
	
	
	int existsArgsKey(BcSysApplicationEntity args);
	
	void updateVersion(BcSysApplicationEntity args);
	
}
