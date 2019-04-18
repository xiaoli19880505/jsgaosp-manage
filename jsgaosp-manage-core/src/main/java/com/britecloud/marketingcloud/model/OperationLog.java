package com.britecloud.marketingcloud.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperationLog implements Serializable {

    private String id;
    private String userLoginName;
    private String operation;
    private String method;
    private String params;
    private String ip;


}
