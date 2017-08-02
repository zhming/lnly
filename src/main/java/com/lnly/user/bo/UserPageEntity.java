package com.lnly.user.bo;


import com.lnly.core.mybatis.page.PageEntity;

public class UserPageEntity extends PageEntity {
    private String nickname;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
