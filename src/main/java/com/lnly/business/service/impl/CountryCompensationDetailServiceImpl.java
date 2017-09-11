package com.lnly.business.service.impl;

import com.lnly.business.bo.GrantAreaSumBo1;
import com.lnly.business.service.CountryCompensationDetailService;
import com.lnly.common.dao.CountryCompensationDetailMapper;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryCompensationDetailServiceImpl extends BaseMybatisDao<CountryCompensationDetailMapper> implements CountryCompensationDetailService {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	CountryCompensationDetailMapper countryCompensationStandardMapper;

	@Override
    @Transactional(rollbackFor = Exception.class)
	public int deleteByPrimaryKey(Long id) {
		return countryCompensationStandardMapper.deleteByPrimaryKey(id);
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public CountryCompensationDetail insert(CountryCompensationDetail entity) {
		countryCompensationStandardMapper.insert(entity);    
		return entity;
	}



	@Override
	public CountryCompensationDetail selectByPrimaryKey(Long id) {
		return countryCompensationStandardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(CountryCompensationDetail entity) {
		return countryCompensationStandardMapper.updateByPrimaryKey(entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Pagination<CountryCompensationDetail> findByPage(Map<String, Object> params,
			Integer pageNo, Integer pageSize) {
		return super.findPage("findAll","findCount", params, pageNo, pageSize);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<CountryCompensationDetail> list) throws Exception {
           for(CountryCompensationDetail entity : list){
               countryCompensationStandardMapper.insert(entity);
           }
    }

    @Override
    public Double findSmallClassData(CountryCompensationDetail entity) throws Exception {

        double result = 0.00;

        List<CountryCompensationDetail> list = countryCompensationStandardMapper.findSmallClassData(entity);

        if(null != list){
            for(CountryCompensationDetail detail : list){
                result += Double.parseDouble(detail.getArea());
            }

        }




        return  result;
    }

    @Override
    public Pagination<GrantAreaSumBo1> findCountrySendReport(Map<String, Object> params, Integer pageNo,
                                                       Integer pageSize) throws Exception {
        return super.findPage("countrySendReport","countrySendReportCount", params, pageNo, pageSize);
    }


}
