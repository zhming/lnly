package com.lnly.business.controller;

import com.lnly.business.bo.BcbzPageEntity;
import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.CountryCompensationDetailService;
import com.lnly.business.service.SmallClassService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.common.model.SmallClass;
import com.lnly.common.model.UUser;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.OSInfo;
import com.lnly.common.utils.StringUtils;
import com.lnly.common.utils.excel.ExcelUtil;
import com.lnly.common.utils.poi.ExcelReader;
import com.lnly.common.utils.poi.ExcelSheetCallback;
import com.lnly.common.utils.poi.ExcelWorkSheetHandler;
import com.lnly.core.mybatis.page.PageEntity;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.user.service.UUserService;
import com.mchange.v2.log.LogUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * <p>
 * 用户管理
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月3日 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author zhou-baicheng
 * @version 1.0, 2016年5月3日 <br/>
 * @email i@itboy.net
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("countryDetail")
public class CountryCompensationDetailController extends BaseController {

    @Resource
    CountryCompensationDetailService countryCompensationDetailService;
    @Autowired
    UUserService userService;
    @Autowired
    AdminDictService adminDictService;

    @Autowired
    SmallClassService smallClassService;

    private static int iEcho = 0;


    @RequestMapping(value = "countryCompensationDetail", method = RequestMethod.GET)
    public ModelAndView compensationStandard() {
        return new ModelAndView("business/countryCompensationDetail");
    }

    /**
     * 偷懒一下，通用页面跳转
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "{page}", method = RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("page") String page) {
        return new ModelAndView(String.format("country/%s", page));
    }

    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findOne(Long id) {

        CountryCompensationDetail countryCompensationStandard = countryCompensationDetailService.selectByPrimaryKey(id);
        resultMap.put("object", countryCompensationStandard);
        resultMap.put("message", "ok");
        return resultMap;
    }

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAllPost(HttpServletRequest request,BcbzPageEntity param) {
        iEcho++;
        String dictCode = request.getParameter("dictCode");
        LoggerUtils.debug(getClass(), "###################### " + dictCode);
        
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

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$: " + map.get("searchContent"));

        List<CountryCompensationDetail> countryCompensationStandards = null;
        int total = 0;
        try {
            Pagination<CountryCompensationDetail> pagination = countryCompensationDetailService.findByPage(map, param.getiDisplayStart() / param.getiDisplayLength(), param.getiDisplayLength());
            total = pagination.getTotalCount();
            if (null != pagination) {
                countryCompensationStandards = pagination.getList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", countryCompensationStandards);
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
     * 新增国家补偿标准
     *
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(CountryCompensationDetail entity) {
        try {
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            countryCompensationDetailService.insert(entity);
            resultMap.put("status", 200);
            resultMap.put("message", "保存成功!");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "保存失败!");
            LoggerUtils.fmtError(getClass(), e, "保存失败。[%s]", JSONObject.fromObject(entity).toString());
        }
        return resultMap;
    }


    /**
     * 修改
     *
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(CountryCompensationDetail entity) {
        try {
            countryCompensationDetailService.updateByPrimaryKey(entity);
            resultMap.put("status", 200);
            resultMap.put("message", "修改成功!");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "修改失败!");
            LoggerUtils.fmtError(getClass(), e, "修改失败。[%s]", JSONObject.fromObject(entity).toString());
        }
        return resultMap;
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> check(String ids, String checkStatus) {
        LoggerUtils.debug(getClass(), "checkStatus: " + checkStatus);
        String[] idss = ids.split(",");
        try {
            for(String id : idss){
                if(StringUtils.isNotBlank(id)){
                    CountryCompensationDetail entity  = countryCompensationDetailService.selectByPrimaryKey(Long.parseLong(id));
                    if(entity != null ){
                        if(Integer.parseInt(checkStatus) - Integer.parseInt(entity.getCheckFlag())  == 1){
                            entity.setCheckFlag(checkStatus);
                            countryCompensationDetailService.updateByPrimaryKey(entity);
                        }

                    }
                }


            }
            resultMap.put("status", 200);
            resultMap.put("message", "审批成功!");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "审批失败!");
            LoggerUtils.fmtError(getClass(), e, "审批失败。[%s]",ids );
        }
        return resultMap;
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        try {
            countryCompensationDetailService.deleteByPrimaryKey(id);
            resultMap.put("status", 200);
            resultMap.put("message", "删除成功!");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "删除失败!");
            LoggerUtils.fmtError(getClass(), e, "删除失败。[%s]", id);
        }
        return resultMap;
    }

    /**
     * 在线用户管理
     *
     * @return
     */
    @RequestMapping(value = "findAllPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findAll(ModelMap modelMap, PageEntity pageEntity) {

        Map<String, Object> result = new HashMap<>();

        Pagination<CountryCompensationDetail> pagination = countryCompensationDetailService.findByPage(modelMap, pageEntity.getiDisplayStart(), pageEntity.getiDisplayLength());
        result.put("data", pagination.getList());
        result.put("length", pagination.getList().size());
        result.put("sEcho", 1);
        result.put("iColumns", 5);
        result.put("sColumns", ",,,,");
        result.put("iDisplayStart", 0);
        result.put("iDisplayLength", 10);

        return result;
    }


