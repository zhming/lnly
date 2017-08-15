package com.lnly.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by monsoon on 2017-07-02.
 */
public class CountryCompensationDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String city;
    private String county;
    private String town;
    private String village;
    private String forestClass;
    private String smallClass;
    private String littleClass;
    private String landTypes;
    private String forestBelong;
    private String landBelong;
    private String source;
    private String belongProve;
    private String identityCard;
    private String username;
    private String uniteUsername;
    private String year;
    private Double area;
    private Double compensationStandard;
    private Double compensationAmount;
    private String remitNum;
    private String remitUserName;
    private char sendFlag;
    private char checkFlag;
    private String comment;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;
    private char deleteFlag;

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

    public String getLittleClass() {
        return littleClass;
    }

    public void setLittleClass(String littleClass) {
        this.littleClass = littleClass;
    }

    public String getLandTypes() {
        return landTypes;
    }

    public void setLandTypes(String landTypes) {
        this.landTypes = landTypes;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBelongProve() {
        return belongProve;
    }

    public void setBelongProve(String belongProve) {
        this.belongProve = belongProve;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUniteUsername() {
        return uniteUsername;
    }

    public void setUniteUsername(String uniteUsername) {
        this.uniteUsername = uniteUsername;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getCompensationStandard() {
        return compensationStandard;
    }

    public void setCompensationStandard(Double compensationStandard) {
        this.compensationStandard = compensationStandard;
    }

    public Double getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount(Double compensationAmount) {
        this.compensationAmount = compensationAmount;
    }

    public String getRemitNum() {
        return remitNum;
    }

    public void setRemitNum(String remitNum) {
        this.remitNum = remitNum;
    }

    public String getRemitUserName() {
        return remitUserName;
    }

    public void setRemitUserName(String remitUserName) {
        this.remitUserName = remitUserName;
    }

    public char getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(char sendFlag) {
        this.sendFlag = sendFlag;
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

    public char getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(char deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public char getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(char checkFlag) {
        this.checkFlag = checkFlag;
    }
}
