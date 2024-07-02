package com.pushpm.model;

public class PageInfo {
    private int currentPages;

    private int pagesSize;

    public PageInfo(String currentPages, String pagesSize) {
        this.currentPages = Integer.valueOf(currentPages);
        this.pagesSize = Integer.valueOf(pagesSize);
    }

    public int getCurrentPages() {
        return currentPages;
    }

    public void setCurrentPages(int currentPages) {
        this.currentPages = currentPages;
    }

    public int getPagesSize() {
        return pagesSize;
    }

    public void setPagesSize(int pagesSize) {
        this.pagesSize = pagesSize;
    }
}
