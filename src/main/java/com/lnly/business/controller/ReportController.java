package com.lnly.business.controller;

import com.lnly.business.bo.*;
import com.lnly.business.service.*;
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
@Scope(value = "prototype")
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

    @Autowired
    private LocalReportService localReportService;


    private static final String KEY_PRE = "report_zbdh_jd_data_pre_";

    private static final int DB_INDEX = 1;


    static final Class<ReportController> SELF = ReportController.class;

    private static int iEcho = 0;

    @RequestMapping(value = "localZbdhJdReport", method = RequestMethod.GET)
    public ModelAndView localZbdhJdReport() {
        return new ModelAndView("report/localZbdhJdReport");
    }

    @RequestMapping(value = "contryZbdhJdReport", method = RequestMethod.GET)
    public ModelAndView contryZbdhJdReport() {
        return new ModelAndView("report/contryZbdhJdReport");
    }

    @RequestMapping(value = "countryGrantAreaSumReport", method = RequestMethod.GET)
    public ModelAndView countryGrantAreaSumReport() {
        return new ModelAndView("report/countryGrantAreaSumReport");
    }

    @RequestMapping(value = "localGrantAreaSumReport", method = RequestMethod.GET)
    public ModelAndView localGrantAreaSumReport() {
        return new ModelAndView("report/localGrantAreaSumReport");
    }

    @RequestMapping(value = "countryGrantAreaSumResearchReport", method = RequestMethod.GET)
    public ModelAndView countryGrantAreaSumResearchReport() {
        return new ModelAndView("report/countryGrantAreaSumResearchReport");
    }

    @RequestMapping(value = "localGrantAreaSumResearchReport", method = RequestMethod.GET)
    public ModelAndView localGrantAreaSumResearchReport() {
        return new ModelAndView("report/localGrantAreaSumResearchReport");
    }

    @RequestMapping(value = "localSmallClassGrantReport", method = RequestMethod.GET)
    public ModelAndView localSmallClassGrantReport() {
        return new ModelAndView("report/localSmallClassGrantReport");
    }

    @RequestMapping(value = "countrySmallClassGrantReport", method = RequestMethod.GET)
    public ModelAndView countrySmallClassGrantReport() {
        return new ModelAndView("report/countrySmallClassGrantReport");
    }

    @RequestMapping(value = "grantNoOkAreaSumDetail", method = RequestMethod.GET)
    public ModelAndView grantNoOkAreaSumDetail() {
        return new ModelAndView("report/grantNoOkAreaSumDetail");
    }

    @RequestMapping(value = "grantOkAreaSumDetail", method = RequestMethod.GET)
    public ModelAndView grantOkAreaSumDetail() {
        return new ModelAndView("report/grantOkAreaSumDetail");
    }


    @RequestMapping(value = "localZbdhDataInputReport", method = RequestMethod.GET)
    public ModelAndView localZbdhDataInputReport() {
        return new ModelAndView("report/localZbdhDataInputReport");
    }

    @RequestMapping(value = "countryZbdhDataInputReport", method = RequestMethod.GET)
    public ModelAndView countryZbdhDataInputReport() {
        return new ModelAndView("report/countryZbdhDataInputReport");
    }
    

    /**
     * 国家补偿明细列表
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
        } else {
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

        if (!map.containsKey("searchContent")) {
            String searchEmail = param.getSearchEmail();
            try {
                UUser user = userService.findUserByEmail(searchEmail);
                if (StringUtils.isBlank(dictCode)) {
                    dictCode = user.getDictCode();
                }
                if (!"210000".equalsIgnoreCase(dictCode)) {
                    AdminDict dict = adminDictService.findByDictCode(dictCode);
                    map.put("searchContent", dict.getDictName());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        DateTime end1 = new DateTime();
        LoggerUtils.debug(getClass(), "param: : " + (end1.getSecondOfDay() - start.getSecondOfDay()));
        List<GrantAreaSumBo1> reportList = null;
        int total = 0;
        try {
            start = new DateTime();
            Pagination<GrantAreaSumBo1> pagination = countryCompensationDetailService.findCountrySendReport(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryList: " + (end1.getSecondOfDay() - start.getSecondOfDay()));
            start = new DateTime();
            total = pagination.getTotalCount();
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryCount: " + (end1.getSecondOfDay() - start.getSecondOfDay()));
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

    /**
     * 国家已发放统计
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findCityDataSend", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCityDataSend(HttpServletRequest request, BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();


        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);


        int searType = 0;

        try {
            if (!"210000".equals(dictCode)) {

                //市级单位
                if (dictCode.length() == 6 && dictCode.endsWith("00")) {
                    searType = 1;
                    map.put("city", dictCode);
                } else if (dictCode.length() == 6 && !dictCode.endsWith("00")) {
                    searType = 2;
                    map.put("county", dictCode);
                } else if (dictCode.length() > 6) {
                    searType = 3;
                    map.put("town", dictCode);
                }

            } else {
                searType = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<CountryReport> result = null;

        try {
            if (1 == searType) {
                result = countryReportService.findCityDataSend(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (2 == searType) {
                result = countryReportService.findCityDataSend(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (3 == searType) {
                result = countryReportService.findCityDataSend(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", result.getList());
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered", result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;


    }

    /**
     * 地方已发放数据统计
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findLocalCityDataSend", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findLocalCityDataSend(HttpServletRequest request, BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();


        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);


        int searType = 0;

        try {
            if (!"210000".equals(dictCode)) {

                //市级单位
                if (dictCode.length() == 6 && dictCode.endsWith("00")) {
                    searType = 1;
                    map.put("city", dictCode);
                } else if (dictCode.length() == 6 && !dictCode.endsWith("00")) {
                    searType = 2;
                    map.put("county", dictCode);
                } else if (dictCode.length() > 6) {
                    searType = 3;
                    map.put("town", dictCode);
                }

            } else {
                searType = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<LocalReport> result = null;

        try {
            if (1 == searType) {
                result = localReportService.findCityDataSend(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (2 == searType) {
                result = localReportService.findCityDataSend(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (3 == searType) {
                result = localReportService.findCityDataSend(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", result.getList());
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered", result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;


    }

    /**
     * 国家公益林调查面积统计
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findCityDataSendAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCityDataSendAll(HttpServletRequest request, BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();


        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);


        int searType = 0;

        try {
            if (!"210000".equals(dictCode)) {

                //市级单位
                if (dictCode.length() == 6 && dictCode.endsWith("00")) {
                    searType = 1;
                    map.put("city", dictCode);
                } else if (dictCode.length() == 6 && !dictCode.endsWith("00")) {
                    searType = 2;
                    map.put("county", dictCode);
                } else if (dictCode.length() > 6) {
                    searType = 3;
                    map.put("town", dictCode);
                }

            } else {
                searType = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<CountryReport> result = null;

        try {
            if (1 == searType) {
                result = countryReportService.findCityDataSendAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (2 == searType) {
                result = countryReportService.findCityDataSendAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (3 == searType) {
                result = countryReportService.findCityDataSendAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", result.getList());
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered", result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;


    }


    /**
     * 地方公益林调查面积统计
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findLocalCityDataSendAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findLocalCityDataSendAll(HttpServletRequest request, BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();


        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);


        int searType = 0;

        try {
            if (!"210000".equals(dictCode)) {

                //市级单位
                if (dictCode.length() == 6 && dictCode.endsWith("00")) {
                    searType = 1;
                    map.put("city", dictCode);
                } else if (dictCode.length() == 6 && !dictCode.endsWith("00")) {
                    searType = 2;
                    map.put("county", dictCode);
                } else if (dictCode.length() > 6) {
                    searType = 3;
                    map.put("town", dictCode);
                }

            } else {
                searType = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<LocalReport> result = null;

        try {
            if (1 == searType) {
                result = localReportService.findCityDataSendAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (2 == searType) {
                result = localReportService.findCityDataSendAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (3 == searType) {
                result = localReportService.findCityDataSendAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", result.getList());
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered", result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;


    }



    /**
     * 国家公益林直补到户相关数据录入率统计表
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findCityDataAmountAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCityDataAmountAll(HttpServletRequest request, BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();


        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);


        int searType = 0;

        try {
            if (!"210000".equals(dictCode)) {

                //市级单位
                if (dictCode.length() == 6 && dictCode.endsWith("00")) {
                    searType = 1;
                    map.put("city", dictCode);
                } else if (dictCode.length() == 6 && !dictCode.endsWith("00")) {
                    searType = 2;
                    map.put("county", dictCode);
                } else if (dictCode.length() > 6) {
                    searType = 3;
                    map.put("town", dictCode);
                }

            } else {
                searType = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<DataInputBo> result = null;
        List<DataInputBo> list = new ArrayList<>();


        try {
            if (1 == searType) {
                result = countryReportService.findCityDataAmountAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (2 == searType) {
                result = countryReportService.findCityDataAmountAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (3 == searType) {
                result = countryReportService.findCityDataAmountAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }

            DecimalFormat df = new DecimalFormat("#.00");
            if (null != result && null != result.getList()) {
                for (DataInputBo report : result.getList()) {
                    double input10 = Double.parseDouble(report.getInput10());
                    double standard10 = Double.parseDouble(report.getStandard10());
                    if (0.00 != standard10) {
                        String percent10 = df.format(input10 / standard10 * 100);
                        report.setPercent10(percent10);
                    }


                    double input11 = Double.parseDouble(report.getInput11());
                    double standard11 = Double.parseDouble(report.getStandard11());
                    if (0.00 != standard11) {
                        String percent11 = df.format(input11 / standard11 * 100);
                        report.setPercent11(percent11);
                    }

                    double input12 = Double.parseDouble(report.getInput12());
                    double standard12 = Double.parseDouble(report.getStandard12());
                    if (0.00 != standard12) {
                        String percent12 = df.format(input12 / standard12 * 100);
                        report.setPercent12(percent12);
                    }

                    double input13 = Double.parseDouble(report.getInput13());
                    double standard13 = Double.parseDouble(report.getStandard13());
                    if (0.00 != standard12) {
                        String percent13 = df.format(input13 / standard13 * 100);
                        report.setPercent13(percent13);
                    }

                    double input14 = Double.parseDouble(report.getInput14());
                    double standard14 = Double.parseDouble(report.getStandard14());
                    if (0.00 != standard14) {
                        String percent14 = df.format(input14 / standard14 * 100);
                        report.setPercent14(percent14);
                    }
                    double input15 = Double.parseDouble(report.getInput15());
                    double standard15 = Double.parseDouble(report.getStandard15());
                    if (0.00 != standard15) {
                        String percent15 = df.format(input15 / standard15 * 100);
                        report.setPercent15(percent15);
                    }
                    double input16 = Double.parseDouble(report.getInput16());
                    double standard16 = Double.parseDouble(report.getStandard16());
                    if (0.00 != standard16) {
                        String percent16 = df.format(input16 / standard16 * 100);
                        report.setPercent16(percent16);
                    }
                    double input17 = Double.parseDouble(report.getInput17());
                    double standard17 = Double.parseDouble(report.getStandard17());
                    if (0.00 != standard17) {
                        String percent17 = df.format(input17 / standard17 * 100);
                        report.setPercent17(percent17);
                    }
                    double input18 = Double.parseDouble(report.getInput18());
                    double standard18 = Double.parseDouble(report.getStandard18());
                    if (0.00 != standard18) {
                        String percent18 = df.format(input18 / standard18 * 100);
                        report.setPercent18(percent18);
                    }
                    double input19 = Double.parseDouble(report.getInput19());
                    double standard19 = Double.parseDouble(report.getStandard19());
                    if (0.00 != standard19) {
                        String percent19 = df.format(input19 / standard19 * 100);
                        report.setPercent19(percent19);
                    }
                    double input20 = Double.parseDouble(report.getInput20());
                    double standard20 = Double.parseDouble(report.getStandard20());
                    if (0.00 != standard20) {
                        String percent20 = df.format(input20 / standard20 * 100);

                        report.setPercent20(percent20);
                    }

                    double input21 = Double.parseDouble(report.getInput21());
                    double standard21 = Double.parseDouble(report.getStandard21());
                    if (0.00 != standard21) {
                        String percent21 = df.format(input21 / standard21 * 100);
                        report.setPercent21(percent21);
                    }
                    list.add(report);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", list);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered", result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;


    }


    /**
     * 地方公益林直补到户相关数据录入率统计表
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findLocalCityDataAmountAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findLocalCityDataAmountAll(HttpServletRequest request, BcbzPageEntity param) {
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "######################DictCode: " + dictCode);

        iEcho++;
        Map<String, Object> map = new HashMap<>();


        String searchEmail = param.getSearchEmail();


        LoggerUtils.debug(getClass(), "user_dict_code: " + searchEmail);


        int searType = 0;

        try {
            if (!"210000".equals(dictCode)) {

                //市级单位
                if (dictCode.length() == 6 && dictCode.endsWith("00")) {
                    searType = 1;
                    map.put("city", dictCode);
                } else if (dictCode.length() == 6 && !dictCode.endsWith("00")) {
                    searType = 2;
                    map.put("county", dictCode);
                } else if (dictCode.length() > 6) {
                    searType = 3;
                    map.put("town", dictCode);
                }

            } else {
                searType = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!StringUtils.isBlank(param.getSearchType())) {
            map.put("type", param.getSearchType());
        }

        Pagination<DataInputBo> result = null;
        List<DataInputBo> list = new ArrayList<>();


        try {
            if (1 == searType) {
                result = localReportService.findCityDataAmountAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (2 == searType) {
                result = localReportService.findCityDataAmountAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            } else if (3 == searType) {
                result = localReportService.findCityDataAmountAll(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            }

            DecimalFormat df = new DecimalFormat("#.00");
            if (null != result && null != result.getList()) {
                for (DataInputBo report : result.getList()) {
                    double input10 = Double.parseDouble(report.getInput10());
                    double standard10 = Double.parseDouble(report.getStandard10());
                    if (0.00 != standard10) {
                        String percent10 = df.format(input10 / standard10 * 100);
                        report.setPercent10(percent10);
                    }


                    double input11 = Double.parseDouble(report.getInput11());
                    double standard11 = Double.parseDouble(report.getStandard11());
                    if (0.00 != standard11) {
                        String percent11 = df.format(input11 / standard11 * 100);
                        report.setPercent11(percent11);
                    }

                    double input12 = Double.parseDouble(report.getInput12());
                    double standard12 = Double.parseDouble(report.getStandard12());
                    if (0.00 != standard12) {
                        String percent12 = df.format(input12 / standard12 * 100);
                        report.setPercent12(percent12);
                    }

                    double input13 = Double.parseDouble(report.getInput13());
                    double standard13 = Double.parseDouble(report.getStandard13());
                    if (0.00 != standard12) {
                        String percent13 = df.format(input13 / standard13 * 100);
                        report.setPercent13(percent13);
                    }

                    double input14 = Double.parseDouble(report.getInput14());
                    double standard14 = Double.parseDouble(report.getStandard14());
                    if (0.00 != standard14) {
                        String percent14 = df.format(input14 / standard14 * 100);
                        report.setPercent14(percent14);
                    }
                    double input15 = Double.parseDouble(report.getInput15());
                    double standard15 = Double.parseDouble(report.getStandard15());
                    if (0.00 != standard15) {
                        String percent15 = df.format(input15 / standard15 * 100);
                        report.setPercent15(percent15);
                    }
                    double input16 = Double.parseDouble(report.getInput16());
                    double standard16 = Double.parseDouble(report.getStandard16());
                    if (0.00 != standard16) {
                        String percent16 = df.format(input16 / standard16 * 100);
                        report.setPercent16(percent16);
                    }
                    double input17 = Double.parseDouble(report.getInput17());
                    double standard17 = Double.parseDouble(report.getStandard17());
                    if (0.00 != standard17) {
                        String percent17 = df.format(input17 / standard17 * 100);
                        report.setPercent17(percent17);
                    }
                    double input18 = Double.parseDouble(report.getInput18());
                    double standard18 = Double.parseDouble(report.getStandard18());
                    if (0.00 != standard18) {
                        String percent18 = df.format(input18 / standard18 * 100);
                        report.setPercent18(percent18);
                    }
                    double input19 = Double.parseDouble(report.getInput19());
                    double standard19 = Double.parseDouble(report.getStandard19());
                    if (0.00 != standard19) {
                        String percent19 = df.format(input19 / standard19 * 100);
                        report.setPercent19(percent19);
                    }
                    double input20 = Double.parseDouble(report.getInput20());
                    double standard20 = Double.parseDouble(report.getStandard20());
                    if (0.00 != standard20) {
                        String percent20 = df.format(input20 / standard20 * 100);

                        report.setPercent20(percent20);
                    }

                    double input21 = Double.parseDouble(report.getInput21());
                    double standard21 = Double.parseDouble(report.getStandard21());
                    if (0.00 != standard21) {
                        String percent21 = df.format(input21 / standard21 * 100);
                        report.setPercent21(percent21);
                    }
                    list.add(report);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        resultMap.put("iEcho", iEcho);
        resultMap.put("data", list);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", result.getTotalCount());
        resultMap.put("recordsFiltered", result.getTotalCount());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;


    }



    /**
     *   国家公益林直补到户小班发放汇总面积小于小班面积统计
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findSmallClassDiff", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findSmallClassDiff(HttpServletRequest request,BcbzPageEntity param) {
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

        try {
            if (!"210000".equals(dictCode)) {

                //市级单位
                if (dictCode.length() == 6 && dictCode.endsWith("00")) {
                    map.put("city", dictCode);
                } else if (dictCode.length() == 6 && !dictCode.endsWith("00")) {
                    map.put("county", dictCode);
                } else if (dictCode.length() > 6) {
                    map.put("town", dictCode);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


       

        map.put("localDiff", 10);

        DateTime end1 = new DateTime();
        LoggerUtils.debug(getClass(), "param: : "+(end1.getSecondOfDay() - start.getSecondOfDay()));
        List<SmallClass> smallClassList = null;
        int total = 0;
        try {
            start = new DateTime();
            Pagination<SmallClass> pagination = smallClassService.findListDiff(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryList: "+(end1.getSecondOfDay() - start.getSecondOfDay()));
            start = new DateTime();
            total = pagination.getTotalCount();
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryCount: "+(end1.getSecondOfDay() - start.getSecondOfDay()));
            if (null != pagination) {
                smallClassList = pagination.getList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", smallClassList);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", total);
        resultMap.put("recordsFiltered", total);
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;
    }


    /**
     *   地方公益林直补到户小班发放汇总面积小于小班面积统计
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findLocalSmallClassDiff", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findLocalSmallClassDiff(HttpServletRequest request,BcbzPageEntity param) {
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


        if (StringUtils.isNotBlank(param.getSearchContentFromSelect())) {
            map.put("searchContent", param.getSearchContentFromSelect());
        }else{
            if(!"210000".equalsIgnoreCase(dictCode)){
                AdminDict dict = null;
                try {
                    dict = adminDictService.findByDictCode(dictCode);
                    map.put("searchContent", dict.getDictName());
                } catch (Exception e) {
                }

            }else{
                map.put("searchContent", null);
            }
        }

        map.put("localDiff", 10);

        DateTime end1 = new DateTime();
        LoggerUtils.debug(getClass(), "param: : "+(end1.getSecondOfDay() - start.getSecondOfDay()));
        List<SmallClass> smallClassList = null;
        int total = 0;
        try {
            start = new DateTime();
            Pagination<SmallClass> pagination = smallClassService.findListDiff(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryList: "+(end1.getSecondOfDay() - start.getSecondOfDay()));
            start = new DateTime();
            total = pagination.getTotalCount();
            end1 = new DateTime();
            LoggerUtils.debug(getClass(), "queryCount: "+(end1.getSecondOfDay() - start.getSecondOfDay()));
            if (null != pagination) {
                smallClassList = pagination.getList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", smallClassList);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());

        resultMap.put("recordsTotal", total);
        resultMap.put("recordsFiltered", total);
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;
    }

    private String proStr2D(String str) {
        if (StringUtils.isBlank(str)) {
            return "0.00";
        }
        return str;
    }

    private String buildCacheKey(String year, String key) {
        return KEY_PRE + year + "_" + key;
    }


    private String buildMapKey(CountryReport report) {
        return new StringBuilder().append(report.getDictName() + report.getCity() + report.getCounty()).toString();
    }

    public static void main(String[] args) {

        LoggerUtils.debug(String.class, "2017".substring(2));

        Double d1 = 2096.00;
        Double d2 = 3603.00;
        DecimalFormat df = new DecimalFormat("#.00");
        String ret = df.format(d1 / d2 * 100);
        LoggerUtils.debug(String.class, ret + "%");
    }
}
