package com.lnly.business.service.impl;

import com.lnly.business.bo.AdminDictTreeBo;
import com.lnly.business.service.AdminDictService;
import com.lnly.common.dao.AdminDictMapper;
import com.lnly.common.dao.CountryCompensationStandardMapper;
import com.lnly.common.model.AdminDict;
import com.lnly.common.utils.LoggerUtils;
import com.lnly.common.utils.SerializeUtil;
import com.lnly.core.mybatis.BaseMybatisDao;
import com.lnly.core.shiro.cache.JedisManager;
import com.lnly.core.shiro.session.CustomSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Service
public class AdminDictServiceImpl extends BaseMybatisDao<AdminDictMapper> implements AdminDictService {
    @Autowired
    CustomSessionManager customSessionManager;
    @Autowired
    AdminDictMapper adminDictMapper;

    @Autowired
    public JedisManager jedisManager;

    private static final String ADMIN_DICT_SERVICE_PRE = "ADMIN_DICT_SERVICE_PRE_";

    private static final int DB_INDEX = 1;


    static final Class<AdminDictServiceImpl> SELF = AdminDictServiceImpl.class;

    @Override
    public List<AdminDict> findAll() throws Exception {
        return adminDictMapper.findAll();
    }

    @Override
    public List<AdminDict> findByHighDict(String highDict) throws Exception {
        return adminDictMapper.findByHighDict(highDict);
    }

    @Override
    public AdminDict findByDictCode(String dictCode) throws Exception {
        AdminDict result = null;
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(dictCode));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
            result = (AdminDict) SerializeUtil.deserialize(byteValue);
            LoggerUtils.debug(SELF, "This value from cache!" + result.getDictCode());
        } catch (Exception e) {
            LoggerUtils.error(SELF, "AdminDict get value by cache throw exception", e);
            result = adminDictMapper.findByDictCode(dictCode);
            if(null != result){
                jedisManager.saveValueByKey(DB_INDEX, byteKey,SerializeUtil.serialize(result), 50000 );
                LoggerUtils.debug(SELF, "This value from DB!" + result.getDictCode());
            }
        }


        return result;
    }

    @Override
    public AdminDict findById(Long id) throws Exception {


        return adminDictMapper.findById(id);
    }


    public List<Map<String, Object>> getAllDict(String dictCode) throws Exception{
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

                dictName = findByDictCode(dictCode).getDictName();


                rootTree = new AdminDictTreeBo();
                rootTree.setId(findByDictCode(dictCode).getId());
                rootTree.setDictCode(dictCode);
                rootTree.setDictName(dictName);
                rootTree.setChildren(new ArrayList<AdminDictTreeBo>());


                //地级市
                List<AdminDict> listA = findByHighDict(rootTree.getDictCode());
                for (AdminDict dict : listA) {
                    AdminDictTreeBo treeBo = new AdminDictTreeBo();
                    treeBo.setId(dict.getId());
                    treeBo.setDictCode(dict.getDictCode());
                    treeBo.setDictName(dict.getDictName());
                    treeBo.setChildren(new ArrayList<AdminDictTreeBo>());
                    rootTree.getChildren().add(treeBo);
                }

                //区县
                List<AdminDictTreeBo> listB = rootTree.getChildren();
                if (null != listB) {
                    for (AdminDictTreeBo treeBo : listB) {
                        List<AdminDict> listC = findByHighDict(treeBo.getDictCode());
                        for (AdminDict dict : listC) {
                            AdminDictTreeBo treeBoC = new AdminDictTreeBo();
                            treeBoC.setId(dict.getId());
                            treeBoC.setDictCode(dict.getDictCode());
                            treeBoC.setDictName(dict.getDictName());
                            treeBoC.setChildren(new ArrayList<AdminDictTreeBo>());
                            treeBo.getChildren().add(treeBoC);
                        }

                        //乡村
                        List<AdminDictTreeBo> listD = treeBo.getChildren();
                        if (null != listD) {
                            for (AdminDictTreeBo treeBo1 : listD) {
                                List<AdminDict> listE = findByHighDict(treeBo1.getDictCode());
                                if(null != listD){
                                    for (AdminDict dict : listE) {
                                        AdminDictTreeBo treeBoE = new AdminDictTreeBo();
                                        treeBoE.setId(dict.getId());
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

    @Override
    public AdminDict findByDictName(String dictName) throws Exception {
        AdminDict result = null;
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(dictName));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
            result = (AdminDict) SerializeUtil.deserialize(byteValue);
            LoggerUtils.debug(SELF, "This value from cache!" + result.getDictCode());
        } catch (Exception e) {
            LoggerUtils.error(SELF, "AdminDict get value by cache throw exception", e);
            result = adminDictMapper.findByDictName(dictName);
            if(null != result){
                jedisManager.saveValueByKey(DB_INDEX, byteKey,SerializeUtil.serialize(result), 50000 );
                LoggerUtils.debug(SELF, "This value from DB!" + result.getDictCode());
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
        List<AdminDictTreeBo> ps = root.getChildren();
        map.put("tags", new Long[]{root.getId()});//显示子数据条数
        if (null != ps && ps.size() > 0) {
            List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
            //权限列表
            for (AdminDictTreeBo up : ps) {
                Map<String, Object> mapx = new LinkedHashMap<String, Object>();
                mapx.put("text", up.getDictName());//权限名称
                mapx.put("tags", new Long[]{up.getId()});//没有下一级

                List<AdminDictTreeBo> ps1 = up.getChildren();
                mapx.put("tags", new Long[]{up.getId()});//显示子数据条数
                if (null != ps1 && ps.size() > 0) {
                    List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();
                    //权限列表
                    for (AdminDictTreeBo up1 : ps1) {
                        Map<String, Object> mapx1 = new LinkedHashMap<String, Object>();
                        mapx1.put("text", up1.getDictName());//权限名称
                        mapx1.put("tags",  new Long[]{up1.getId()});//没有下一级

                        List<AdminDictTreeBo> ps2 = up1.getChildren();
                        mapx1.put("tags", new Long[]{up1.getId()});//显示子数据条数  ,
                        if (null != ps1 && ps.size() > 0) {
                            List<Map<String, Object>> list2 = new LinkedList<Map<String, Object>>();
                            //权限列表
                            for (AdminDictTreeBo up2 : ps2) {
                                Map<String, Object> mapx2 = new LinkedHashMap<String, Object>();
                                mapx2.put("text", up2.getDictName());//权限名称
                                mapx2.put("tags",  new Long[]{up2 .getId()});//没有下一级

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

    private String buildCacheKey(String key) {
        return ADMIN_DICT_SERVICE_PRE + key;
    }


}
