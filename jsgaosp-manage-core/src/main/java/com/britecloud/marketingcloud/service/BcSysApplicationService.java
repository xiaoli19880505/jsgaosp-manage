package com.britecloud.marketingcloud.service;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysArgs;

public interface BcSysApplicationService {

	PageDataResult<BcSysApplicationEntity> listSysApplications(Map params);
	
	void saveSysArgs(BcSysApplicationEntity args);
	int existsArgsKey(BcSysApplicationEntity args);
	void updateSysApplication(BcSysApplicationEntity args);
	void deleteSysApplication(BcSysApplicationEntity args);
}
