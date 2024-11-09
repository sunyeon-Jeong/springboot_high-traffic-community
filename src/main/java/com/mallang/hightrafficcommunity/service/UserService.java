package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface UserService {

    // 아이디 중복체크
    boolean isDuplicated(String username);

    // 회원가입
    void signup(UserDTO userDTO);

    // 로그인
    UserDTO login(String username, String password, String nickName);

    // 로그인 후 회원정보 조회
    UserDTO getUserInfo(String username);

    // 비밀번호 변경
    void updatePassword(String username, String originPassword, String modifiedPassword);

}