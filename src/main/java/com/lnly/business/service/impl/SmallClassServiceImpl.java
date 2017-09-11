package com.lnly.business.service.impl;

import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.SmallClassService;
import com.lnly.common.dao.AdminDictMapper;
import com.lnly.common.dao.SmallClassMapper;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.SmallClass;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.SerializeUtil;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.cache.JedisManager;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmallClassServiceImpl extends BaseMybatisDao<SmallClassMapper> implements SmallClassService {
   @Autowired
   CustomSessionManager customSessionManager;
	@Autowired
	SmallClassMapper smallClassMapper;
    @Autowired
    public JedisManager jedisManager;

    @Autowired
    private static final String SMALL_CLASS_PRE = "small_class_pre_";

    private static final int DB_INDEX = 1;

    static final Class<SmallClassServiceImpl> SELF = SmallClassServiceImpl.class;

	public SmallClassServiceImpl() {
	}

	@Override
	public SmallClass findById(Long id) throws Exception {
		return smallClassMapper.findById(id);
	}

    @Override
    public void deleteByPrimaryKey(Long id) throws Exception {
        smallClassMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmallClass insert(SmallClass record) throws Exception {
        int ret =  smallClassMapper.insert(record);
        if (ret != 1){
            throw new Exception("insert tb_small_class record error");
        }
        return record;
    }

    @Override
    public SmallClass update(SmallClass record) throws Exception {
        int ret =  smallClassMapper.update(record);
        if (ret != 1){
            throw new Exception("update tb_small_class record error");
        }
        return record;
    }

    @Override
	public Pagination<SmallClass> findAll(SmallClass entity, Integer pageNo, Integer pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", entity.getYear());
		params.put("city", entity.getCity());
		params.put("county", entity.getCounty());
		params.put("village", entity.getVillage());
		params.put("town", entity.getTown());
        return super.findPage("findAll", "findCount", params, pageNo, pageSize);
	}

    @Override
    public List<SmallClass> findSmallList(SmallClass entity) throws Exception {
        List<SmallClass> result = null;
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(entity.getTown() + entity.getVillage() + entity.getForestClass() + entity.getSmallClass()));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
            result = (List<SmallClass>) SerializeUtil.deserialize(byteValue);
            LoggerUtils.debug(SELF, "This value from cache!" + result.size());
        } catch (Exception e) {
             result = smallClassMapper.findSmallList(entity);
            jedisManager.saveValueByKey(DB_INDEX, byteKey, SerializeUtil.serialize(result), -1);
            LoggerUtils.debug(SELF, "This value from DB!" + result.size());
        }
        return result;
    }


    private String buildCacheKey(String key) {
        return SMALL_CLASS_PRE + key;
    }

}
