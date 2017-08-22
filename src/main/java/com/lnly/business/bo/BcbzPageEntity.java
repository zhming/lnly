package com.lnly.business.bo;

import com.lnly.core.mybatis.page.PageEntity;

/**
 * 补偿标准查询参数
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
    private Integer searchYear;
    private String searchContent;
    private String searchContentFromSelect;
    private String searchEmail;


    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }


    public String getSearchContentFromSelect() {
        return searchContentFromSelect;
    }

    public void setSearchContentFromSelect(String searchContentFromSelect) {
        this.searchContentFromSelect = searchContentFromSelect;
    }

    public Integer getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(Integer searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }
}
