package com.lnly.business.service;

import com.lnly.common.model.LocalCompensationDetail;
import com.lnly.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

public interface LocalCompensationDetailService {

	int deleteByPrimaryKey(Long id);

	LocalCompensationDetail insert(LocalCompensationDetail record);

	LocalCompensationDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(LocalCompensationDetail record);
    

	Pagination<LocalCompensationDetail> findByPage(Map<String, Object> resultMap, Integer pageNo,
                                                     Integer pageSize);

    Double findSmallClassData(LocalCompensationDetail entity) throws Exception;
    public void insertList(List<LocalCompensationDetail> list) throws Exception;

}
