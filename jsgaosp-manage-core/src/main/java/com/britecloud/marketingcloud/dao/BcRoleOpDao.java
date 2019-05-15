
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.BcRoleOp;

import java.util.List;
import java.util.Map;


public interface BcRoleOpDao {

    void setRoleOp(BcRoleOp bcRoleOp);

    void cleanRoleOpByRoleId(String roleId);


    List<BcRoleOp> getRoleOp(Map params);

}
