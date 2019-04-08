package com.britecloud.marketingcloud.model;

public class CsvDescription {
    private String dataFieldName;//csv文件的字段名
    private String dataFieldCode;//csv文件的字段代码
    private String dataFieldType;//csv文件的字段预测类型
    public String getDataFieldName() {
        return dataFieldName;
    }
    public void setDataFieldName(String dataFieldName) {
        this.dataFieldName = dataFieldName;
    }
    public String getDataFieldCode() {
        return dataFieldCode;
    }
    public void setDataFieldCode(String dataFieldCode) {
        this.dataFieldCode = dataFieldCode;
    }
    public String getDataFieldType() {
        return dataFieldType;
    }
    public void setDataFieldType(String dataFieldType) {
        this.dataFieldType = dataFieldType;
    }

}
