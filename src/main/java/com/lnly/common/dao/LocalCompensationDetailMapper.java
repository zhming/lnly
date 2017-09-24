package com.lnly.common.dao;

import com.lnly.common.model.LocalCompensationDetail;

import java.util.List;

/**
 * Description
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　钱志明　2017-08-04 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author qianzhiming
 * @version 1.0, 2017年7月1日 <br/>
 * @email 35691226@qq.com
 */
public interface LocalCompensationDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LocalCompensationDetail record);

    LocalCompensationDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(LocalCompensationDetail record);

    List<LocalCompensationDetail> findAll();
    List<LocalCompensationDetail> findSmallClassData(LocalCompensationDetail entity);

}
