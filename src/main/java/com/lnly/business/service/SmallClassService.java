package com.lnly.business.service;

import com.lnly.common.model.AdminDict;
import com.lnly.common.model.SmallClass;
import com.lnly.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

public interface SmallClassService {


	SmallClass findById(Long id) throws Exception;

    void deleteByPrimaryKey(Long id)throws Exception;

    SmallClass insert(SmallClass record)throws Exception;

    SmallClass update(SmallClass record)throws Exception;

	/**
	 * 获取子列表
	 * @param entity
	 * @return
	 * @throws Exception
	 */
    public Pagination<SmallClass> findAll(SmallClass entity, Integer pageNo, Integer pageSize) throws Exception;

    List<SmallClass> findSmallList(SmallClass entity,  Integer pageNo, Integer pageSize) throws Exception;

    public Pagination<SmallClass> findListDiff(Map<String, Object> map, Integer pageNo, Integer pageSize) throws Exception ;

}


