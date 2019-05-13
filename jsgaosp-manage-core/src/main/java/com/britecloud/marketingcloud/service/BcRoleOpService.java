package com.britecloud.marketingcloud.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.model.BcRoleOp;

import java.util.List;
import java.util.Map;

public interface BcRoleOpService {

    void setRoleOp(Map params);

    List<BcRoleOp> getRoleOpList(String roleId);
}
