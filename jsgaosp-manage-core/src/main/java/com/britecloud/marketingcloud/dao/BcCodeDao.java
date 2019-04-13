
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCode;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Map;

/**
 * 项目名称:省公安
 * 说明：数据字典
 * 文件名：BcCodeDao.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-10
 * Copyright 紫金数云 2019 版权所有
 */
public interface BcCodeDao {

    PageDataResult<BcCodeSort> listCodeSort(Map params);

	void saveCodeSort(BcCodeSort codeSort);

    void updateCodeSort(BcCodeSort codeSort);

    void deleteCodeSort(BcCodeSort codeSort);

    PageDataResult<BcCode> listCode(Map params);

    void saveCode(BcCode code);

    void updateCode(BcCode code);

    void deleteCode(BcCode code);
}
