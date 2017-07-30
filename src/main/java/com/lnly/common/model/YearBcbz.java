package com.lnly.common.model;

import java.io.Serializable;

/**
 * Created by monsoon on 2017-07-07.
 */
public class YearBcbz implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String year;
    private Double bz1;
    private Double bz2;
    private Integer type;

    public YearBcbz() {
    }

    public YearBcbz(Long id,String year, Double bz1, Double bz2, Integer type) {
        this.id = id;
        this.year = year;
        this.bz1 = bz1;
        this.bz2 = bz2;
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getBz1() {
        return bz1;
    }

    public void setBz1(Double bz1) {
        this.bz1 = bz1;
    }

    public Double getBz2() {
        return bz2;
    }

    public void setBz2(Double bz2) {
        this.bz2 = bz2;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
