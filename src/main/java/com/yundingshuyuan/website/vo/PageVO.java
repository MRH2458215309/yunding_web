package com.yundingshuyuan.website.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {

    private Integer page;

    private Integer size;

    private Integer totalPages;

    private List<T> dataList;

}
