package com.lnly.business.service.impl;

import com.lnly.business.service.AdminDictService;
import com.lnly.common.dao.AdminDictMapper;
import com.lnly.common.dao.CountryCompensationStandardMapper;
import com.lnly.common.model.AdminDict;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDictServiceImpl extends BaseMybatisDao<AdminDictMapper> implements AdminDictService {
   @Autowired
   CustomSessionManager customSessionManager;
	@Autowired
	AdminDictMapper adminDictMapper;

	@Override
	public List<AdminDict> findAll() throws Exception {
		return adminDictMapper.findAll();
	}

	@Override
	public List<AdminDict> findByHighDict(String highDict) throws Exception {
		return adminDictMapper.findByHighDict(highDict);
	}
}
