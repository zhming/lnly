package com.lnly.business.service;

import com.lnly.common.model.CountryCompensationStandard;
import com.lnly.common.model.UUser;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.permission.bo.URoleBo;
import com.lnly.permission.bo.UserRoleAllocationBo;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface CountryCompensationStandardService {

	int deleteByPrimaryKey(Long id);

	CountryCompensationStandard insert(CountryCompensationStandard record);

	CountryCompensationStandard insertSelective(CountryCompensationStandard record);

	CountryCompensationStandard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CountryCompensationStandard record);

    int updateByPrimaryKey(CountryCompensationStandard record);
    

	Pagination<CountryCompensationStandard> findByPage(Map<String, Object> resultMap, Integer pageNo,
													   Integer pageSize);

	Map<String, Object> deleteUserById(String ids);

	Map<String, Object> updateForbidUserById(Long id, Long status);

	public Map<String, Object> deleteRoleByUserIds(String userIds);


}
