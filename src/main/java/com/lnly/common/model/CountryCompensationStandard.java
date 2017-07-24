package com.lnly.common.model;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by monsoon on 2017-07-02.
 */
public class CountryCompensationStandard implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer year;
    private String city;
    private String county;
    private Double area;
    private Double countryZbje;
    private Double otherZbje;
    private String comment;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;
    private String deleteFlag;
    private Integer status;

    public CountryCompensationStandard() {
    }

    public CountryCompensationStandard(CountryCompensationStandard countryCompensationStandard) {
        this.id = countryCompensationStandard.getId();
        this.year = countryCompensationStandard.getYear();
        this.city = countryCompensationStandard.getCity();
        this.county = countryCompensationStandard.getCounty();
        this.area = countryCompensationStandard.getArea();
        this.countryZbje = countryCompensationStandard.getCountryZbje();
        this.otherZbje = countryCompensationStandard.getOtherZbje();
        this.comment = countryCompensationStandard.getComment();
        this.createUser = countryCompensationStandard.getCreateUser();
        this.updateUser = countryCompensationStandard.getUpdateUser();
        this.createTime = countryCompensationStandard.getCreateTime();
        this.updateTime = countryCompensationStandard.getUpdateTime();
        this.deleteFlag = countryCompensationStandard.getDeleteFlag();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getCountryZbje() {
        return countryZbje;
    }

    public void setCountryZbje(Double countryZbje) {
        this.countryZbje = countryZbje;
    }

    public Double getOtherZbje() {
        return otherZbje;
    }

    public void setOtherZbje(Double otherZbje) {
        this.otherZbje = otherZbje;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String toString(){
        return JSONObject.fromObject(this).toString();
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
