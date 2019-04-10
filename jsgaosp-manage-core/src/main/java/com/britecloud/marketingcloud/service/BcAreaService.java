package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.model.BcArea;

import java.util.List;

/**
 * 行政区划Service
 */
public interface BcAreaService {

    List<BcArea> listArea(BcArea area);

    void saveArea(BcArea area);

    void updateArea(BcArea area);

    void deleteArea(BcArea area);

    BcArea getAreaById(String id);
}
