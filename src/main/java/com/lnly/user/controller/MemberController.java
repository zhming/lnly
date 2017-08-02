package com.lnly.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.lnly.common.controller.BaseController;
import com.lnly.common.model.UUser;
import com.lnly.common.utils.StringUtils;
import com.lnly.core.mybatis.page.Pagination;
import com.lnly.core.shiro.session.CustomSessionManager;
import com.lnly.user.bo.UserOnlineBo;
import com.lnly.user.bo.UserPageEntity;
import com.lnly.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * 
 * 用户会员管理
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月26日 　<br/>
 * <p>
 * *******
 * <p>
 * @author zhou-baicheng
 * @email  i@itboy.net
 * @version 1.0,2016年5月26日 <br/>
 * 
 */
@Controller
@Scope(value="prototype")
@RequestMapping("member")
public class MemberController extends BaseController {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
    CustomSessionManager customSessionManager;
	@Autowired
    UUserService userService;

	public static int iEcho = 1;
	/**
	 * 用户列表管理
	 * @return
	 */
	@RequestMapping(value="list")
	public ModelAndView list(ModelMap map,Integer pageNo,String findContent){
		
		map.put("findContent", findContent);
		Pagination<UUser> page = userService.findByPage(map,pageNo,pageSize);
		map.put("page", page);
		return new ModelAndView("member/list");
	}
	/**
	 * 在线用户管理
	 * @return
	 */
	@RequestMapping(value="online")
	public ModelAndView online(){
		List<UserOnlineBo> list = customSessionManager.getAllUser();
		return new ModelAndView("member/online","list",list);
	}
	/**
	 * 在线用户详情
	 * @return
	 */
	@RequestMapping(value="onlineDetails/{sessionId}",method=RequestMethod.GET)
	public ModelAndView onlineDetails(@PathVariable("sessionId")String sessionId){
		UserOnlineBo bo = customSessionManager.getSession(sessionId);
		return new ModelAndView("member/onlineDetails","bo",bo);
	}
	/**
	 * 改变Session状态
	 * @param status
	 * @param
	 * @return
	 */
	@RequestMapping(value="changeSessionStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> changeSessionStatus(Boolean status,String sessionIds){
		return customSessionManager.changeSessionStatus(status,sessionIds);
	}
	/**
	 * 根据ID删除，
	 * @param ids	如果有多个，以“,”间隔。
	 * @return
	 */
	@RequestMapping(value="deleteUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteUserById(String ids){
		return userService.deleteUserById(ids);
	}
	/**
	 * 禁止登录
	 * @param id		用户ID
	 * @param status	1:有效，0:禁止登录
	 * @return
	 */
	@RequestMapping(value="forbidUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> forbidUserById(Long id,Long status){
		return userService.updateForbidUserById(id,status);
	}

	/**
	 *
	 * @return
	 */
	@RequestMapping(value="datatables",method =RequestMethod.GET)
	@ResponseBody
	public ModelAndView test(){
		return new ModelAndView("member/datatables");
	}


	/**
	 * 在线用户管理
	 * @return
	 */
	@RequestMapping(value="findAll",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findAll(ModelMap modelMap, UserPageEntity entity){
        Map<String, Object> result = new HashMap<>();
		iEcho = iEcho + 1;
	    try{
if(StringUtils.isNotBlank(entity.getNickname())){
    System.out.println( "@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+new String(entity.getNickname().getBytes(), "utf-8"));
    modelMap.put("nickname", entity.getNickname());
}


//		Pagination<UUser> pagination = userService.findAll(modelMap, pageEntity.getiDisplayStart(),pageEntity.getiDisplayLength());
            Pagination<UUser> pagination = userService.findCondition(modelMap, entity.getiDisplayStart(),entity.getiDisplayLength());
            result.put("data", pagination.getList());

            System.out.println("--------------------------------: " + entity.getiDisplayLength());
            System.out.println("--------------------------------: " + entity.getiDisplayStart());

            // json = "{\"sEcho\":" + initEcho + ",\"iTotalRecords\":" + count + ",\"iTotalDisplayRecords\":" + count + ",\"aaData\":" + jsonArray2.toString() + "}";

            result.put("iEcho",iEcho);
            result.put("iColumns",5);
            result.put("sColumns",",,,,");
            result.put("iDisplayStart",entity.getiDisplayStart());
            result.put("iDisplayLength",entity.getiDisplayLength());
            result.put("total", pagination.getTotalCount());
        }catch (Exception e){
	        e.printStackTrace();
        }


		return result;
	}


	public static void main(String[] args){
		String ss = "(12, '8446666', '8446666', '4afdc875a67a55528c224ce088be2ab8', '2016-05-27 22:34:19', '2016-06-15 17:03:16', 1),";

		for(int i = 0; i < 5000; i++){
			System.out.println("(" + (12 + i) +", '8446666"+i+"', '8446666"+i+"', '4afdc875a67a55528c224ce088be2ab8', '2016-05-27 22:34:19', '2016-06-15 17:03:16', 1),");
		}
	}

}
