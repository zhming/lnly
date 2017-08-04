package com.lnly.business.service.impl;

import com.lnly.business.service.CountryCompensationDetailService;
import com.lnly.common.dao.CountryCompensationDetailMapper;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryCompensationDetailServiceImpl extends BaseMybatisDao<CountryCompensationDetailMapper> implements CountryCompensationDetailService {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	CountryCompensationDetailMapper countryCompensationStandardMapper;
	

	@Override
	public int deleteByPrimaryKey(Long id) {
		return countryCompensationStandardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CountryCompensationDetail insert(CountryCompensationDetail entity) {
		countryCompensationStandardMapper.insert(entity);    
		return entity;
	}



	@Override
	public CountryCompensationDetail selectByPrimaryKey(Long id) {
		return countryCompensationStandardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(CountryCompensationDetail entity) {
		return countryCompensationStandardMapper.updateByPrimaryKey(entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Pagination<CountryCompensationDetail> findByPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage("findAll","findCount",resultMap, pageNo, pageSize);
	}

	




}
