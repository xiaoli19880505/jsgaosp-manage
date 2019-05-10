package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.BcLinkInfo;

import java.util.List;

public interface BcLinkInfoDao {
    List<BcLinkInfo> getLinkInfoList(String linkType);
}
