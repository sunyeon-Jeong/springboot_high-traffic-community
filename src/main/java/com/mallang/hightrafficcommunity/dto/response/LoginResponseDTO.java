package com.mallang.hightrafficcommunity.dto.response;

import com.mallang.hightrafficcommunity.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor // final, @NonNull만 생성자 주입
public class LoginResponseDTO {

    enum LoginStatus {
        SUCCESS, FAIL, DELETED
    }

    @NonNull
    private LoginStatus loginStatus;

    private UserDTO userDTO;

    // 객체 한번 생성 후 재사용 -> 메모리 절약
    // FAIL status는 userDTO가 필요하지 않으므로 상수로 생성
    private static final LoginResponseDTO FAIL = new LoginResponseDTO(LoginStatus.FAIL);

    // SUCCESS는 호출 될 때 마다 -> 새로운 객체 생성
    public static LoginResponseDTO success(UserDTO userDTO) {
        return new LoginResponseDTO(LoginStatus.SUCCESS, userDTO);
    }

}