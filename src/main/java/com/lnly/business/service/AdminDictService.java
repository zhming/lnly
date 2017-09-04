package com.lnly.business.service;

import com.lnly.common.model.AdminDict;
import com.lnly.common.model.CountryCompensationStandard;
import com.lnly.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

public interface AdminDictService {

	List<AdminDict> findAll() throws Exception;

	List<AdminDict> findByHighDict(String highDict) throws Exception;

	AdminDict findByDictCode(String dictCode) throws Exception;
	AdminDict findById(Long id) throws Exception;
    public List<Map<String, Object>> getAllDict(String dictCode) throws Exception;
}
