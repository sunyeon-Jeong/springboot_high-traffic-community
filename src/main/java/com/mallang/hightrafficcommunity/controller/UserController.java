package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import com.mallang.hightrafficcommunity.dto.request.LoginRequestDTO;
import com.mallang.hightrafficcommunity.dto.response.LoginResponseDTO;
import com.mallang.hightrafficcommunity.service.Impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private static final ResponseEntity<LoginResponseDTO> FAIL_RESPONSE = new ResponseEntity<LoginResponseDTO>(HttpStatus.BAD_REQUEST);
    private static LoginResponseDTO loginResponseDTO;

    /* 생성자를 통한 객체 DI 주입 */
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /* 회원가입 */
    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody UserDTO userDTO) {

        // 사용자 입력값 null validation
        if (UserDTO.signupRequestDataNullValidation(userDTO)) {
            throw new NullPointerException("회원가입 시, 필수 데이터를 모두 입력해야 합니다.");
        }

        userServiceImpl.signup(userDTO);

    }

}