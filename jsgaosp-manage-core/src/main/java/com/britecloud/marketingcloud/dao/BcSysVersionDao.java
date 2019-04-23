package com.britecloud.marketingcloud.dao;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysArgs;

public interface BcSysVersionDao {

	public PageDataResult<BcSysApplicationEntity> listSysVersions(Map params);
	
	
	public PageDataResult<BcSysApplicationEntity> listHisSysVersions(Map params);
	
	
	int existsArgsKey(BcSysApplicationEntity args);
	
	void insertVersion(BcSysApplicationEntity args);
	void deleteVersion(BcSysApplicationEntity args);
	
}
