package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.BcLoginLog;

public interface BcLogDao {

    void saveLoginLog(BcLoginLog loginLog);

}
