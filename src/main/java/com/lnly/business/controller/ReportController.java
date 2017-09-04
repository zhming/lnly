package com.lnly.business.controller;

import com.lnly.business.bo.GrantAreaSumBo;
import com.lnly.business.bo.LocalZbdhJdBo;
import com.lnly.business.bo.LocalZbdhJdPageEntity;
import com.lnly.business.bo.SmallClassPageEntity;
import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.SmallClassService;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.SmallClass;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.SerializeUtil;
import com.lnly.common.utils.StaticValusUtil;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.cache.JedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据统计
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　钱志明　2017-08-14 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author qianzhiming
 * @version 1.0, 2017年7月1日 <br/>
 * @email 35691226@qq.com
 */

@Controller
@Scope(value="prototype")
@RequestMapping("report")
public class ReportController {
    @Resource
    SmallClassService smallClassService;

    @Resource
    AdminDictService adminDictService;



    @Autowired
    public JedisManager jedisManager;



    private static final String KEY_PRE = "report_zbdh_jd_data_pre_";

    private static final int DB_INDEX = 1;


    static final Class<ReportController> SELF = ReportController.class;

    private static int iEcho = 0;

    @RequestMapping(value = "localZbdhJdReport", method = RequestMethod.GET)
    public ModelAndView localZbdhJdReport(){
        return new ModelAndView("report/localZbdhJdReport");
    }

    @RequestMapping(value = "contryZbdhJdReport", method = RequestMethod.GET)
    public ModelAndView contryZbdhJdReport(){
        return new ModelAndView("report/contryZbdhJdReport");
    }

    @RequestMapping(value = "countryGrantAreaSumReport", method = RequestMethod.GET)
    public ModelAndView countryGrantAreaSumReport(){
        return new ModelAndView("report/countryGrantAreaSumReport");
    }

    @RequestMapping(value = "localGrantAreaSumReport", method = RequestMethod.GET)
    public ModelAndView localGrantAreaSumReport(){
        return new ModelAndView("report/localGrantAreaSumReport");
    }

    @RequestMapping(value = "countryGrantAreaSumResearchReport", method = RequestMethod.GET)
    public ModelAndView countryGrantAreaSumResearchReport(){
        return new ModelAndView("report/countryGrantAreaSumResearchReport");
    }

    @RequestMapping(value = "localGrantAreaSumResearchReport", method = RequestMethod.GET)
    public ModelAndView localGrantAreaSumResearchReport(){
        return new ModelAndView("report/localGrantAreaSumResearchReport");
    }

    @RequestMapping(value = "localSmallClassGrantReport", method = RequestMethod.GET)
    public ModelAndView localSmallClassGrantReport(){
        return new ModelAndView("report/localSmallClassGrantReport");
    }

    @RequestMapping(value = "countrySmallClassGrantReport", method = RequestMethod.GET)
    public ModelAndView countrySmallClassGrantReport(){
        return new ModelAndView("report/countrySmallClassGrantReport");
    }

    @RequestMapping(value = "grantNoOkAreaSumDetail", method = RequestMethod.GET)
    public ModelAndView grantNoOkAreaSumDetail(){
        return new ModelAndView("report/grantNoOkAreaSumDetail");
    }

    @RequestMapping(value = "grantOkAreaSumDetail", method = RequestMethod.GET)
    public ModelAndView grantOkAreaSumDetail(){
        return new ModelAndView("report/grantOkAreaSumDetail");
    }




    @RequestMapping(value = "findLocalZbdhJd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findLocalZbdhJd(LocalZbdhJdPageEntity param) {
        iEcho++;
        Long searchId = param.getSearchId();
        String dictName = "";
        String colum = "";
        String dictCode = "";
        AdminDict adminDictH = null;

        List<LocalZbdhJdBo> result = new ArrayList<>();
        LocalZbdhJdBo bo = new LocalZbdhJdBo();
        bo.setDict("辽宁省");
        bo.setWclC("50%");
        bo.setZbdhZbJe("55660012");
        bo.setSjdhJe("45000000");
        bo.setWdhJe("10660012");
        bo.setWcl("58%");
        bo.setZbdhZb1("3500000");
        bo.setSjdhJe1("2500000");
        bo.setWcl1("78%");

        bo.setZbdhZb2("3500000");
        bo.setSjdhJe2("2500000");
        bo.setWcl2("78%");
        result.add(bo);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", result);
        resultMap.put("iDisplayLength", 10);
        resultMap.put("iDisplayStart", 0);
//
        resultMap.put("recordsTotal", 1);
        resultMap.put("recordsFiltered", 1);
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 12);
        resultMap.put("message", "ok");
//        resultMap.put("error", "no data");
         return resultMap;



    }


    @RequestMapping(value = "findContryZbdhJd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findContryZbdhJd(LocalZbdhJdPageEntity param) {
        iEcho++;
        Long searchId = param.getSearchId();
        String dictName = "";
        String colum = "";
        String dictCode = "";
        AdminDict adminDictH = null;

        List<LocalZbdhJdBo> result = new ArrayList<>();
        LocalZbdhJdBo bo = new LocalZbdhJdBo();
        bo.setDict("辽宁省");
        bo.setWclC("50%");
        bo.setZbdhZbJe("55660012");
        bo.setSjdhJe("45000000");
        bo.setWdhJe("10660012");
        bo.setWcl("58%");
        bo.setZbdhZb1("3500000");
        bo.setSjdhJe1("2500000");
        bo.setWcl1("78%");

        bo.setZbdhZb2("3500000");
        bo.setSjdhJe2("2500000");
        bo.setWcl2("78%");
        result.add(bo);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", result);
        resultMap.put("iDisplayLength", 10);
        resultMap.put("iDisplayStart", 0);
//
        resultMap.put("recordsTotal", 1);
        resultMap.put("recordsFiltered", 1);
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 12);
        resultMap.put("message", "ok");
//        resultMap.put("error", "no data");
        return resultMap;



    }




    @RequestMapping(value = "findLocalGrantAreaSumReport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findLocalGrantAreaSumReport(LocalZbdhJdPageEntity param) {
        iEcho++;
        Long searchId = param.getSearchId();
        String dictName = "";
        String colum = "";
        String dictCode = "";
        AdminDict adminDictH = null;

        List<GrantAreaSumBo> result = new ArrayList<>();
        GrantAreaSumBo bo = new GrantAreaSumBo();
        bo.setDict("辽宁省");
        bo.setGrantArea10("356270.29");
        bo.setGrantSum10("1030554.34");
        bo.setGrantArea11("4699830.7");
        bo.setGrantSum11("5319582.49");
        bo.setGrantArea12("11222734.34");
        bo.setGrantSum12("20977900.04");
        bo.setGrantArea13("12989010.74");
        bo.setGrantSum13("21077682.66");
        bo.setGrantArea14("16149811.5");
        bo.setGrantSum14("52041324.77");
        bo.setGrantArea15("14638117.82");
        bo.setGrantSum15("47448752.28");
        bo.setGrantArea16("11446398.63");
        bo.setGrantSum16("36947087.92");
        bo.setGrantArea17("432482.54");
        bo.setGrantSum17("1507355.17");

        result.add(bo);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", result);
        resultMap.put("iDisplayLength", 10);
        resultMap.put("iDisplayStart", 0);
//
        resultMap.put("recordsTotal", 1);
        resultMap.put("recordsFiltered", 1);
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 12);
        resultMap.put("message", "ok");
//        resultMap.put("error", "no data");
        return resultMap;



    }

    private String buildCacheKey(String year, String key) {
        return KEY_PRE + year+ "_" + key;
    }
}
