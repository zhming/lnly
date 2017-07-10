package com.lnly.business.service;

import com.lnly.common.model.AdminDict;
import com.lnly.common.model.SmallClass;

import java.util.List;

public interface SmallClassService {

	List<SmallClass> findAll() throws Exception;

	SmallClass findById(Long id) throws Exception;
	
	List<SmallClass> findListByYear(Long year) throws Exception;

}
