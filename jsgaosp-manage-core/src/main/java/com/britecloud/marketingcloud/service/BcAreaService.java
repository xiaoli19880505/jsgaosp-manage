package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.model.BcArea;

import java.util.List;
import java.util.Map;

/**
 * 行政区划Service
 */
public interface BcAreaService {

    List<BcArea> listArea(String pAreaNo);

    void saveArea(BcArea area);

    void updateArea(BcArea area);

    void deleteArea(BcArea area);

    BcArea getAreaById(String id);

    Map<String,Object> getAreaByAreaNo(String areaNo);

    /**
     * 根据areaNo判断是否存在
     * @param area
     * @return
     */
    int existsAreaNo(BcArea area);

    String getAreaList();
}
