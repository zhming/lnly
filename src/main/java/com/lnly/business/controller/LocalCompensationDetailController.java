package com.lnly.business.controller;

import com.lnly.business.bo.BcbzPageEntity;
import com.lnly.business.service.LocalCompensationDetailService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.LocalCompensationDetail;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.StringUtils;
import com.lnly.core.mybatis.page.PageEntity;
import com.lnly.core.mybatis.page.Pagination;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Scope(value="prototype")
@RequestMapping("localDetail")
public class LocalCompensationDetailController extends BaseController {

    @Resource
    LocalCompensationDetailService localCompensationDetailService;

    private static int iEcho = 0;

 
    @RequestMapping(value = "localCompensationDetail", method = RequestMethod.GET)
    public ModelAndView compensationStandard(){
        return new ModelAndView("business/localCompensationDetail");
    }

    /**
     * @param page
     * @return
     */
    @RequestMapping(value="{page}",method=RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("page")String page){
        return new ModelAndView(String.format("local/%s", page));
    }

    @RequestMapping(value="findOne",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findOne(Long id){

        LocalCompensationDetail localCompensationStandard = localCompensationDetailService.selectByPrimaryKey(id);
        resultMap.put("object", localCompensationStandard);
        resultMap.put("message", "ok");
        return resultMap;
    }

    @RequestMapping(value="findAll",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllPost(BcbzPageEntity param){
        iEcho++;
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(param.getSearchYear())){
            map.put("year", param.getSearchYear());
        }

        if(StringUtils.isNotBlank(param.getSearchContentFromSelect()) && !"210000".equals(param.getSearchContentFromSelect())){
            map.put("searchContent", param.getSearchContentFromSelect());
        }

        if(StringUtils.isNotBlank(param.getSearchContent())){
            map.put("searchContent", param.getSearchContent());
        }




        List<LocalCompensationDetail> localCompensationStandards = null;
        try {
            Pagination<LocalCompensationDetail> pagination =  localCompensationDetailService.findByPage(map, param.getiDisplayStart(), param.getiDisplayLength());
            if(null != pagination){
                localCompensationStandards = pagination.getList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("iEcho", iEcho);
        resultMap.put("data", localCompensationStandards);
        resultMap.put("iDisplayLength", param.getiDisplayLength());
        resultMap.put("iDisplayStart", param.getiDisplayStart());
        resultMap.put("total", localCompensationStandards.size());
        resultMap.put("sColumns", ",,,,");
        resultMap.put("iColumns", 9);
        resultMap.put("message", "ok");
        return resultMap;
    }

    /**
     * 新增国家补偿标准
     * @return
     */
    @RequestMapping(value="add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(LocalCompensationDetail entity){
        try {
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            localCompensationDetailService.insert(entity);
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
     * @return
     */
    @RequestMapping(value="update",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(LocalCompensationDetail entity){
        try {
            localCompensationDetailService.updateByPrimaryKey(entity);
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
     * 删除
     * @return
     */
    @RequestMapping(value="delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(Long id){
        try {
            localCompensationDetailService.deleteByPrimaryKey(id);
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
     * @return
     */
    @RequestMapping(value="findAllPage",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findAll(ModelMap modelMap, PageEntity pageEntity){

        Map<String, Object> result = new HashMap<>();

        Pagination<LocalCompensationDetail> pagination = localCompensationDetailService.findByPage(modelMap, pageEntity.getiDisplayStart(),pageEntity.getiDisplayLength());
        result.put("data", pagination.getList());
        result.put("length", pagination.getList().size());
        result.put("sEcho",1);
        result.put("iColumns",5);result.put("sColumns",",,,,");result.put("iDisplayStart",0);
        result.put("iDisplayLength",10);

        return result;
    }
}
