package com.lnly.business.bo;

import java.util.List;

/**
 * Created by monsoon on 2017-07-08.
 */
public class AdminDictTreeBo {
    private String dictCode;
    private String dictName;
    private List<AdminDictTreeBo> children;

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public List<AdminDictTreeBo> getChildren() {
        return children;
    }

    public void setChildren(List<AdminDictTreeBo> children) {
        this.children = children;
    }
}
