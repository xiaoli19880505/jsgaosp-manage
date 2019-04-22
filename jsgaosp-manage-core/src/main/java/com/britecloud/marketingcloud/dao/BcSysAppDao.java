package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;

import java.util.Map;

public interface BcSysAppDao {

    /**
     * 查询申报列表
     * @param params
     * @return
     */
    PageDataResult<BcSysApplicationEntity> listAppReport(Map params);
}
