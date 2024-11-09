package com.mallang.hightrafficcommunity.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class UpdatePasswordRequestDTO {

    @NonNull
    private String originPassword;

    @NonNull
    private String modifiedPassword;

}