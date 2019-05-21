package com.britecloud.marketingcloud.dao.impl;

import java.net.URL;

/**
 * @author wangli
 * @class Test
 * @description TODO(用一句话描述该类做什么)
 * @date 2019/5/21 12:01
 */
public class Test {
    public static void main(String[] args){
        System.out.println("111");
       // String url = "D:\\泰州公安局系统文档\\紫金框架\\jsgaosp-manage\\jsgaosp-manage-core\\src\\main\\java\\com\\britecloud\\marketingcloud\\dao\\impl\\sql\\SysUserJdbcDaoImpl.sql";
        Test test = new Test();
        URL url1 = Test.class.getClassLoader().getResource("tes1t.properties");
        System.out.println("url:"+url1);
    }
}
