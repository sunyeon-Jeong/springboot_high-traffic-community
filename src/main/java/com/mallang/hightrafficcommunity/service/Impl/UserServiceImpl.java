package com.mallang.hightrafficcommunity.service.Impl;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import com.mallang.hightrafficcommunity.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Override
    public void signin(UserDTO userProfile) {

    }

    @Override
    public UserDTO login(String userId, String password) {
        return null;
    }

    @Override
    public boolean isDuplicated(String username) {
        return false;
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return null;
    }

    @Override
    public void updatePassword(String userId, String originPassword, String modifiedPassword) {

    }

    @Override
    public void deleteUser(String userId, String password) {

    }

}