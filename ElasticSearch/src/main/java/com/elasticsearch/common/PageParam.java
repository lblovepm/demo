package com.elasticsearch.common;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/7/2 9:18
 */
public class PageParam {

    private Integer pageNumber;

    private Integer pageSize;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
