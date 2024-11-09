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
    private int id;
    private String username;
    private String password;
    private String nickName;
    private boolean isAdmin;
    private boolean isWithDraw;
    private Date createTime;
    private Date modifyTime;
    private Status status;

    /*  생성자 */
    public UserDTO(){}

    public UserDTO(String username, String password, String nickName, boolean isAdmin,
                            boolean isWithDraw, Date createTime, Date modifyTime, Status status) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.isAdmin = isAdmin;
        this.isWithDraw = isWithDraw;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.status = status;
    }

    /* 회원가입 > 사용자 입력값 null validation */
    public static boolean signupRequestDataNullValidation(UserDTO userDTO) {

        return userDTO.getUsername() == null || userDTO.getPassword() == null
                    || userDTO.getNickName() == null;

    }

}