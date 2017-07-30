package com.lnly.common.dao;

import com.lnly.common.model.UUser;
import com.lnly.common.model.YearBcbz;
import com.lnly.permission.bo.URoleBo;

import java.util.List;
import java.util.Map;

public interface YearBcbzMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YearBcbz record);


    YearBcbz selectByPrimaryKey(Long id);

    int updateByPrimaryKey(YearBcbz record);

    YearBcbz findBcbz(Map<String, Object> map);

}