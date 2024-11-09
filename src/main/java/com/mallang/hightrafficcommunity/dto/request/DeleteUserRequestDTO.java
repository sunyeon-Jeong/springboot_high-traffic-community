package com.mallang.hightrafficcommunity.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class DeleteUserRequestDTO {

    @NonNull
    private String username;

    @NonNull
    private String password;

}