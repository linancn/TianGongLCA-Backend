package org.crystalca.backend.model;

import java.util.List;

public class GridData<T> {
    private List<T> data;
    private long total;
    private long current = 1;
    private long pageSize = 10;
    private boolean success = true;

    public GridData() {

    }

    public GridData(List<T> data, long total) {
        this.data = data;
        this.total = total;
    }

    public GridData(List<T> data, long total, long current, long pageSize, boolean success) {
        this.data = data;
        this.total = total;
        this.current = current;
        this.pageSize = pageSize;
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}