package com.britecloud.marketingcloud.dao;


import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;


public interface ApplicatonDao {

	int existsArgsKey(ApplicationEntity args);
	int existsApplicationInfo(ApplicationEntity args);
	void saveApplication(ApplicationEntity args);
	void updateApplication(ApplicationEntity args);
	void updateApplicationInfo(ApplicationEntity args);
	void updateStatus(ApplicationEntity args);
	void updateAudit(ApplicationEntity args) ;
    PageDataResult<ApplicationEntity> listSysApplications(Map params);
    PageDataResult<ApplicationEntity> listHisVersion(Map params);

    PageDataResult<ApplicationEntity> getApplicationsByAreaNo(Map params);
}
