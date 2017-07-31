package com.lnly.business.service.impl;

import com.lnly.business.service.YearBcbzService;
import com.lnly.common.dao.YearBcbzMapper;
import com.lnly.common.model.UUser;
import com.lnly.common.model.UUserRole;
import com.lnly.common.model.YearBcbz;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.session.CustomSessionManager;
import com.lnly.core.shiro.token.manager.TokenManager;
import com.lnly.permission.bo.URoleBo;
import com.lnly.permission.bo.UserRoleAllocationBo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YearBcbzServiceImpl extends BaseMybatisDao<YearBcbzMapper> implements YearBcbzService {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;              
	@Autowired
	YearBcbzMapper yearBcbzMapper;


	@Override
	public int deleteByPrimaryKey(Long id) {
		return yearBcbzMapper.deleteByPrimaryKey(id);
	}

	@Override
	public YearBcbz insert(YearBcbz entity) {
		yearBcbzMapper.insert(entity);
		return entity;
	}

	

	@Override
	public YearBcbz selectByPrimaryKey(Long id) {
		return yearBcbzMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(YearBcbz entity) {
		return yearBcbzMapper.updateByPrimaryKey(entity);
	}


	@Override
	public YearBcbz findBcbz(String year ,String type) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("type", type);
		YearBcbz yearBcbz = yearBcbzMapper.findBcbz(map);
		return yearBcbz;
	}

	


}
