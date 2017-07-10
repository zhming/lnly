package com.lnly.common.dao;

import com.lnly.common.model.CountryCompensationStandard;
import com.lnly.common.model.LocalCompensationStandard;

/**
 * Created by monsoon on 2017-07-04.
 */
public interface LocalCompensationStandardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LocalCompensationStandard record);

    int insertSelective(LocalCompensationStandard record);

    LocalCompensationStandard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LocalCompensationStandard record);

    int updateByPrimaryKey(LocalCompensationStandard record);

}
