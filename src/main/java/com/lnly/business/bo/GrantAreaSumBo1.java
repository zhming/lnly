package com.lnly.business.bo;

import java.io.Serializable;

/**
 * Description
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　钱志明　2017-08-16 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author qianzhiming
 * @version 1.0, 2017年7月1日 <br/>
 * @email 35691226@qq.com
 */
public class GrantAreaSumBo1 implements Serializable {
    private String year;
    private String type;
    private String dict;      
    private Double grantArea;
    private Double grantSum;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public Double getGrantArea() {
        return grantArea;
    }

    public void setGrantArea(Double grantArea) {
        this.grantArea = grantArea;
    }

    public Double getGrantSum() {
        return grantSum;
    }

    public void setGrantSum(Double grantSum) {
        this.grantSum = grantSum;
    }
}
