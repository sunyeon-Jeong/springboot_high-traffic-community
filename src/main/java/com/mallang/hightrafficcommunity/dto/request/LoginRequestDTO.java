package com.mallang.hightrafficcommunity.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String nickName;

}