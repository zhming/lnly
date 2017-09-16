package com.lnly.business.service.impl;

import com.lnly.business.bo.DataInputBo;
import com.lnly.business.service.CountryReportService;
import com.lnly.business.service.LocalReportService;
import com.lnly.common.dao.CountryReportMapper;
import com.lnly.common.dao.LocalReportMapper;
import com.lnly.common.model.CountryReport;
import com.lnly.common.model.LocalReport;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.mybatis.page.Pagination;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Description
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　钱志明　2017-09-09 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author qianzhiming
 * @version 1.0, 2017年7月1日 <br/>
 * @email 35691226@qq.com
 */
@Service
public class LocalReportServiceImpl extends BaseMybatisDao<LocalReportMapper>  implements LocalReportService {
    @Override
    public Pagination<LocalReport> findCityData(Map<String, Object> params, Integer pageNo, Integer pageSize) throws Exception {
        return super.findPage("findCityData","findCityDataCount", params, pageNo, pageSize);
    }

    @Override
    public Pagination<LocalReport> findCountyData(Map<String, Object> params, Integer pageNo, Integer pageSize) throws Exception {
        return super.findPage("findCountyData","findCountyDataCount", params, pageNo, pageSize);
    }

    @Override
    public Pagination<LocalReport> findTownData(Map<String, Object> params, Integer pageNo, Integer pageSize) throws Exception {
        return super.findPage("findTownData","findTownDataCount", params, pageNo, pageSize);
    }

    @Override
    public Pagination<LocalReport> findVillageData(Map<String, Object> params, Integer pageNo, Integer pageSize) throws Exception {
        return super.findPage("findVillageData","findVillageDataCount", params, pageNo, pageSize);
    }

    @Override
    public Pagination<LocalReport> findCityDataSend(Map<String, Object> params, Integer pageNo, Integer pageSize) throws Exception {
        return super.findPage("findCityDataSend","findCityDataSendCount", params, pageNo, pageSize);
    }

    @Override
    public Pagination<LocalReport> findCityDataSendAll(Map<String, Object> params, Integer pageNo, Integer pageSize) throws Exception {
        return super.findPage("findCityDataSendAll","findCityDataSendAllCount", params, pageNo, pageSize);
    }

    @Override
    public Pagination<DataInputBo> findCityDataAmountAll(Map<String, Object> params, Integer pageNo, Integer pageSize) throws Exception {
        return super.findPage("findCityDataAmountAll","findCityDataAmountAllCount", params, pageNo, pageSize);
    }
}
