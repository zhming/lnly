package com.lnly.business.service.impl;

import com.lnly.business.service.CountryCompensationStandardService;
import com.lnly.common.dao.CountryCompensationStandardMapper;
import com.lnly.common.dao.UUserMapper;
import com.lnly.common.dao.UUserRoleMapper;
import com.lnly.common.model.CountryCompensationStandard;
import com.lnly.common.model.UUser;
import com.lnly.common.model.UUserRole;
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
public class CountryCompensationStandardServiceImpl extends BaseMybatisDao<CountryCompensationStandardMapper> implements CountryCompensationStandardService {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	CountryCompensationStandardMapper countryCompensationStandardMapper;
	

	@Override
	public int deleteByPrimaryKey(Long id) {
		return countryCompensationStandardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CountryCompensationStandard insert(CountryCompensationStandard entity) {
		countryCompensationStandardMapper.insert(entity);    
		return entity;
	}

	@Override
	public CountryCompensationStandard insertSelective(CountryCompensationStandard entity) {
		countryCompensationStandardMapper.insertSelective(entity);
		return entity;
	}

	@Override
	public CountryCompensationStandard selectByPrimaryKey(Long id) {
		return countryCompensationStandardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(CountryCompensationStandard entity) {
		return countryCompensationStandardMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateByPrimaryKeySelective(CountryCompensationStandard entity) {
		return countryCompensationStandardMapper.updateByPrimaryKeySelective(entity);
	}




	@SuppressWarnings("unchecked")
	@Override
	public Pagination<CountryCompensationStandard> findByPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Override
	public Map<String, Object> deleteUserById(String ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			int count=0;
			String[] idArray = new String[]{};
			if(StringUtils.contains(ids, ",")){
				idArray = ids.split(",");
			}else{
				idArray = new String[]{ids};
			}
			
			for (String id : idArray) {
				count+=this.deleteByPrimaryKey(new Long(id));
			}
			resultMap.put("status", 200);
			resultMap.put("count", count);
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
			resultMap.put("status", 500);
			resultMap.put("message", "删除出现错误，请刷新后再试！");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> updateForbidUserById(Long id, Long status) {
		return null;
	}


	@Override
	public Map<String, Object> deleteRoleByUserIds(String userIds) {

		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("userIds", userIds);
			resultMap.put("status", 200);
			resultMap.put("message", "操作成功");
		} catch (Exception e) {
			resultMap.put("status", 200);
			resultMap.put("message", "操作失败，请重试！");
		}
		return resultMap;

	}

	@Override
	public List<CountryCompensationStandard> findAll() throws Exception {
		return countryCompensationStandardMapper.findAll();
	}

	@Override
	public Pagination<CountryCompensationStandard> findAllPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) throws Exception {
		return null;
	}

	@Override
	public List<CountryCompensationStandard> findList(String dictCodes, String year) throws Exception {
		return countryCompensationStandardMapper.findList(dictCodes, year);
	}


}
