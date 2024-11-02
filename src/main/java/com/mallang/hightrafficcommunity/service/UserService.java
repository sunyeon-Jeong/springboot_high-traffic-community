package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface UserService {

    // 회원가입
    void signin(UserDTO userProfile);

    // 로그인
    UserDTO login(String userId, String password);

    // 아이디 중복체크
    boolean isDuplicated(String username);

    // 사용자 정보 조회
    UserDTO getUserInfo(String userId);

    // 패스워드 수정
    void updatePassword(String userId, String originPassword, String modifiedPassword);

    // 사용자 삭제
    void deleteUser(String userId, String password);

}