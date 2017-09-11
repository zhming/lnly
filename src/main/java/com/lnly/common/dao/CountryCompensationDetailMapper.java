package com.lnly.common.dao;

import com.lnly.business.bo.GrantAreaSumBo1;
import com.lnly.common.model.CountryCompensationDetail;

import java.sql.ResultSet;
import java.util.List;

/**
 * Description
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　钱志明　2017-08-04 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author qianzhiming
 * @version 1.0, 2017年7月1日 <br/>
 * @email 35691226@qq.com
 */
public interface CountryCompensationDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CountryCompensationDetail record);

    CountryCompensationDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CountryCompensationDetail record);

    List<CountryCompensationDetail> findAll();
    List<CountryCompensationDetail> findSmallClassData(CountryCompensationDetail entity);

    List<GrantAreaSumBo1> countrySendReport();

}
