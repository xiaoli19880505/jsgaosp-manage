package com.britecloud.marketingcloud.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageDataResult<T> {

    private int totalCount;
    private int totalPage;
    private int page;
    private List<T> list;

}
