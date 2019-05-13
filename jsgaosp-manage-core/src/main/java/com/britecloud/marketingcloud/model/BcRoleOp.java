package com.britecloud.marketingcloud.model;


import lombok.Data;

@Data
public class BcRoleOp {
    private  String id;
    private String roleId;
    private  String perm;
    private String opCode;
    private  String opName;
}
