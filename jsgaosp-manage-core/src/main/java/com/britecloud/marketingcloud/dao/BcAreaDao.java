
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.BcArea;

import java.util.List;

/**
 * 项目名称:省公安
 * 文件名：BcAreaDao.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-9
 * Copyright 紫金数云 2019 版权所有
 */
public interface BcAreaDao {

	List<BcArea> listArea(BcArea area);

	void saveArea(BcArea area);

    void updateArea(BcArea area);

    void deleteArea(BcArea area);

    BcArea getAreaById(String id);

    /**
     * 根据areaNo判断是否存在
     * @param area
     * @return
     */
    int existsAreaNo(BcArea area);
}
