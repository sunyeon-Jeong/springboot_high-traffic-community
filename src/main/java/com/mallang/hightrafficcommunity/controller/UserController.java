package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.service.Impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    // 의존해야하는 객체 final로 선언
    private final UserServiceImpl userServiceImpl;

    // 생성자를 통해 객체 DI 주입
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

}