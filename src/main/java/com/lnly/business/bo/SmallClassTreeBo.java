package com.lnly.business.bo;

import java.util.List;

/**
 * Created by monsoon on 2017-07-08.
 */
public class SmallClassTreeBo {
    private Long id;
    private String dictCode;
    private String dictName;
    private List<SmallClassTreeBo> children;

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

    public List<SmallClassTreeBo> getChildren() {
        return children;
    }

    public void setChildren(List<SmallClassTreeBo> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
