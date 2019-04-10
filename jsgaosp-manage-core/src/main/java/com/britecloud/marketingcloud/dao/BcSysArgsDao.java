
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcSysArgs;

import java.util.List;

/**
 * 项目名称:省公安
 * 说明：系统参数
 * 文件名：BcSysArgsDao.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-10
 * Copyright 紫金数云 2019 版权所有
 */
public interface BcSysArgsDao {

	List<BcSysArgs> listSysArgs(BcSysArgs args);

	void saveSysArgs(BcSysArgs args);

    void updateSysArgs(BcSysArgs BcSysArgsService);

    void deleteSysArgs(BcSysArgs BcSysArgsService);

    BcSysArgs getSysArgsById(BcSysArgs BcSysArgsService);

    /**
     * 根据argsKey判断是否存在
     * @param BcSysArgsService
     * @return
     */
    int existsArgsKey(BcSysArgs BcSysArgsService);
}
