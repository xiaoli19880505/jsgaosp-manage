package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.BcLoginLog;
import com.britecloud.marketingcloud.model.OperationLog;

public interface BcLogDao {

    void saveLoginLog(BcLoginLog loginLog);

    void saveOperationLog(OperationLog operationLog);
}
