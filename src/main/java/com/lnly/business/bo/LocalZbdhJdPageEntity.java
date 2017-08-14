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
public class LocalZbdhJdPageEntity extends PageEntity {
    private Long searchId;
    private String searchYear;
    private String searchContent;
    private String searchContentFromSelect;


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

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }
}
