package com.mallang.hightrafficcommunity.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 커스텀 어노테이션 */
@Retention(RetentionPolicy.RUNTIME) // 런타임동안 유지(AOP에서 어노테이션 정보 사용)
@Target(ElementType.METHOD) // 메서드에만 적용 가능
public @interface LoginCheck {

    // 어노테이션 속성 (각 메서드에서 요구하는 사용자 유형 정의 가능)
    public static enum UserType {
        MEMBER, ADMIN
    }

    UserType userType();

}