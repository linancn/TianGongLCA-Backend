package org.tiangonglca.backend.model;

public class BaseGridFilter {
    private int current = 1;
    private int pageSize = 10;
    private String sortBy;
    private String orderBy;

    public BaseGridFilter() { }

    public BaseGridFilter(int current, int pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }

    public BaseGridFilter(int current, int pageSize, String sortBy, String orderBy) {
        this.current = current;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.orderBy = orderBy;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}

