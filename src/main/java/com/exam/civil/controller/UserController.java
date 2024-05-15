package com.exam.civil.controller;

import com.exam.civil.config.RSAUtils;
import com.exam.civil.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/publicKey")
    @ResponseBody
    public String getPublicKey() {
        try {
            RSAUtils.initKey(1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RSAUtils.getPublicKeyStr();
    }

    @RequestMapping("/login")
    @ResponseBody
    public Boolean login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        String psd = RSAUtils.decrypt(password);
        System.out.println(request.getRequestURI());
        String pass = userMapper.findUser(username);
        if(psd.equals(pass)){
            request.getSession().setAttribute("user",username);
            request.getSession().setMaxInactiveInterval(30);
            System.out.println(request.getSession().getAttribute("user"));
            return true;
        }
        return false;
    }

}
