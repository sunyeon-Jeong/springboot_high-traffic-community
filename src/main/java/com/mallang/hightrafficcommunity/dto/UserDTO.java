package com.mallang.hightrafficcommunity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class UserDTO {

    public enum Status {
        DEFAULT, ADMIN, DELETED
    }

    // USER Table column
    private int userId;
    private String username;
    private String password;
    private String nickName;
    private boolean isAdmin;
    private boolean isWithDraw;
    private Date createTime;
    private Date modifyTime;
    private Status status;

}