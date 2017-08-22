package com.lnly.business.controller;

import com.lnly.business.bo.BcbzPageEntity;
import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.CountryCompensationDetailService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.common.model.UUser;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.OSInfo;
import com.lnly.common.utils.StringUtils;
import com.lnly.common.utils.poi.ExcelReader;
import com.lnly.common.utils.poi.ExcelSheetCallback;
import com.lnly.common.utils.poi.ExcelWorkSheetHandler;
import com.lnly.core.mybatis.page.PageEntity;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.user.service.UUserService;
import net.sf.json.JSONObject;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
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
        }

        if (StringUtils.isNotBlank(param.getSearchContentFromSelect())
                && !"210000".equals(param.getSearchContentFromSelect())
                && !"辽宁省".equals(param.getSearchContentFromSelect())) {
            map.put("searchContent", param.getSearchContentFromSelect());
        }

        if (StringUtils.isNotBlank(param.getSearchContent())) {
            map.put("searchContent", param.getSearchContent());
        }

        if(!map.containsKey("")) {
            String searchEmail = param.getSearchEmail();
            try {
                AdminDict dict = adminDictService.findByDictCode(dictCode);
                map.put("searchContent", dict.getDictName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


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
    @RequestMapping("/upload")
    public Map<String, Object> upload2(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "";
        String email = request.getParameter("email");
        LoggerUtils.debug(getClass(),"############# "+ email);
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
                    AdminDict dict =   adminDictService.findByDictCode(user.getDictCode());
                    AdminDict adminDict =   adminDictService.findByDictCode(user.getHighDictCode());
                    AdminDict adminDictH =   adminDictService.findByDictCode(adminDict.getHighDict());
                    //解析数据
                    List<CountryCompensationDetail> list =   processExcel(localFile);
                    if(null != list){
                        for  (CountryCompensationDetail entity : list){
                            if(!entity.getTown().equalsIgnoreCase(dict.getDictName())){
                                throw  new Exception("导入数据错误：乡");
                            }
                            LoggerUtils.debug(getClass(), entity.toString());
                            entity.setYear(new DateTime().toString("yyyy"));
                            entity.setCity(adminDictH.getDictName());
                            entity.setCounty(adminDict.getDictName());
                            entity.setCheckFlag("0");
                            entity.setCreateUser(user.getEmail());
                            entity.setUpdateUser(user.getEmail());
                            countryCompensationDetailService.insert(entity);
                        }
                    }


                    resultMap.put("status", 200);
                    resultMap.put("message", "操作成功!");
                }
            }


        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败!" + e.getMessage());
            LoggerUtils.fmtError(getClass(), e, "操作失败。[%s]", fileName);
        }

        return resultMap;
    }


    private List<CountryCompensationDetail> processExcel(String filePath) throws Exception{
        String SAMPLE_PERSON_DATA_FILE_PATH = filePath;

        // Input File initialize
        File file = new File(SAMPLE_PERSON_DATA_FILE_PATH);
        InputStream inputStream = new FileInputStream(file);

        // Excel Cell Mapping
        Map<String, String> cellMapping = new HashMap<String, String>();
        cellMapping.put("HEADER", "乡,村,林班,小班,户名,身份证号码,汇款账号,面积(亩),补偿标准,是否发放");
        cellMapping.put("A", "town");
        cellMapping.put("B", "village");
        cellMapping.put("C", "forestClass");
        cellMapping.put("D", "smallClass");
        cellMapping.put("E", "username");
        cellMapping.put("F", "identityCard");
        cellMapping.put("G", "remitNum");
        cellMapping.put("H", "area");
        cellMapping.put("I", "compensationStandard");
        cellMapping.put("J", "sendFlag");

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
}



