package com.lnly.business.service;

import com.lnly.common.model.UUser;
import com.lnly.common.model.YearBcbz;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.permission.bo.URoleBo;
import com.lnly.permission.bo.UserRoleAllocationBo;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface YearBcbzService {

	int deleteByPrimaryKey(Long id);

	YearBcbz insert(YearBcbz record);


	YearBcbz selectByPrimaryKey(Long id);

    int updateByPrimaryKey(YearBcbz record);
    
    YearBcbz findBcbz(String year, Integer type);


}
