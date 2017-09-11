package com.lnly.business.service;

import com.lnly.business.bo.GrantAreaSumBo1;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

public interface CountryCompensationDetailService {

	int deleteByPrimaryKey(Long id);

	CountryCompensationDetail insert(CountryCompensationDetail record);

	CountryCompensationDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CountryCompensationDetail record);
    

	Pagination<CountryCompensationDetail> findByPage(Map<String, Object> params, Integer pageNo,
                                                       Integer pageSize);

	void insertList(List<CountryCompensationDetail> list)throws Exception;

    Double findSmallClassData(CountryCompensationDetail entity) throws Exception;

    Pagination<GrantAreaSumBo1>   findCountrySendReport(Map<String, Object> params, Integer pageNo,
                                                  Integer pageSize) throws Exception;
}
