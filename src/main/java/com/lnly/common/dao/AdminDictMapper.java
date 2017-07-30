package com.lnly.common.dao;

import com.lnly.common.model.AdminDict;
import com.lnly.common.model.UUser;
import com.lnly.permission.bo.URoleBo;

import java.util.List;
import java.util.Map;

public interface AdminDictMapper {
    List<AdminDict> findAll();

    List<AdminDict> findByHighDict(String highDict);

    AdminDict findByDictCode(String dictCode) ;
    AdminDict findById(Long id)  ;
}