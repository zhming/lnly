package com.lnly.business.controller;

import com.lnly.business.bo.CountryCompensationStandardBo;
import com.lnly.business.bo.LocalCompensationStandardBo;
import com.lnly.business.service.LocalCompensationStandardService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.LocalCompensationStandard;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.StringUtils;
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
import java.util.Date;
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
@RequestMapping("localStandard")
public class LocalCompensactionStandardCoreController extends BaseController {

	@Resource
	LocalCompensationStandardService localCompensationStandardService;
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView userIndex(){
		
		return new ModelAndView("country/index");
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
	public Map<String,Object> getAll(Long id){

		LocalCompensationStandard localCompensationStandard = localCompensationStandardService.selectByPrimaryKey(id);
		resultMap.put("object", localCompensationStandard);
		resultMap.put("message", "ok");
		return resultMap;
	}

	/**
	 * 修改
	 * @return
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(LocalCompensationStandard entity){
		try {
			localCompensationStandardService.updateByPrimaryKeySelective(entity);
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
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(Long id){
		try {
			localCompensationStandardService.deleteByPrimaryKey(id);
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
	 * 新增地方补偿标准
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(LocalCompensationStandard entity){
		try {
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			localCompensationStandardService.insert(entity);
			resultMap.put("status", 200);
			resultMap.put("message", "保存成功!");
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "保存失败!");
			LoggerUtils.fmtError(getClass(), e, "保存失败。[%s]", JSONObject.fromObject(entity).toString());
		}
		return resultMap;
	}


	@RequestMapping(value="findAll",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAllPost(String dictCode, Integer year){
		System.out.println(dictCode);
		System.out.println(year);
		String city ="";
		String county = "";

		if(StringUtils.isNotBlank(dictCode)){
			dictCode = StringUtils.getNum(dictCode);
			if(dictCode.length() == 6){
				county = dictCode;
			}else{
				city = dictCode;
			}
		}


		List<LocalCompensationStandard> countryCompensationStandards = null;
		List<CountryCompensationStandardBo> resultList = new ArrayList<CountryCompensationStandardBo>();
		try {
			countryCompensationStandards = localCompensationStandardService.findAll(city,county, year);
			for(LocalCompensationStandard entity : countryCompensationStandards){
				CountryCompensationStandardBo bo = new CountryCompensationStandardBo();
				bo.setYear(entity.getYear());
				bo.setId(entity.getId());
				bo.setArea(entity.getArea());
				bo.setCity(entity.getCity());
				bo.setComment(entity.getComment());
				bo.setJe(entity.getJe());
				bo.setCounty(entity.getCounty());
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




	public static void main(String[] args){
		String str = "2101沈阳";
		System.out.println(StringUtils.getNum(str));
	}

}
