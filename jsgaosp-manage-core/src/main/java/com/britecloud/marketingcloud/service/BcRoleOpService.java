package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.model.BcRoleOp;

import java.util.List;
import java.util.Map;

public interface BcRoleOpService {

    void setRoleOp(Map params);

    List<BcRoleOp> getRoleOpList(String roleId);
}
