package com.lnly.common.dao;

import com.lnly.business.bo.CountryCompensationStandardBo;
import com.lnly.common.model.CountryCompensationStandard;

import java.util.List;
import java.util.Map;

/**
 * Created by monsoon on 2017-07-04.
 */
public interface CountryCompensationStandardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CountryCompensationStandard record);

    int insertSelective(CountryCompensationStandard record);

    CountryCompensationStandard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CountryCompensationStandard record);

    int updateByPrimaryKey(CountryCompensationStandard record);

}
