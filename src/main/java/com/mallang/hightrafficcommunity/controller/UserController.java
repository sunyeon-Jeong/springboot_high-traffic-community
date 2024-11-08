package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import com.mallang.hightrafficcommunity.dto.request.LoginRequestDTO;
import com.mallang.hightrafficcommunity.dto.response.LoginResponseDTO;
import com.mallang.hightrafficcommunity.service.Impl.UserServiceImpl;
import com.mallang.hightrafficcommunity.util.HttpSessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody UserDTO userDTO) {

        // 사용자 입력값 null validation
        if (UserDTO.signupRequestDataNullValidation(userDTO)) {
            throw new NullPointerException("회원가입 시, 필수 데이터를 모두 입력해야 합니다.");
        }

        userServiceImpl.signup(userDTO);

    }

    /*  로그인 */
    @PostMapping("login")
    public HttpStatus login(@RequestBody LoginRequestDTO loginRequestDTO,
                                        HttpSession httpSession) {

        // 사용자 입력값 -> db조회 후, 반환값 dto에 저장
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        String nickName = loginRequestDTO.getNickName();

        UserDTO userDTO = userServiceImpl.login(username, password, nickName);

        ResponseEntity<LoginResponseDTO> loginResult = null;

        if (userDTO == null) {

            // db에 사용자 정보 없을 경우 예외처리
            return HttpStatus.NOT_FOUND;

        } else if (userDTO != null) {

            // db에 사용자 정보 있을 경우 성공
            loginResponseDTO = LoginResponseDTO.success(userDTO);

            // session -> username set
            if (userDTO.getStatus() == (UserDTO.Status.ADMIN))
                HttpSessionUtil.setLoginAdminUsername(httpSession, username);
            else
                HttpSessionUtil.setLoginMemberUsername(httpSession, username);

            loginResult = new ResponseEntity<LoginResponseDTO>(loginResponseDTO, HttpStatus.OK);

        } else {
            throw new RuntimeException("Login Error!, 회원정보가 없거나 삭제된 정보입니다.");
        }

        return HttpStatus.OK;

    }

}