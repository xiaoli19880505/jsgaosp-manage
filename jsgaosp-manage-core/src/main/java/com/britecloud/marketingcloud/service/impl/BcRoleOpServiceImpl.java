package com.britecloud.marketingcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.dao.BcRoleOpDao;
import com.britecloud.marketingcloud.dao.SysRoleJdbcDao;
import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.model.BcRoleOp;
import com.britecloud.marketingcloud.service.BcRoleOpService;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BcRoleOpServiceImpl implements BcRoleOpService {

    @Autowired
    private BcRoleOpDao bcRoleOpDao;

    @Autowired
    private SysRoleJdbcDao sysRoleJdbcDao;

    @Override
    public void setRoleOp(Map params) {
        String codeStr=(String)params.get("data");
        String roleStr=(String)params.get("role");
        JSONObject obj0=JSON.parseObject(roleStr);
        net.sf.json.JSONObject obj1=  net.sf.json.JSONObject.fromObject(roleStr);
        BcRole bcRole= (BcRole)net.sf.json.JSONObject.toBean(obj1, BcRole.class);



        String roleId=(String)obj0.get("roleId");
//        String [] list=codeStr.split(",");


        //清空roleOp表里对应角色的按钮权限
        bcRoleOpDao.cleanRoleOpByRoleId(roleId);

        JSONArray arr=JSONArray.parseArray(codeStr);

        Set<String> tmpPermSet=new HashSet();
        //设置roleOp表新的按钮权限
        for(int i=0;i<arr.size();i++){

            BcRoleOp bcRoleOp=new BcRoleOp();
            bcRoleOp.setId(UUIDUtils.generateUUID());
            bcRoleOp.setRoleId(roleId);
            JSONObject obj=(JSONObject) arr.get(i);
            bcRoleOp.setPerm((String)obj.get("pid"));
            bcRoleOp.setOpCode((String)obj.get("code"));
            bcRoleOpDao.setRoleOp(bcRoleOp);


            tmpPermSet.add((String)obj.get("pid"));
        }

        StringBuffer tmpStr=new StringBuffer();
        for(String item : tmpPermSet){
            tmpStr.append(item+",");
        }
        tmpStr.deleteCharAt(tmpStr.length()-1);

        bcRole.setPerm(tmpStr.toString());
        //更新role表里菜单权限
        sysRoleJdbcDao.update(bcRole);



    }

    @Override
    public List<BcRoleOp> getRoleOpList(String roleId) {
        Map params=new HashMap();
        params.put("roleId",roleId);
        return bcRoleOpDao.getRoleOp(params);
    }
}
