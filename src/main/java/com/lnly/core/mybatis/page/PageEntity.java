package com.lnly.core.mybatis.page;

public class PageEntity {
    private Integer iDisplayStart;
    private Integer iDisplayLength;
    private String sSearch;

    public Integer getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(Integer iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public Integer getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(Integer iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getsSearch() {
        return sSearch;
    }

    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }
}
