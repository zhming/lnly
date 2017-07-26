package com.lnly.business.service.impl;

import com.lnly.business.service.LocalCompensationStandardService;
import com.lnly.common.dao.LocalCompensationStandardMapper;
import com.lnly.common.model.LocalCompensationStandard;
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
public class LocalCompensationStandardServiceImpl extends BaseMybatisDao<LocalCompensationStandardMapper> implements LocalCompensationStandardService {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	LocalCompensationStandardMapper localCompensationStandardMapper;
	

	@Override
	public int deleteByPrimaryKey(Long id) {
		return localCompensationStandardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public LocalCompensationStandard insert(LocalCompensationStandard entity) {
		localCompensationStandardMapper.insert(entity);
		return entity;
	}

	@Override
	public LocalCompensationStandard insertSelective(LocalCompensationStandard entity) {
		localCompensationStandardMapper.insertSelective(entity);
		return entity;
	}

	@Override
	public LocalCompensationStandard selectByPrimaryKey(Long id) {
		return localCompensationStandardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(LocalCompensationStandard entity) {
		return localCompensationStandardMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateByPrimaryKeySelective(LocalCompensationStandard entity) {
		return localCompensationStandardMapper.updateByPrimaryKeySelective(entity);
	}




	@SuppressWarnings("unchecked")
	@Override
	public Pagination<LocalCompensationStandard> findByPage(Map<String, Object> resultMap,
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
	public List<LocalCompensationStandard> findAll(String city, String county, Integer year) throws Exception {
		Map<String, Object> map = new HashMap<>();

		map.put("city", city);
		map.put("county", county);
		map.put("year", year);

		return localCompensationStandardMapper.findAll(map);
	}


}
