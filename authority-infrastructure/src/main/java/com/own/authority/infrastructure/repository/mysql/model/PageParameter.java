package com.own.authority.infrastructure.repository.mysql.model;

/**
 * Created by shizhan on 2015/9/24.
 */

public class PageParameter {

    private int offset;

    private int size = 20;

    private int page = 1;

    public int getOffset() {
        return (this.page-1)*this.size;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
