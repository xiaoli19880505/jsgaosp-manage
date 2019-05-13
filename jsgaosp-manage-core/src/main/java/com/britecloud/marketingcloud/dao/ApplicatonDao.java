package com.britecloud.marketingcloud.dao;


import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.Pageable;


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
    void rollbackVersion(ApplicationEntity args);
    ApplicationEntity listInfoAppById(String Id );
    ApplicationEntity queryInfoList(String appId,String orgId );
    void updateInfoWorkStatus(ApplicationEntity args,String method);
    

    void insertAppInfo(ApplicationEntity args);
    PageDataResult<ApplicationEntity> getApplicationsByAreaNo(Map params);

    PageDataResult<ApplicationEntity> getCustomizeList(Map params);

    void addCustomize(Map params);

    Integer existsCustomizeApp(Map params);

    void updateCustomize(Map params);

    PageDataResult<ApplicationEntity> queryApplications(Pageable page, String sysType, String keyWord, String ywType, String xzType, String blType, String serverType, String areaNo);
}
