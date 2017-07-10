package com.lnly.common.dao;

import com.lnly.common.model.AdminDict;
import com.lnly.common.model.SmallClass;

import java.util.List;

public interface SmallClassMapper {
    List<SmallClass> findAll();

    SmallClass findById(Long id);

    List<SmallClass> findListByYear(Long year);
}