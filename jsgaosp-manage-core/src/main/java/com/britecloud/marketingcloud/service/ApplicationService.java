package com.britecloud.marketingcloud.service;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.Pageable;

public interface ApplicationService {

	boolean existsArgsKey(ApplicationEntity args);
	void saveApplication(ApplicationEntity args);
    void updateApplicationInfo(ApplicationEntity args);
    void updateApplication(ApplicationEntity args);
    void updateStatus(ApplicationEntity args);
    void updateAudit(ApplicationEntity args) ;
    PageDataResult<ApplicationEntity> listSysApproves(Map params);
    PageDataResult<ApplicationEntity> listHisVersion(Map params);

    String getApplicationList(Integer channel, String sysType,Pageable page);
}
