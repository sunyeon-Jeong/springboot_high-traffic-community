package com.mallang.hightrafficcommunity.aop;

import com.mallang.hightrafficcommunity.util.HttpSessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component // Bean 등록
@Aspect // AOP를 수행하는 Aspect
@Order(Ordered.LOWEST_PRECEDENCE) // AOP 실행순서 (낮은 우선순위)
@Log4j2
public class LoginCheckAspect {

    // @Around : 메서드 실행 전/후 로직 수행
    // @LoginCheck 어노테이션 붙은 메서드를 대상으로 함
    // ProceedingJoinPoint : 해당 메서드를 호출하는 시점과 인자에 접근
    // LoginCheck : 메서드의 어노테이션 정보를 가져옴
    @Around("@annotation(com.mallang.hightrafficcommunity.aop.LoginCheck) && @annotation(loginCheck)")
    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck) throws Throwable {

        HttpSession httpSession = (HttpSession) ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();

        String username = null;
        int usernameIndex = 0;

        String userType = loginCheck.userType().toString();

        switch (userType) {
            case "ADMIN": {
                username = HttpSessionUtil.getLoginAdminUsername(httpSession);
                break;
            }
            case "MEMBER": {
                username = HttpSessionUtil.getLoginMemberUsername(httpSession);
                break;
            }
        }

        if (username == null) {
            log.debug(proceedingJoinPoint.toString() + "account : " + username);
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인한 username 값을 확인해주세요.") {};
        }

        Object[] modifiedArgs = proceedingJoinPoint.getArgs();

        if (proceedingJoinPoint.getArgs() != null) {
            modifiedArgs[usernameIndex] = username;
        }

        return proceedingJoinPoint.proceed(modifiedArgs);

    }

}