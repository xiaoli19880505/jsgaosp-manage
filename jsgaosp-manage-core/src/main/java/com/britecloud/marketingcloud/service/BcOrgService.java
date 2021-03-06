package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcOrg;

import java.util.List;
import java.util.Map;

/**
 * 组织Service
 */
public interface BcOrgService {

    List<BcOrg> listOrg(String pOrgNo);

    PageDataResult<BcOrg> listDepartmentByOrgId(Map params);

    void saveOrg(BcOrg org);

    void updateOrg(BcOrg org);

    void deleteOrg(BcOrg org);

    BcOrg getOrgById(String id);

    Map<String,Object> getOrgByOrgNo(String orgNo);

    int existsOrgName(BcOrg org);

    String getOrgAreaNameList(Integer channel, String orgNo);
}
