package com.lnly.business.service;

import com.lnly.common.model.CountryCompensationStandard;
import com.lnly.common.model.LocalCompensationStandard;
import com.lnly.core.mybatis.page.Pagination;

import java.util.Map;

public interface LocalCompensationStandardService {

	int deleteByPrimaryKey(Long id);

	LocalCompensationStandard insert(LocalCompensationStandard record);

	LocalCompensationStandard insertSelective(LocalCompensationStandard record);

	LocalCompensationStandard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LocalCompensationStandard record);

    int updateByPrimaryKey(LocalCompensationStandard record);


	Pagination<LocalCompensationStandard> findByPage(Map<String, Object> resultMap, Integer pageNo,
                                                       Integer pageSize);

	Map<String, Object> deleteUserById(String ids);

	Map<String, Object> updateForbidUserById(Long id, Long status);

	public Map<String, Object> deleteRoleByUserIds(String userIds);


}
