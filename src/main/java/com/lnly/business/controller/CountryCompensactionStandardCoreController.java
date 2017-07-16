package com.lnly.business.controller;

import com.lnly.business.bo.CountryCompensationStandardBo;
import com.lnly.business.service.CountryCompensationStandardService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.CountryCompensationStandard;
import com.lnly.common.utils.LoggerUtils;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * 
 * 用户管理
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月3日 　<br/>
 * <p>
 * *******
 * <p>
 * @author zhou-baicheng
 * @email  i@itboy.net
 * @version 1.0,2016年5月3日 <br/>
 * 
 */
@Controller
@Scope(value="prototype")
@RequestMapping("countryStandard")
public class CountryCompensactionStandardCoreController extends BaseController {

	@Resource
	CountryCompensationStandardService countryCompensationStandardService;
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView userIndex(){
		
		return new ModelAndView("country/index");
}
	@RequestMapping(value = "compensationStandard", method = RequestMethod.GET)
	public ModelAndView ompensationStandard(){
		return new ModelAndView("business/compensationStandard");
	}
	
	/**
	 * 偷懒一下，通用页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value="{page}",method=RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("page")String page){
		return new ModelAndView(String.format("country/%s", page));
	}
	
	@RequestMapping(value="findOne",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> findOne(Long id){

		CountryCompensationStandard countryCompensationStandard = countryCompensationStandardService.selectByPrimaryKey(id);
		resultMap.put("object", countryCompensationStandard);
		resultMap.put("message", "ok");
		return resultMap;
	}

	@RequestMapping(value="findAll",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAll(Long id){

		List<CountryCompensationStandard> countryCompensationStandards = null;
		List<CountryCompensationStandardBo> resultList = new ArrayList<CountryCompensationStandardBo>();
		try {
			countryCompensationStandards = countryCompensationStandardService.findAll();
			for(CountryCompensationStandard entity : countryCompensationStandards){
				CountryCompensationStandardBo bo = new CountryCompensationStandardBo();
				bo.setYear(entity.getYear());
				bo.setId(entity.getId());
				bo.setArea(entity.getArea());
				bo.setCity(entity.getCity());
				bo.setComment(entity.getComment());
				bo.setCountryZbje(entity.getCountryZbje());
				bo.setCounty(entity.getCounty());
				bo.setOtherZbje(entity.getOtherZbje());
				bo.setCreateTimeStr(new DateTime(entity.getCreateTime()).toString("yyyy-MM-dd"));
				resultList.add(bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("data", resultList);
		resultMap.put("message", "ok");
		return resultMap;
	}

	/**
	 * 个人资料修改
	 * @return
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(CountryCompensationStandard entity){
		try {
			countryCompensationStandardService.updateByPrimaryKeySelective(entity);
			resultMap.put("status", 200);
			resultMap.put("message", "修改成功!");
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "修改失败!");
			LoggerUtils.fmtError(getClass(), e, "修改失败。[%s]", JSONObject.fromObject(entity).toString());
		}
		return resultMap;
	}
}
