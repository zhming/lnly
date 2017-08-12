package com.lnly.business.service.impl;

import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.SmallClassService;
import com.lnly.common.dao.AdminDictMapper;
import com.lnly.common.dao.SmallClassMapper;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.SmallClass;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmallClassServiceImpl extends BaseMybatisDao<SmallClassMapper> implements SmallClassService {
   @Autowired
   CustomSessionManager customSessionManager;
	@Autowired
	SmallClassMapper smallClassMapper;

	@Override
	public List<SmallClass> findAll() throws Exception {
		return smallClassMapper.findAll();
	}

	@Override
	public SmallClass findById(Long id) throws Exception {
		return smallClassMapper.findById(id);
	}

	@Override
	public List<SmallClass> findListByYear(Long year) throws Exception {
		return smallClassMapper.findListByYear(year);
	}

	@Override
	public AdminDict findByDictCode(String dictCode) throws Exception {
		return null;
	}

	@Override
	public List<AdminDict> findByHighDict(String highDict) throws Exception {
		return null;
	}

}
