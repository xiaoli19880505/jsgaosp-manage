
package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysArgs;

import java.util.List;
import java.util.Map;

/**
 * 项目名称:省公安
 * 说明：系统参数
 * 文件名：BcSysArgsService.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-10
 * Copyright 紫金数云 2019 版权所有
 */
public interface BcSysArgsService {

    PageDataResult<BcSysArgs> listSysArgs(Map params);

	void saveSysArgs(BcSysArgs args);

    void updateSysArgs(BcSysArgs args);

    void deleteSysArgs(BcSysArgs args);

    BcSysArgs getSysArgsById(BcSysArgs args);

    /**
     * 根据argsKey判断是否存在
     * @param args
     * @return
     */
    int existsArgsKey(BcSysArgs args);
}
