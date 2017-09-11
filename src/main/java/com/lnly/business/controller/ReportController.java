package com.lnly.business.controller;

import com.lnly.business.bo.*;
import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.CountryCompensationDetailService;
import com.lnly.business.service.CountryReportService;
import com.lnly.business.service.SmallClassService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.*;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.SerializeUtil;
import com.lnly.common.utils.StaticValusUtil;
import com.lnly.common.utils.StringUtils;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.cache.JedisManager;
import com.lnly.user.service.UUserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

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
public class ReportController extends BaseController {
    @Resource
    SmallClassService smallClassService;

    @Resource
    AdminDictService adminDictService;


    @Autowired
    private CountryCompensationDetailService countryCompensationDetailService;


    @Autowired
    private JedisManager jedisManager;
    
    @Autowired
    private UUserService userService;

    @Autowired
    private CountryReportService countryReportService;



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


    @RequestMapping(value = "localZbdhDataInputReport", method = RequestMethod.GET)
    public ModelAndView localZbdhDataInputReport(){
        return new ModelAndView("report/localZbdhDataInputReport");
    }

    @RequestMapping(value = "countryZbdhDataInputReport", method = RequestMethod.GET)
    public ModelAndView countryZbdhDataInputReport(){
        return new ModelAndView("report/countryZbdhDataInputReport");
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


    @RequestMapping(value = "findLocalZbdhDataInputReport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findLocalZbdhDataInputReport(LocalZbdhJdPageEntity param) {
        iEcho++;
        Long searchId = param.getSearchId();
        String dictName = "";
        String colum = "";
        String dictCode = "";
        AdminDict adminDictH = null;

        List<DataInputBo> result = new ArrayList<>();
        DataInputBo bo = new DataInputBo();
        bo.setDict("辽宁省");
        bo.setInput10("5926563.65");
        bo.setInput11("23227241.21");
        bo.setInput12("26995151.91");
        bo.setInput13("27760933.83");
        bo.setInput14("56419379.95");
        bo.setInput15("53610884.20");
        bo.setInput16("49350641.79");
        bo.setInput17("1656494.20");
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

    @RequestMapping(value = "findCountryZbdhDataInputReport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findCountryZbdhDataInputReport(LocalZbdhJdPageEntity param) {
        iEcho++;
        Long searchId = param.getSearchId();
        String dictName = "";
        String colum = "";
        String dictCode = "";
        AdminDict adminDictH = null;

        List<DataInputBo> result = new ArrayList<>();
        DataInputBo bo = new DataInputBo();
        bo.setDict("辽宁省");
        bo.setInput10("5926563.65");
        bo.setInput11("23227241.21");
        bo.setInput12("26995151.91");
        bo.setInput13("27760933.83");
        bo.setInput14("56419379.95");
        bo.setInput15("53610884.20");
        bo.setInput16("49350641.79");
        bo.setInput17("1656494.20");
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


    /**
     *   国家补偿明细列表
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findCountrySendReport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCountrySendReport(HttpServletRequest request, BcbzPageEntity param) {
        iEcho++;
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "###################### " + dictCode);
        DateTime start = new DateTime();
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(param.getSearchYear())) {
            map.put("year", param.getSearchYear());
        }else{
            DateTime dateTime = new DateTime();
            map.put("year", dateTime.toString("yyyy"));
        }

        if (StringUtils.isNotBlank(param.getSearchContentFromSelect())
                && !"210000".equals(param.getSearchContentFromSelect())
                && !"辽宁省".equals(param.getSearchContentFromSelect())) {
            map.put("searchContent", param.getSearchContentFromSelect());
        }

        if (StringUtils.isNotBlank(param.getSearchContent())) {
            map.put("searchContent", param.getSearchContent());
        }

        if(!map.containsKey("searchContent")) {
            String searchEmail = param.getSearchEmail();
            try {
                UUser user = userService.findUserByEmail(searchEmail);
                if(StringUtils.isBlank(dictCode)){
                    dictCode = user.getDictCode();
                }
                if(!"210000".equalsIgnoreCase(dictCode)){
                    AdminDict dict = adminDictService.findByDictCode(dictCode);
                    map.put("searchContent", dict.getDictName());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        DateTime end1 = new DateTime();
        LoggerUtils.debug(getClass(), "param: : "+(end1.getSecondOfDay() - start.getSecondOfDay()));
        List<GrantAreaSumBo1> reportList = null;
        int total = 0;
        try {
            start = new DateTime();
            Pagination<GrantAreaSumBo1> pagination = countryCompensationDetailService.findCountrySendReport(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryList: "+(end1.getSecondOfDay() - start.getSecondOfDay()));
            start = new DateTime();
            total = pagination.getTotalCount();
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryCount: "+(end1.getSecondOfDay() - start.getSecondOfDay()));
            if (null != pagination) {
                reportList = pagination.getList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", reportList);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", total);
        resultMap.put("recordsFiltered", total);
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 10);
        resultMap.put("message", "ok");
        return resultMap;
    }



    @RequestMapping(value = "findCityData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findCityData(HttpServletRequest request,BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();



        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);



        int searType = 0;

        try {
            if(!"210000".equals(dictCode)){

                //市级单位
                if(dictCode.length() == 6 && dictCode.endsWith("00")){
                    searType = 1;
                    map.put("city", dictCode);
                }else if(dictCode.length() == 6 && !dictCode.endsWith("00")){
                    searType = 2;
                    map.put("county", dictCode);
                }else if(dictCode.length() > 6){
                    searType = 3;
                    map.put("town", dictCode);
                }

            }else{
                searType = 1;
                map.put("city", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!StringUtils.isBlank(param.getSearchType())) {
              map.put("type", param.getSearchType());
        }

        Pagination<CountryReport> result = null;
        List<GrantAreaSumBo>  list = new ArrayList<>();

        Map<String, GrantAreaSumBo> mapBo = new TreeMap<>();

        try {
            if(1 == searType){
                result = countryReportService.findCityData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }else if(2 == searType){
                result = countryReportService.findCountyData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }else if(3 == searType){
                result = countryReportService.findTownData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }


            if(null != result && null != result.getList()){
                for(CountryReport report : result.getList()){
                    if(mapBo.containsKey(buildMapKey(report))){

                        LoggerUtils.debug(getClass(), "substr Year:   "+ report.getYear().substring(2));

                        GrantAreaSumBo bo = mapBo.get(buildMapKey(report));
                       Method m1 = bo.getClass().getMethod("setGrantArea"+ report.getYear().substring(2),String.class);
                        m1.invoke(bo, report.getSendArea());
                        Method m2 = bo.getClass().getMethod("setGrantSum"+ report.getYear().substring(2),String.class);
                        m2.invoke(bo, report.getSendAmount());
                        mapBo.put(buildMapKey(report), bo);
                    }else{
                        GrantAreaSumBo bo = new GrantAreaSumBo();
                        bo.setDict(report.getDictName());
                        Method m1 = bo.getClass().getMethod("setGrantArea"+ report.getYear().substring(2),String.class);
                        m1.invoke(bo, report.getSendArea());
                        Method m2 = bo.getClass().getMethod("setGrantSum"+ report.getYear().substring(2),String.class);
                        m2.invoke(bo, report.getSendAmount());
                        mapBo.put(buildMapKey(report), bo);
                    }
                }
            }


            for (Map.Entry<String, GrantAreaSumBo> entry : mapBo.entrySet()) {
                 list.add(entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", list);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered",  result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;



    }

    /**
     * 国家公益林调查面积统计
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findCoutryReSearchData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findCoutryReSearchData(HttpServletRequest request,BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();



        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);



        int searType = 0;

        try {
            if(!"210000".equals(dictCode)){

                //市级单位
                if(dictCode.length() == 6 && dictCode.endsWith("00")){
                    searType = 1;
                    map.put("city", dictCode);
                }else if(dictCode.length() == 6 && !dictCode.endsWith("00")){
                    searType = 2;
                    map.put("county", dictCode);
                }else if(dictCode.length() > 6){
                    searType = 3;
                    map.put("town", dictCode);
                }

            }else{
                searType = 1;
                map.put("city", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<CountryReport> result = null;
        List<GrantAreaSumBo>  list = new ArrayList<>();

        Map<String, GrantAreaSumBo> mapBo = new TreeMap<>();

        try {
            if(1 == searType){
                result = countryReportService.findCityData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }else if(2 == searType){
                result = countryReportService.findCountyData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }else if(3 == searType){
                result = countryReportService.findTownData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }


            if(null != result && null != result.getList()){
                for(CountryReport report : result.getList()){
                    if(mapBo.containsKey(buildMapKey(report))){
                        LoggerUtils.debug(getClass(), "substr Year:   "+ report.getYear().substring(2));
                        GrantAreaSumBo bo = mapBo.get(buildMapKey(report));
                        Method m1 = bo.getClass().getMethod("setGrantArea"+ report.getYear().substring(2),String.class);
                        m1.invoke(bo, (report.getSendArea() + report.getNoSendArea()));
                        Method m2 = bo.getClass().getMethod("setGrantSum"+ report.getYear().substring(2),String.class);
                        m2.invoke(bo, report.getSendAmount() + report.getNoSendAmount());
                        mapBo.put(buildMapKey(report), bo);
                    }else{
                        GrantAreaSumBo bo = new GrantAreaSumBo();
                        bo.setDict(report.getDictName());
                        Method m1 = bo.getClass().getMethod("setGrantArea"+ report.getYear().substring(2),String.class);
                        m1.invoke(bo, report.getSendArea());
                        Method m2 = bo.getClass().getMethod("setGrantSum"+ report.getYear().substring(2),String.class);
                        m2.invoke(bo, report.getSendAmount());
                        mapBo.put(buildMapKey(report), bo);
                    }
                }
            }


            for (Map.Entry<String, GrantAreaSumBo> entry : mapBo.entrySet()) {
                list.add(entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", list);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered",  result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;



    }


    /**
     * 国家公益林直补到户相关数据录入率统计表
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findCoutryInputData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findCoutryInputData(HttpServletRequest request,BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();



        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);



        int searType = 0;

        try {
            if(!"210000".equals(dictCode)){

                //市级单位
                if(dictCode.length() == 6 && dictCode.endsWith("00")){
                    searType = 1;
                    map.put("city", dictCode);
                }else if(dictCode.length() == 6 && !dictCode.endsWith("00")){
                    searType = 2;
                    map.put("county", dictCode);
                }else if(dictCode.length() > 6){
                    searType = 3;
                    map.put("town", dictCode);
                }

            }else{
                searType = 1;
                map.put("city", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<CountryReport> result = null;
        List<DataInputBo>  list = new ArrayList<>();

        Map<String, DataInputBo> mapBo = new TreeMap<>();

        try {
            if(1 == searType){
                result = countryReportService.findCityData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }else if(2 == searType){
                result = countryReportService.findCountyData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }else if(3 == searType){
                result = countryReportService.findTownData(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }

            DecimalFormat df = new DecimalFormat("#.00");
            if(null != result && null != result.getList()){
                for(CountryReport report : result.getList()){
                    if(mapBo.containsKey(buildMapKey(report))){
                        LoggerUtils.debug(getClass(), "substr Year:   "+ report.getYear().substring(2));
                        DataInputBo bo = mapBo.get(buildMapKey(report));
                        bo.setDict(report.getDictName());
                        Double amount = Double.parseDouble(proStr2D(report.getSendAmount())) + Double.parseDouble(proStr2D(report.getNoSendAmount()));
                        Double standard =   Double.parseDouble(report.getStandard());
                        Method m1 = bo.getClass().getMethod("setInput"+ report.getYear().substring(2),String.class);
                        m1.invoke(bo, df.format(amount));
                        Method m2 = bo.getClass().getMethod("setStandard"+ report.getYear().substring(2),String.class);
                        m2.invoke(bo, df.format(standard));
                        Double ret = 0.00;
                        if( 0 != amount.intValue() && 0 != standard.intValue()){
                            ret = amount / standard * 100;
                        }

                        Method m3 = bo.getClass().getMethod("setPercent"+ report.getYear().substring(2),String.class);
                        m3.invoke(bo, df.format(ret));

                        mapBo.put(buildMapKey(report), bo);
                    }else{
                        DataInputBo bo = new DataInputBo();
                        bo.setDict(report.getDictName());
                        Double amount = Double.parseDouble(proStr2D(report.getSendAmount())) + Double.parseDouble(proStr2D(report.getNoSendAmount()));
                        Double standard =   Double.parseDouble(report.getStandard());

                        if(".00".equals(df.format(standard))){
                            LoggerUtils.debug(getClass(), ".00  "+standard);
                            standard = 0.00;
                        }

                        if(".00".equals(df.format(amount))){
                            LoggerUtils.debug(getClass(), ".00  "+amount);
                            amount = 0.00;
                        }
                        Method m1 = bo.getClass().getMethod("setInput"+ report.getYear().substring(2),String.class);
                        m1.invoke(bo, df.format(amount));
                        Method m2 = bo.getClass().getMethod("setStandard"+ report.getYear().substring(2),String.class);
                        m2.invoke(bo, df.format(standard));

                        Double ret = 0.00;
                        if( 0 != amount.intValue() && 0 != standard.intValue()){
                            ret = amount / standard * 100;
                        }

                        if(".00".equals(df.format(ret))){
                            LoggerUtils.debug(getClass(), ".00  "+ret);
                            ret = 0.00;
                        }


                        
                        Method m3 = bo.getClass().getMethod("setPercent"+ report.getYear().substring(2),String.class);
                        m3.invoke(bo, df.format(ret));
                        mapBo.put(buildMapKey(report), bo);
                    }
                }
            }


            for (Map.Entry<String, DataInputBo> entry : mapBo.entrySet()) {
                list.add(entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", list);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered",  result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;



    }

    private String proStr2D(String str){
        if(StringUtils.isBlank(str)){
            return "0.00";
        }
        return str;
    }

    private String buildCacheKey(String year, String key) {
        return KEY_PRE + year+ "_" + key;
    }



    private String buildMapKey(CountryReport report)
    {
        return new StringBuilder().append(report.getDictName() + report.getCity() + report.getCounty()).toString();
    }

    public static void main(String[] args){

                                          LoggerUtils.debug(String.class, "2017".substring(2));

                                          Double d1 = 2096.00;
                                          Double d2 = 3603.00;
        DecimalFormat df = new DecimalFormat("#.00");
        String ret = df.format(d1 / d2 * 100);
        LoggerUtils.debug(String.class, ret + "%");
    }
}
