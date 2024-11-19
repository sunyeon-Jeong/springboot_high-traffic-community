package com.mallang.hightrafficcommunity.exception.handler;

import com.mallang.hightrafficcommunity.dto.response.CommonResponse;
import com.mallang.hightrafficcommunity.exception.CommonException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {

        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "RuntimeException", exception.getMessage(), exception.getMessage());
        return new ResponseEntity<>(commonResponse, new HttpHeaders(), commonResponse.getHttpStatus());

    }

    @ExceptionHandler({CommonException.class})
    public ResponseEntity<Object> handleCommonException(CommonException exception) {

        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "CommonException", exception.getMessage(), exception.getMessage());
        return new ResponseEntity<>(commonResponse, new HttpHeaders(), commonResponse.getHttpStatus());

    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> defaultException(Exception exception) {

        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "high-traffic-community Exception", exception.getMessage(), exception.getMessage());
        return new ResponseEntity<>(commonResponse, new HttpHeaders(), commonResponse.getHttpStatus());

    }

}