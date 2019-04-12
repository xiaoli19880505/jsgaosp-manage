package com.britecloud.marketingcloud.model;

import lombok.Data;

/**
 * 数据字典详情
 */
@Data
public class BcCode {

    private String codeId;
    private String codeSortId;
    private String codeKey;
    private String codeText;
    private String status;
}
