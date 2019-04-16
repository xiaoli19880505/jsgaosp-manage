package com.britecloud.marketingcloud.service;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysArgs;

public interface BcSysApproveService {

	PageDataResult<BcSysApplicationEntity> listSysApproves(Map params);
	
	void saveSysArgs(BcSysApplicationEntity args);
	int existsArgsKey(BcSysApplicationEntity args);
	void updateApprove(BcSysApplicationEntity args);
	void deleteSysApplication(BcSysApplicationEntity args);
}
