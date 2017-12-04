package com.spring.boot.bean;

/**
 * Created by yuderen on 2017-9-2.
 */

public class Page {

    private int currentPage = 1;
    private int numPerPage = 10;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }
}
