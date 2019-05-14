package com.britecloud.marketingcloud.dao;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysArgs;

public interface BcSysApproveDao {

	void updateAudit(ApplicationEntity args);
	
    PageDataResult<ApplicationEntity> listSysApproves(Map params) ;
}
