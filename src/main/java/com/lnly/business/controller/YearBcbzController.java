package com.lnly.business.controller;

import com.lnly.business.bo.AdminDictTreeBo;
import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.YearBcbzService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.YearBcbz;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.SerializeUtil;
import com.lnly.core.shiro.cache.JedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;


@Controller
@Scope(value = "prototype")
@RequestMapping("yearBcbz")
public class YearBcbzController extends BaseController {

    @Resource
    YearBcbzService yearBcbzService;

    @Autowired
    public JedisManager jedisManager;

    private static final String ADMIN_DICT_TREE_PRE = "year_bcbz_pre_";

    private static final int DB_INDEX = 1;


    static final Class<YearBcbzController> SELF = YearBcbzController.class;

    @RequestMapping(value = "findBcbz", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findBcbz(String year, String type) {
        Map<String, Object> result = new HashMap<>();

        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(year, type));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
            result = (   Map<String, Object>) SerializeUtil.deserialize(byteValue);
            LoggerUtils.debug(SELF, "This value from cache!" + result.size());
        } catch (Exception e) {
            try {
                YearBcbz entity = yearBcbzService.findBcbz(year, type);
                result.put("data", entity);
                result.put("message", "ok");
                jedisManager.saveValueByKey(DB_INDEX, byteKey,SerializeUtil.serialize(result), 500000 );
            } catch (Exception e1) {
                e1.printStackTrace();
                result.put("message", "error");

            }
        }



        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(YearBcbz bcbz) {
        Map<String, Object> result = new HashMap<>();
        try {
            YearBcbz entity = yearBcbzService.insert(bcbz);
            result.put("data", entity);
            result.put("message", "ok");
            byte[] byteKey = SerializeUtil.serialize(buildCacheKey(bcbz.getYear(), bcbz.getType()));
            jedisManager.deleteByKey(DB_INDEX, byteKey);
            jedisManager.saveValueByKey(DB_INDEX, byteKey,SerializeUtil.serialize(result), 500000 );
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");

        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(YearBcbz bcbz) {
        Map<String, Object> result = new HashMap<>();
        try {
            int ret = yearBcbzService.updateByPrimaryKey(bcbz);
            if( 1 != ret){
                result.put("data", ret);
                result.put("message", "fail");
            }else{
                result.put("data", ret);
                result.put("message", "ok");
                byte[] byteKey = SerializeUtil.serialize(buildCacheKey(bcbz.getYear(), bcbz.getType()));
                jedisManager.deleteByKey(DB_INDEX, byteKey);
                jedisManager.saveValueByKey(DB_INDEX, byteKey,SerializeUtil.serialize(result), 500000 );
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");

        }
        return result;
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delte(Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            YearBcbz bcbz = yearBcbzService.selectByPrimaryKey(id);

            if(null != bcbz){
                int ret = yearBcbzService.deleteByPrimaryKey(id);
                if( 1 != ret){
                    result.put("data", ret);
                    result.put("message", "fail");
                }else{
                    result.put("data", ret);
                    result.put("message", "ok");
                    byte[] byteKey = SerializeUtil.serialize(buildCacheKey(bcbz.getYear(), bcbz.getType()));
                    jedisManager.deleteByKey(DB_INDEX, byteKey);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");

        }
        return result;
    }

    private String buildCacheKey(String year, String type) {
        return ADMIN_DICT_TREE_PRE + year + "_" + type;
    }

}
