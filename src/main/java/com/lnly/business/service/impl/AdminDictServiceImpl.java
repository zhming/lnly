package com.lnly.business.service.impl;

import com.lnly.business.service.AdminDictService;
import com.lnly.common.dao.AdminDictMapper;
import com.lnly.common.dao.CountryCompensationStandardMapper;
import com.lnly.common.model.AdminDict;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.SerializeUtil;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.shiro.cache.JedisManager;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class AdminDictServiceImpl extends BaseMybatisDao<AdminDictMapper> implements AdminDictService {
    @Autowired
    CustomSessionManager customSessionManager;
    @Autowired
    AdminDictMapper adminDictMapper;

    @Autowired
    public JedisManager jedisManager;

    private static final String ADMIN_DICT_SERVICE_PRE = "ADMIN_DICT_SERVICE_PRE_";

    private static final int DB_INDEX = 1;


    static final Class<AdminDictServiceImpl> SELF = AdminDictServiceImpl.class;

    @Override
    public List<AdminDict> findAll() throws Exception {
        return adminDictMapper.findAll();
    }

    @Override
    public List<AdminDict> findByHighDict(String highDict) throws Exception {
        return adminDictMapper.findByHighDict(highDict);
    }

    @Override
    public AdminDict findByDictCode(String dictCode) throws Exception {
        AdminDict result = null;
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(dictCode));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
            result = (AdminDict) SerializeUtil.deserialize(byteValue);
            LoggerUtils.debug(SELF, "This value from cache!" + result.getDictCode());
        } catch (Exception e) {
            LoggerUtils.error(SELF, "AdminDict get value by cache throw exception", e);
            result = adminDictMapper.findByDictCode(dictCode);
            if(null != result){
                jedisManager.saveValueByKey(DB_INDEX, byteKey,SerializeUtil.serialize(result), 50000 );
                LoggerUtils.debug(SELF, "This value from DB!" + result.getDictCode());
            }
        }


        return result;
    }

    @Override
    public AdminDict findById(Long id) throws Exception {


        return adminDictMapper.findById(id);
    }

    private String buildCacheKey(String key) {
        return ADMIN_DICT_SERVICE_PRE + key;
    }


}
