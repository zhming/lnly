package com.lnly.business.controller;

import com.lnly.business.service.LocalCompensationStandardService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.LocalCompensationStandard;
import com.lnly.common.utils.LoggerUtils;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
	 * 个人资料修改
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
}
