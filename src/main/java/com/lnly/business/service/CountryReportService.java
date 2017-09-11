package com.lnly.business.service;

import com.lnly.common.model.CountryReport;
import com.lnly.core.mybatis.page.PageEntity;
import com.lnly.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Description
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　钱志明　2017-09-09 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author qianzhiming
 * @version 1.0, 2017年7月1日 <br/>
 * @email 35691226@qq.com
 */
public interface CountryReportService {
    Pagination<CountryReport> findCityData(Map<String, Object> params, Integer pageNo,
                                           Integer pageSize) throws Exception;

    Pagination<CountryReport> findCountyData(Map<String, Object> params, Integer pageNo,
                                           Integer pageSize) throws Exception;


    Pagination<CountryReport> findTownData(Map<String, Object> params, Integer pageNo,
                                           Integer pageSize) throws Exception;


    Pagination<CountryReport> findVillageData(Map<String, Object> params, Integer pageNo,
                                           Integer pageSize) throws Exception;
}
