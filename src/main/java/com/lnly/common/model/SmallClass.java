package com.lnly.common.model;

import java.io.Serializable;

/**
 * Created by monsoon on 2017-07-10.
 */
public class SmallClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String year;
    private String city;
    private String county;
    private String town;
    private String village;
    private String forestClass;
    private String smallClass;
    private Double area;
    private String sqdj;
    private String forestBelong;
    private String landBelong;
    private String xdm;
    private String landZl;
    private String source;
    private String forestZl;
    private String comment;


    public SmallClass() {
    }

    public SmallClass(Long id, String city, String county, String town, String village, String forestClass, String smallClass, Double area, String sqdj, String forestBelong, String lanBelong, String xdm, String landZl, String source, String forestZl, String comment) {
        this.id = id;
        this.city = city;
        this.county = county;
        this.town = town;
        this.village = village;
        this.forestClass = forestClass;
        this.smallClass = smallClass;
        this.area = area;
        this.sqdj = sqdj;
        this.forestBelong = forestBelong;
        this.landBelong = lanBelong;
        this.xdm = xdm;
        this.landZl = landZl;
        this.source = source;
        this.forestZl = forestZl;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getForestClass() {
        return forestClass;
    }

    public void setForestClass(String forestClass) {
        this.forestClass = forestClass;
    }

    public String getSmallClass() {
        return smallClass;
    }

    public void setSmallClass(String smallClass) {
        this.smallClass = smallClass;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getSqdj() {
        return sqdj;
    }

    public void setSqdj(String sqdj) {
        this.sqdj = sqdj;
    }

    public String getForestBelong() {
        return forestBelong;
    }

    public void setForestBelong(String forestBelong) {
        this.forestBelong = forestBelong;
    }

    public String getLandBelong() {
        return landBelong;
    }

    public void setLandBelong(String landBelong) {
        this.landBelong = landBelong;
    }

    public String getXdm() {
        return xdm;
    }

    public void setXdm(String xdm) {
        this.xdm = xdm;
    }

    public String getLandZl() {
        return landZl;
    }

    public void setLandZl(String landZl) {
        this.landZl = landZl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getForestZl() {
        return forestZl;
    }

    public void setForestZl(String forestZl) {
        this.forestZl = forestZl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
