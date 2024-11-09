package com.mallang.hightrafficcommunity.util;

import jakarta.servlet.http.HttpSession;

public class HttpSessionUtil {

    // 사용자 권한 분류
    private static final String LOGIN_MEMBER_USERNAME = "LOGIN_MEMBER_USERNAME";
    private static final String LOGIN_ADMIN_USERNAME = "LOGIN_ADMIN_USERNAME";

    // 생성자 private -> 인스턴스화 X, 유틸리티 클래스
    private HttpSessionUtil () {}

    public static String getLoginMemberUsername(HttpSession httpSession) {
        return (String) httpSession.getAttribute(LOGIN_MEMBER_USERNAME);
    }

    public static void setLoginMemberUsername(HttpSession httpSession, String username) {
        httpSession.setAttribute(LOGIN_MEMBER_USERNAME, username);
    }

    public static String getLoginAdminUsername(HttpSession httpSession) {
        return (String) httpSession.getAttribute(LOGIN_ADMIN_USERNAME);
    }

    public static void setLoginAdminUsername(HttpSession httpSession, String username) {
        httpSession.setAttribute(LOGIN_ADMIN_USERNAME, username);
    }

    // 세션 무효화
    public static void clear(HttpSession httpSession) {
        httpSession.invalidate();
    }

}