package com.lnly.business.controller;

import com.lnly.business.bo.AdminDictTreeBo;
import com.lnly.business.service.AdminDictService;
import com.lnly.business.service.SmallClassService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.SmallClass;
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
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView userIndex(){
		
		return new ModelAndView("admindict/index");
	}
	
	
	@RequestMapping(value="findListByYear",method=RequestMethod.GET)
	@ResponseBody
	public List<SmallClass> getListByYear(Long year){
		List<SmallClass> list = null;
		try {
			list = smallClassService.findListByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


	/**
	 * 把查询出来的roles 转换成bootstarp 的 tree数据
	 * @param root
	 * @return
	 */
	public static List<Map<String,Object>> toTreeData(AdminDictTreeBo root){
		List<Map<String,Object>> resultData = new LinkedList<Map<String,Object>>();

			//角色列表
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			map.put("text", root.getDictName());//名称
			map.put("href", "javascript:void(0)");//链接
			List<AdminDictTreeBo> ps =  root.getChildren();
			map.put("tags",  new Integer[]{ps.size()});//显示子数据条数
			if(null != ps && ps.size() > 0){
				List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
				//权限列表
				for (AdminDictTreeBo up : ps) {
					Map<String,Object> mapx = new LinkedHashMap<String, Object>();
					mapx.put("text", up.getDictName());//权限名称
					mapx.put("href",  "javascript:void(0)");//权限url
					mapx.put("tags", "1");//没有下一级


					List<AdminDictTreeBo> ps1 =  up.getChildren();
					mapx.put("tags",  new Integer[]{ps1.size()});//显示子数据条数
					if(null != ps1 && ps.size() > 0){
						List<Map<String,Object>> list1 = new LinkedList<Map<String,Object>>();
						//权限列表
						for (AdminDictTreeBo up1 : ps1) {
							Map<String,Object> mapx1 = new LinkedHashMap<String, Object>();
							mapx1.put("text", up1.getDictName());//权限名称
							mapx1.put("href",  "javascript:void(0)");//权限url
							mapx1.put("tags", "0");//没有下一级
							list1.add(mapx1);
						}
						mapx.put("nodes", list1);

					}
					list.add(mapx);
				}
				map.put("nodes", list);
			}
			resultData.add(map);
		return resultData;

	}

}
