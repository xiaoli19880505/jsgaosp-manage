package com.britecloud.marketingcloud.service;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysArgs;

public interface BcSysApproveService {

	void updateAudit(ApplicationEntity args) ;
	PageDataResult<ApplicationEntity> listSysApproves(Map params);
}
