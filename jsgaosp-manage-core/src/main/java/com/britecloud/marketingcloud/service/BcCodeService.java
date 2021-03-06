
package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCodeSort;

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
public interface BcCodeService {

    PageDataResult<BcCodeSort> listCodeSort(Map params);

    PageDataResult<BcCodeSort> listCodeSortDetailBypCodeSortId(Map params);
    void saveCodeSort(BcCodeSort codeSort);

    void updateCodeSort(BcCodeSort codeSort);

    void deleteCodeSort(BcCodeSort codeSort);

    BcCodeSort getCodeSortById(BcCodeSort codeSort);

    String getCodeList(Integer channel, String sortCode);
}
