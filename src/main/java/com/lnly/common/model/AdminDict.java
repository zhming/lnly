package com.lnly.common.model;

import java.io.Serializable;

/**
 * Created by monsoon on 2017-07-07.
 */
public class AdminDict implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String dictCode;
    private String dictName;
    private String highDict;
    private String comment;

    public AdminDict() {
    }

    public AdminDict(Long id, String dictCode, String dictName, String highDict, String comment) {
        this.id = id;
        this.dictCode = dictCode;
        this.dictName = dictName;
        this.highDict = highDict;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getHighDict() {
        return highDict;
    }

    public void setHighDict(String highDict) {
        this.highDict = highDict;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
