package com.exam.civil.config;

import com.alibaba.fastjson.JSON;
import com.exam.civil.pojo.Result;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String originURL = request.getHeader("Origin");
        if(originURL != null) {
            response.setHeader("Access-Control-Allow-Origin",originURL);
        }
        response.addHeader("Access-Control-Allow-Credentials", "true");
        if(request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/auth/publicKey")) {
            return true;
        }
        if(Objects.nonNull(session.getAttribute("user"))){
            return true;
        }
        response.setStatus(203);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.failed(203,"登录信息失效")));
        return false;
    }
}
