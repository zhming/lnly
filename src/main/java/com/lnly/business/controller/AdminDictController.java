package com.lnly.business.controller;

import com.lnly.business.bo.AdminDictTreeBo;
import com.lnly.business.service.AdminDictService;
import com.lnly.common.controller.BaseController;
import com.lnly.common.model.AdminDict;
import com.lnly.common.model.UPermission;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.SerializeUtil;
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
@Scope(value = "prototype")
@RequestMapping("adminDict")
public class AdminDictController extends BaseController {

    @Resource
    AdminDictService adminDictService;

    @Autowired
    public JedisManager jedisManager;

    private static final String ADMIN_DICT_TREE_PRE = "admin_dict_tree_pre_";

    private static final int DB_INDEX = 1;


    static final Class<AdminDictController> SELF = AdminDictController.class;

    /**
     * 个人资料
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView userIndex() {

        return new ModelAndView("admindict/index");
    }


    @RequestMapping(value = "findDictTree", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> findDictTree(String dictCode) {
        String dictName = "";
        List<Map<String, Object>> result = null;
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(dictCode));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
            result = (   List<Map<String, Object>>) SerializeUtil.deserialize(byteValue);
            LoggerUtils.debug(SELF, "This value from cache!" + result.size());
        } catch (Exception e) {
            LoggerUtils.error(SELF, "AdminDict get tree by cache throw exception", e);
            AdminDictTreeBo rootTree = null;

            try {

                dictName = adminDictService.findByDictCode(dictCode).getDictName();


                rootTree = new AdminDictTreeBo();
                rootTree.setDictCode(dictCode);
                rootTree.setDictName(dictName);
                rootTree.setChildren(new ArrayList<AdminDictTreeBo>());


                //地级市
                List<AdminDict> listA = adminDictService.findByHighDict(rootTree.getDictCode());
                for (AdminDict dict : listA) {
                    AdminDictTreeBo treeBo = new AdminDictTreeBo();
                    treeBo.setDictCode(dict.getDictCode());
                    treeBo.setDictName(dict.getDictName());
                    treeBo.setChildren(new ArrayList<AdminDictTreeBo>());
                    rootTree.getChildren().add(treeBo);
                }

                //区县
                List<AdminDictTreeBo> listB = rootTree.getChildren();
                if (null != listB) {
                    for (AdminDictTreeBo treeBo : listB) {
                        List<AdminDict> listC = adminDictService.findByHighDict(treeBo.getDictCode());
                        for (AdminDict dict : listC) {
                            AdminDictTreeBo treeBoC = new AdminDictTreeBo();
                            treeBoC.setDictCode(dict.getDictCode());
                            treeBoC.setDictName(dict.getDictName());
                            treeBoC.setChildren(new ArrayList<AdminDictTreeBo>());
                            treeBo.getChildren().add(treeBoC);
                        }

                        //乡村
                        List<AdminDictTreeBo> listD = treeBo.getChildren();
                        if (null != listD) {
                            for (AdminDictTreeBo treeBo1 : listD) {
                                List<AdminDict> listE = adminDictService.findByHighDict(treeBo1.getDictCode());
                                if(null != listD){
                                    for (AdminDict dict : listE) {
                                        AdminDictTreeBo treeBoE = new AdminDictTreeBo();
                                        treeBoE.setDictCode(dict.getDictCode());
                                        treeBoE.setDictName(dict.getDictName());
                                        treeBoE.setChildren(new ArrayList<AdminDictTreeBo>());
                                        treeBo1.getChildren().add(treeBoE);
                                    }
                                }
                            }
                        }
                    }
                }

                result = toTreeData(rootTree);
                if(null != result){
                    try {
                        jedisManager.deleteByKey(DB_INDEX, byteKey);
                        jedisManager.saveValueByKey(DB_INDEX, byteKey,SerializeUtil.serialize(result), 50000 );
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    LoggerUtils.debug(SELF, "This value from DB!" + result.size());
                }

            } catch (Exception e1) {
                e.printStackTrace();
                LoggerUtils.error(this.getClass(), e.getMessage());
            }

        }
        return result;
    }


    /**
     * 把查询出来的roles 转换成bootstarp 的 tree数据
     *
     * @param root
     * @return
     */
    public static List<Map<String, Object>> toTreeData(AdminDictTreeBo root) {
        List<Map<String, Object>> resultData = new LinkedList<Map<String, Object>>();

        //角色列表
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("text", root.getDictName());//名称
        map.put("href", "javascript:void(0)");//链接
        List<AdminDictTreeBo> ps = root.getChildren();
        map.put("tags", new Integer[]{ps.size()});//显示子数据条数
        if (null != ps && ps.size() > 0) {
            List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
            //权限列表
            for (AdminDictTreeBo up : ps) {
                Map<String, Object> mapx = new LinkedHashMap<String, Object>();
                mapx.put("text", up.getDictName());//权限名称
                mapx.put("href", "javascript:void(0)");//权限url
                mapx.put("tags", "1");//没有下一级
                mapx.put("dictCode", up.getDictCode());//权限名称

                List<AdminDictTreeBo> ps1 = up.getChildren();
                mapx.put("tags", new Integer[]{ps1.size()});//显示子数据条数
                if (null != ps1 && ps.size() > 0) {
                    List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();
                    //权限列表
                    for (AdminDictTreeBo up1 : ps1) {
                        Map<String, Object> mapx1 = new LinkedHashMap<String, Object>();
                        mapx1.put("text", up1.getDictName());//权限名称
                        mapx1.put("nodeId", up1.getDictCode());
                        mapx1.put("href", " up1.getDictCode()");//权限url
                        mapx1.put("tags", "1");//没有下一级
                        mapx1.put("dictCode", up1.getDictCode());//权限名称

                        List<AdminDictTreeBo> ps2 = up1.getChildren();
                        mapx1.put("tags", new Integer[]{ps2.size()});//显示子数据条数
                        if (null != ps1 && ps.size() > 0) {
                            List<Map<String, Object>> list2 = new LinkedList<Map<String, Object>>();
                            //权限列表
                            for (AdminDictTreeBo up2 : ps2) {
                                Map<String, Object> mapx2 = new LinkedHashMap<String, Object>();
                                mapx2.put("text", up2.getDictName());//权限名称
                                mapx2.put("nodeId", up2.getDictCode());
                                mapx2.put("href", " up2.getDictCode()");//权限url
                                mapx2.put("tags", "0");//没有下一级
                                mapx2.put("dictCode", up2.getDictCode());//权限名称

                                list2.add(mapx2);
                            }
                            mapx1.put("nodes", list2);

                        }

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


    @RequestMapping(value = "findByDictCode", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findByDictCOde(String dictCode) {
        Map<String, Object> result = new HashMap<>();
        try {
            AdminDict entity = adminDictService.findByDictCode(dictCode);
            result.put("data", entity);
            result.put("message", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");

        }


        return result;
    }


    private String buildCacheKey(String key) {
        return ADMIN_DICT_TREE_PRE + key;
    }

}
