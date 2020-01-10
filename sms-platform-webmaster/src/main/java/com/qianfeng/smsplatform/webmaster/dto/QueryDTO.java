package com.qianfeng.smsplatform.webmaster.dto;

public class QueryDTO {
    //http://localhost:8080/sys/menu/list?order=asc&limit=10&offset=0
    private int offset;
    private int limit;
    private String order;
    private String sort;//排序的字段
    private String search;//搜索

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
