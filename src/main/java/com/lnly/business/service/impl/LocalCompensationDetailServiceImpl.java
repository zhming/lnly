package com.lnly.business.service.impl;

import com.lnly.business.service.LocalCompensationDetailService;
import com.lnly.common.dao.LocalCompensationDetailMapper;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.common.model.LocalCompensationDetail;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class LocalCompensationDetailServiceImpl extends BaseMybatisDao<LocalCompensationDetailMapper> implements LocalCompensationDetailService {

	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	LocalCompensationDetailMapper localCompensationDetailMapper;
	

	@Override
	public int deleteByPrimaryKey(Long id) {
		return localCompensationDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public LocalCompensationDetail insert(LocalCompensationDetail entity) {
		localCompensationDetailMapper.insert(entity);    
		return entity;
	}



	@Override
	public LocalCompensationDetail selectByPrimaryKey(Long id) {
		return localCompensationDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(LocalCompensationDetail entity) {
		return localCompensationDetailMapper.updateByPrimaryKey(entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Pagination<LocalCompensationDetail> findByPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage("findAll","findCount",resultMap, pageNo, pageSize);
	}

    @Override
    public Double findSmallClassData(LocalCompensationDetail entity) throws Exception {
            double result = 0.00;

            List<LocalCompensationDetail> list = localCompensationDetailMapper.findSmallClassData(entity);

            if(null != list){
                for(LocalCompensationDetail detail : list){
                    result += Double.parseDouble(detail.getArea());
                }

            }




            return  result;
        }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<LocalCompensationDetail> list) throws Exception {
        for(LocalCompensationDetail entity : list){
            localCompensationDetailMapper.insert(entity);
        }
    }

}
