
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.model.BcSysArgs;

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

}
