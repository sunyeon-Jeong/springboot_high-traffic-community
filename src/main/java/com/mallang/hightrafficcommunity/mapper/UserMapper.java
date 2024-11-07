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

}