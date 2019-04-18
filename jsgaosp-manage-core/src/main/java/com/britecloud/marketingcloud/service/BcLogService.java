package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.model.BcLoginLog;
import com.britecloud.marketingcloud.model.OperationLog;

public interface BcLogService {

    void saveLoginLog(BcLoginLog loginLog);

    void saveOperationLog(OperationLog operationLog);

}
