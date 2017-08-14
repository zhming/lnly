package com.lnly.business.controller;

import com.lnly.business.bo.AdminDictTreeBo;
import com.lnly.business.bo.SmallClassPageEntity;
import com.lnly.business.bo.SmallClassTreeBo;
import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.SmallClassService;
import com.lnly.common.controller.BaseController;
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
import java.util.*;


@Controller
@Scope(value="prototype")
@RequestMapping("smallClass")
public class SmallClassController extends BaseController {

	@Resource
	SmallClassService smallClassService;

    @Resource
    AdminDictService adminDictService;



	@Autowired
	public JedisManager jedisManager;



	private static final String KEY_PRE = "small_class_data_pre_";

	private static final int DB_INDEX = 1;


	static final Class<SmallClassController> SELF = SmallClassController.class;

    private static int iEcho = 0;

    @RequestMapping(value = "smallClassDataDetail", method = RequestMethod.GET)
    public ModelAndView compensationStandard(){
        return new ModelAndView("business/smallClassDataDetail");
    }


    @RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findAll(SmallClassPageEntity param) {
        iEcho++;
        Long searchId = param.getSearchId();
        String dictName = "";
        String colum = "";
        String dictCode = "";
        AdminDict adminDictH = null;
        try {
            AdminDict adminDict = adminDictService.findById(searchId);
            dictCode = adminDict.getDictCode();
            dictName = adminDict.getDictName();


            if(dictCode.equals(StaticValusUtil.root)){
                //省级权限，全部查看
            }else if(dictCode.length() == 6 && dictCode.endsWith("00")){
                //地级市
                colum = "city";
            }else if(dictCode.length() == 6 && !dictCode.endsWith("00")){
                adminDictH = adminDictService.findByDictCode(adminDict.getHighDict());
                //区县
                colum = "county";
            }else if(dictCode.length() > 6){
                adminDictH = adminDictService.findByDictCode(adminDict.getHighDict());
                //乡镇
                colum = "town";
            }else{
                LoggerUtils.debug(SELF, "query info error!adminDictId="+searchId);
                throw new Exception("query info error!");
            }
            

        } catch (Exception e) {
            LoggerUtils.fmtDebug(this.getClass(), "find adminDict fail[id=%s]", searchId);
            e.printStackTrace();
        }


		List<Map<String, Object>> result = null;
		byte[] byteKey = SerializeUtil.serialize(buildCacheKey(param.getSearchYear(), param.getSearchContentFromSelect()));
		byte[] byteValue = new byte[0];
		try {
			byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
			result = (   List<Map<String, Object>>) SerializeUtil.deserialize(byteValue);
			LoggerUtils.debug(SELF, "This value from cache!" + result.size());
		} catch (Exception e) {
            LoggerUtils.error(SELF, "SmallClass get tree by cache throw exception", e);

            //从DB获取数据
            SmallClass queryEntity = new SmallClass();

            queryEntity.setYear(param.getSearchYear());

            if(colum.equalsIgnoreCase(StaticValusUtil.root)){

            }else if(colum.equalsIgnoreCase("city")){
              queryEntity.setCity(dictName);
            }else if(colum.equalsIgnoreCase("county")){
                queryEntity.setCity(adminDictH.getDictName());
                queryEntity.setCounty(dictName);
            }else if(colum.equalsIgnoreCase("town")){
                queryEntity.setCounty(adminDictH.getDictName());
                queryEntity.setTown(dictName);
            }

            Pagination<SmallClass> pagination = null;
            List<SmallClass> resultList = null;
            int total = 0;
            try {
                pagination = smallClassService.findAll(queryEntity, param.getiDisplayStart()/param.getiDisplayLength(), param.getiDisplayLength());


                total = pagination.getTotalCount();
                if(null != pagination){
                    resultList = pagination.getList();
                }

            } catch (Exception e1) {
                LoggerUtils.fmtDebug(this.getClass(), "find smallClass data fail[id=%s]", searchId);
                e1.printStackTrace();
            }
            resultMap.put("iEcho", iEcho);
            resultMap.put("data", resultList);
            resultMap.put("iDisplayLength", param.getiDisplayLength());
            resultMap.put("iDisplayStart", param.getiDisplayStart());

            resultMap.put("recordsTotal", total);
            resultMap.put("recordsFiltered", total);
            resultMap.put("sColumns", ",,,,");
            resultMap.put("iColumns", 9);
            resultMap.put("message", "ok");
            return resultMap;
        }
			


		return resultMap;
	}


	

	private String buildCacheKey(String year, String key) {
		return KEY_PRE + year+ "_" + key;
	}
}
