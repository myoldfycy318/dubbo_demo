package com.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationSuccessHandlerImpl
 *
 * @author Zhang ShanMin
 * @date 2016/4/5
 * @time 10:33
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        System.out.println("*********authentication.name="+authentication.getName());
        httpServletRequest.getRequestDispatcher("/test/loginDetail.do").forward(httpServletRequest, httpServletResponse);
//        httpServletResponse.sendRedirect("/test/loginDetail.do");
        //TODO  登录成功后转到原来请求
    }
}
