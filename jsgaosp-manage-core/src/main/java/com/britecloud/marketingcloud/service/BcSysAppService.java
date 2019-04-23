package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;

import java.util.Map;

public interface BcSysAppService {

    /**
     * 查询申报列表
     * @param params
     * @return
     */
    PageDataResult<BcSysApplicationEntity> listAppReport(Map params);

    void saveApp(BcSysApplicationEntity args);

    int existsAppName(BcSysApplicationEntity args);

    void updateSysApp(BcSysApplicationEntity args);

    void deleteSysApp(BcSysApplicationEntity args);
}
