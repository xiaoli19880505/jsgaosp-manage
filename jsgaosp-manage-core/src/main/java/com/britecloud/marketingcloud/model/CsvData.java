package com.britecloud.marketingcloud.model;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class CsvData {
    private Map<String, String> header;
    private List<JSONObject> data;


    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }

}