    /**
     * 使用SpringMVC封装好的方法进行文件上传
     *
     * @param request
     * @param response
     */
    @RequestMapping(value ="upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload2(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "";
        String email = request.getParameter("email");
        String year = request.getParameter("yearUpload");
        LoggerUtils.debug(getClass(),"############# "+ email);
        Map<Integer, String> errorMap = new HashMap<>();

        Map<String, Double> areaMap = new HashMap<>();
        try {
            //获取解析器
            CommonsMultipartResolver resolver = new CommonsMultipartResolver();
            //判断是否是文件
            if (resolver.isMultipart(request)) {
                //进行转换
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
                //获取所有文件名称
                Iterator<String> it = multiRequest.getFileNames();
                while (it.hasNext()) {
                    //根据文件名称取文件
                    MultipartFile file = multiRequest.getFile(it.next());
                    fileName = file.getOriginalFilename();
                    String localPath = null;
                    if (OSInfo.isWindows()) {
                        localPath = "D:/temp/";
                    } else {
                        localPath = "/opt/Tomcat/temp/";
                    }


                    File fileDir = new File(localPath);

                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }

                    String localFile = localPath + fileName;

                    File newFile = new File(localFile);

                    //删除已存在的文件
                    if(newFile.exists() && newFile.isFile()){
                        newFile.delete();
                    }
                    //上传的文件写入到指定的文件中
                    file.transferTo(newFile);
                    UUser user = userService.findUserByEmail(email);
                   



                    //解析数据
                    List<CountryCompensationDetail> list =   processExcelAll(localFile);

                    List<CountryCompensationDetail> insertList =   new ArrayList<>();

                    int index = 0;

                    if(null != list){
                        for  (int i = 0; i < list.size(); i++){
                            CountryCompensationDetail entity = list.get(i);
                            index = i + 1;

                            //获取小班面积
                            SmallClass reqSmall =  new SmallClass();
                            reqSmall.setVillage(entity.getVillage());
                            reqSmall.setTown(entity.getTown());
                            reqSmall.setForestClass(entity.getForestClass());
                            reqSmall.setSmallClass(entity.getSmallClass());
                            SmallClass smallClass = smallClassService.findSmallList(reqSmall).get(0);

                            if(null == smallClass){
                                errorMap.put(index, entity.getTown());
                                continue;
                            }
                            LoggerUtils.debug(getClass(), entity.toString());
                            entity.setYear(year);
                            entity.setCity(smallClass.getCity());
                            entity.setCounty(smallClass.getCounty());
                            entity.setCheckFlag("0");
                            entity.setCreateUser(user.getEmail());
                            entity.setUpdateUser(user.getEmail());
                            double bc = Double.parseDouble(entity.getCompensationStandard()) * Double.parseDouble(entity.getArea());
                            entity.setCompensationAmount(""+bc);



                            entity.setSource(smallClass.getSource());


                            Double smallClassArea = smallClass.getArea();

                            if(null == smallClassArea){
                                errorMap.put(index, "小班数据不存在");
                                continue;
                            }

                            Double areaDb = countryCompensationDetailService.findSmallClassData(entity);


                            if(null == areaDb){
                                areaDb = 0.00;
                            }

                            LoggerUtils.debug(getClass(), "areaDb: " + areaDb.doubleValue());

                            if(areaMap.containsKey(buildKey(entity))){
                                Double importArea =  areaMap.get(buildKey(entity));
                                areaDb = areaDb + importArea;

                                areaMap.put(buildKey(entity), importArea + Double.parseDouble(entity.getArea()));
                            }else{
                                areaDb = areaDb + Double.parseDouble(entity.getArea());
                                areaMap.put(buildKey(entity),Double.parseDouble(entity.getArea()));
                            }

                            LoggerUtils.debug(getClass(), "areaDb: " + areaDb.doubleValue() + "===" + smallClassArea.doubleValue());
                            if(areaDb.doubleValue() > smallClassArea.doubleValue()){
                                if(errorMap.containsKey(index)){
                                    errorMap.put(index, errorMap.get(index) + ", " + "面积错误："+entity.getArea()+" 小班标准面积为：" + smallClassArea + ", 已导入面积：" + (areaDb-Double.parseDouble(entity.getArea())));
                                }else {
                                    errorMap.put(index, "面积错误："+entity.getArea()+" 小班标准面积为：" + smallClassArea + ", 已导入面积：" + (areaDb-Double.parseDouble(entity.getArea())));
                                }

                            }else{
                                insertList.add(entity);
                            }


                        }
                    }

                    if(errorMap.isEmpty()){
                        countryCompensationDetailService.insertList(insertList);
                        resultMap.put("status", 200);
                        resultMap.put("message", "操作成功!");
                    }else {
                        resultMap.put("status", 500);
                        resultMap.put("message", "操作失败!" + errorMap.toString());
                    }




                }
            }


        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败!" + e.getMessage());
            LoggerUtils.fmtError(getClass(), e, "操作失败。[%s]", errorMap.toString());
        }

