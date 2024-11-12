package com.mallang.hightrafficcommunity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

    private HttpStatus httpStatus;
    private String code;
    private String message;
    private T requestBody;

}