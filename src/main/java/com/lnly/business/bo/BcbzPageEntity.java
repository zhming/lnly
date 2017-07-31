package com.lnly.business.bo;

import com.lnly.core.mybatis.page.PageEntity;

/**
 * Description
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　钱志明　2017-07-31 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author qianzhiming
 * @version 1.0, 2017年7月1日 <br/>
 * @email 35691226@qq.com
 */
public class BcbzPageEntity extends PageEntity {
    private String year;
    private String dictCode;
    private String type;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
