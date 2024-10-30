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

    private final UserServiceImpl userServceImpl;

    @Autowired
    public UserController(UserServiceImpl userServceImpl) {
        this.userServceImpl = userServceImpl;
    }

}