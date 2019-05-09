
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcOrg;

import java.util.List;
import java.util.Map;


public interface BcOrgDao {

	List<BcOrg> listOrg(String pOrgNo);

    PageDataResult<BcOrg> listDepartmentByOrgId(Map params);

	void saveOrg(BcOrg org);

    void updateOrg(BcOrg org);

    void deleteOrg(BcOrg org);

    BcOrg getOrgById(String id);

    Map<String,Object> getOrgByOrgNo(String orgNo);

    /**
     * 根据orgName判断是否存在
     * @param org
     * @return
     */
    int existsOrgName(BcOrg org);

    List<BcOrg> getOrgAreaNameList();

    BcOrg queryOrgById(String orgNo);

    List<BcOrg> queryOrgListByPid(String orgNo);
}
