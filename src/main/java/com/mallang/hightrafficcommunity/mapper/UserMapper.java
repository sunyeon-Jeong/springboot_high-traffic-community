package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserMapper {

    // 아이디 중복체크
    int isDuplicated(@Param("username") String username);

    // 회원가입
    int signup(UserDTO userDTO);

    // 로그인
    public UserDTO login(@Param("username") String username,
                                    @Param("encryptPassword") String encryptPassword,
                                    @Param("nickName") String nickName);

    // 로그인 후 회원정보 조회
    public UserDTO getUserInfo(@Param("username") String username);

}