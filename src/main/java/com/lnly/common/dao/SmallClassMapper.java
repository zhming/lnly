package com.lnly.common.dao;

import com.lnly.common.model.AdminDict;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.common.model.SmallClass;

import java.util.List;
import java.util.Map;

public interface SmallClassMapper {
    SmallClass findById(Long id);
    int deleteByPrimaryKey(Long id);

    int insert(SmallClass record);

    SmallClass selectByPrimaryKey(Long id);

    int update(SmallClass record);

}