package com.exam.civil.config;

import com.alibaba.fastjson.JSON;
import com.exam.civil.pojo.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@WebFilter
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String originURL = request.getHeader("Origin");
        if(originURL != null) {
            response.setHeader("Access-Control-Allow-Origin",originURL);
        }
        response.addHeader("Access-Control-Allow-Credentials", "true");

        if(request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/auth/publicKey")) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (Objects.nonNull(request.getSession().getAttribute("user"))) {
            System.out.println("用户已登录，用户id为："+request.getSession().getAttribute("user"));
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        System.out.println("登录失效");
        response.setStatus(203);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.failed(203,"无效的登录信息")));
        return;
    }

    @Override
    public void destroy() {
    }
}
