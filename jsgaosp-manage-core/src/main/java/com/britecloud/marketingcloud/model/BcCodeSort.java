package com.britecloud.marketingcloud.model;

import lombok.Data;

/**
 * 数据字典表,包含字典和对应的若干值
 */
@Data
public class BcCodeSort {

    private String codeSortId;
    private String codeSortKey;
    private String codeSortText;
    private String status;
    private String orgId;
    private String isPublic;
    private String pCodeSortId;
    private String hasChild;


}
