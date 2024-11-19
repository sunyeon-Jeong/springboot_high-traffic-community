package com.mallang.hightrafficcommunity.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter @Setter
public class CommonException extends RuntimeException {

    HttpStatus code;
    String message;

}