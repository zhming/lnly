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
    public Map<String, Object> findBcbz(String year, Integer type) {
        Map<String, Object> result = new HashMap<>();
        try {
            YearBcbz entity = yearBcbzService.findBcbz(year, type);
            result.put("data", entity);
            result.put("message", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");

        }
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> add(YearBcbz bcbz) {
        Map<String, Object> result = new HashMap<>();
        try {
            YearBcbz entity = yearBcbzService.insert(bcbz);
            result.put("data", entity);
            result.put("message", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");

        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
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
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");

        }
        return result;
    }

    private String buildCacheKey(String key) {
        return ADMIN_DICT_TREE_PRE + key;
    }

}
