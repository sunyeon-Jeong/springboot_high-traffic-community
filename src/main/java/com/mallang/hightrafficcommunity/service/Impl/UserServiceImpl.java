package com.mallang.hightrafficcommunity.service.Impl;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import com.mallang.hightrafficcommunity.exception.DuplicatedUsernameException;
import com.mallang.hightrafficcommunity.mapper.UserMapper;
import com.mallang.hightrafficcommunity.service.UserService;
import com.mallang.hightrafficcommunity.util.SHA256Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    /* Mapper 연결 */
    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /* 아이디 중복체크 */
    @Override
    public boolean isDuplicated(String username) {
        return userMapper.isDuplicated(username) == 1;
    }

    /* 회원가입 */
    @Override
    public void signup(UserDTO userDTO) {

        // 아이디 중복체크 (true : 중복)
        boolean duplicateValidation = isDuplicated(userDTO.getUsername());

        // 아이디 중복체크 예외처리
        if (duplicateValidation) {
            throw new DuplicatedUsernameException("중복된 아이디입니다.");
        }

        // 비밀번호 암호화(SHA256)
        userDTO.setPassword(SHA256Util.encryptSHA256(userDTO.getPassword()));

        // 생성시간 set
        userDTO.setCreateTime(new Date());

        // Mybatis 쿼리 실행 후, 삽입된 레코드 수
        int queryResultInt = userMapper.signup(userDTO);

        // 예외처리
        if (queryResultInt != 1) {
            log.error("signup ERROR! {}", userDTO);
            throw new RuntimeException(
                    "signup ERROR! 회원가입 메서드를 확인해주세요\n" + "Params : " + userDTO);
        }

    }

    /*  로그인 */
    @Override
    public UserDTO login(String username, String password, String nickName) {

        // 비밀번호 암호화(SHA256)
        String encryptPassword = SHA256Util.encryptSHA256(password);

        // DB 조회 후, controller로 dto 반환
        UserDTO userInfo = userMapper.getUserInfo(username, encryptPassword, nickName);

        return userInfo;

    }

}