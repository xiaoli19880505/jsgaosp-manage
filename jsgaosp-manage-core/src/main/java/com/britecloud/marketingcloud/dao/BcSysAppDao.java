package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Map;

public interface BcSysAppDao {

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