        LoggerUtils.debug(getClass(), errorMap.toString());

        return resultMap;
    }


    private List<CountryCompensationDetail> processExcel(String filePath) throws Exception{
        String SAMPLE_PERSON_DATA_FILE_PATH = filePath;

        // Input File initialize
        File file = new File(SAMPLE_PERSON_DATA_FILE_PATH);
        InputStream inputStream = new FileInputStream(file);

        // Excel Cell Mapping
        Map<String, String> cellMapping = new HashMap<String, String>();
        cellMapping.put("HEADER", "排列顺序号,乡,村,林班,小班,户名,身份证号码,汇款账号,面积,补偿标准,补偿金额,林木所有权,是否已发放,地类,起源,备注,土地所有权,细班,联户户名,汇款户名,小地名");
        cellMapping.put("B", "town");
        cellMapping.put("C", "village");
        cellMapping.put("D", "forestClass");
        cellMapping.put("E", "smallClass");
        cellMapping.put("F", "username");
        cellMapping.put("G", "identityCard");
        cellMapping.put("H", "remitNum");
        cellMapping.put("I", "area");
        cellMapping.put("J", "compensationStandard");
        cellMapping.put("K", "compensationAmount");
        cellMapping.put("L", "forestBelong");
        cellMapping.put("M", "sendFlag");

        // The package open is instantaneous, as it should be.
        OPCPackage pkg = null;
        try {

            ExcelWorkSheetHandler<CountryCompensationDetail> workSheetHandler =
                    new ExcelWorkSheetHandler<CountryCompensationDetail>(CountryCompensationDetail.class, cellMapping);

            pkg = OPCPackage.open(inputStream);

            ExcelSheetCallback sheetCallback = new ExcelSheetCallback() {
                private int sheetNumber = 0;

                @Override
                public void startSheet(int sheetNum, String sheetName) {
                    this.sheetNumber = sheetNum;
                    System.out.println("Started processing sheet number=" + sheetNumber
                            + " and Sheet Name is '" + sheetName + "'");
                }

                @Override
                public void endSheet() {
                    System.out.println("Processing completed for sheet number=" + sheetNumber);
                }
            };

            System.out.println("Constructor: pkg, workSheetHandler, sheetCallback");
            ExcelReader example1 = new ExcelReader(pkg, workSheetHandler, sheetCallback);
            example1.process();

            if (workSheetHandler.getValueList().isEmpty()) {
                // No data present
                LoggerUtils.error(getClass(),"sHandler.getValueList() is empty");
            } else {

                LoggerUtils.debug(getClass(), workSheetHandler.getValueList().size()
                        + " no. of records read from given excel worksheet successfully.");

                // Displaying data ead from Excel file
                return workSheetHandler.getValueList();
            }

            System.out.println("\nConstructor: filePath, workSheetHandler, sheetCallback");
            ExcelReader example2 =
                    new ExcelReader(SAMPLE_PERSON_DATA_FILE_PATH, workSheetHandler, sheetCallback);
            example2.process();

            System.out.println("\nConstructor: file, workSheetHandler, sheetCallback");
            ExcelReader example3 = new ExcelReader(file, workSheetHandler, null);
            example3.process();

        } catch (RuntimeException are) {
            are.printStackTrace();
            LoggerUtils.error(getClass(),are.getMessage()+ are.getCause());
        } catch (InvalidFormatException ife) {
            ife.printStackTrace();
            LoggerUtils.error(getClass(),ife.getMessage()+ife.getCause());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            LoggerUtils.error(getClass(),ioe.getMessage()+ ioe.getCause());
        } finally {
            IOUtils.closeQuietly(inputStream);
            try {
                if (null != pkg) {
                    pkg.close();
                }
            } catch (IOException e) {
                // just ignore IO exception
            }
        }
        return null;
    }


    private String buildKey(CountryCompensationDetail entity){
        StringBuffer buffer = new StringBuffer();

        buffer.append(entity.getTown()).append(entity.getVillage()).append(entity.getForestClass()).append(entity.getSmallClass());

        return  buffer.toString();


    }

    private List<CountryCompensationDetail> processExcelAll(String filePath) throws Exception{
        List<CountryCompensationDetail> result = new ArrayList<>();
        List<List<Map<String, String>>> sheets = ExcelUtil.readExcelWithTitle(filePath);

        //获取第一个sheet
        if(null != sheets){
            List<Map<String, String>> rows = sheets.get(0);
            if(null != rows)  {
                int length = rows.size();

                String headerStr = "乡,村,林班,小班,户名,身份证号码,汇款账号,面积,补偿标准,是否已发放";
                //校验header
                Map<String, String> map = rows.get(0);
                int headerCount = 0;
                StringBuilder builder = new StringBuilder();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.append(entry.getKey()).append(",");

                    if(headerStr.contains(entry.getKey())){
                        headerCount += 1;
                    }
                }
                LoggerUtils.debug(getClass(),builder.toString());
                if(10 != headerCount){
                    throw new Exception("导入模板头信息错误!");
                }


                for(int i = 0; i <length;i++){
                    Map<String, String> row = rows.get(i);
                    if(null == row.get("乡") || "".equals(row.get("乡").trim())){
                        break;
                    }
                    CountryCompensationDetail entity = new CountryCompensationDetail();
                    entity.setTown(row.get("乡"));
                    entity.setVillage(row.get("村"));
                    entity.setForestClass(proStr0(row.get("林班")));
                    entity.setSmallClass(proStr0(row.get("小班")));
                    entity.setIdentityCard("身份证号码");
                    entity.setRemitNum(row.get("汇款账号"));
                    entity.setArea(row.get("面积"));
                    entity.setCompensationStandard(row.get("补偿标准"));
                    entity.setSendFlag(row.get("是否已发放"));
                    result.add(entity);
                }
            }
        }
        return result;
    }

    private String proStr0(String str){
        if(null == str){
            return "";
        }


        if(str.endsWith(".0")){
            return str.substring(0, str.length() -2);
        }

        if(str.endsWith("0")){
            return str.substring(0, str.length() -1);
        }
        return str;
    }
}



