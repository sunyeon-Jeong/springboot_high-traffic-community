package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface UserService {

    // 아이디 중복체크
    boolean isDuplicated(String username);

    // 회원가입
    void signup(UserDTO userDTO);

//    // 로그인
//    UserDTO login(String userId, String password);
//
//    // 사용자 정보 조회
//    UserDTO getUserInfo(String username);
//
//    // 패스워드 수정
//    void updatePassword(String userId, String originPassword, String modifiedPassword);
//
//    // 회원탈퇴
//    void deleteUser(String userId, String password);

}