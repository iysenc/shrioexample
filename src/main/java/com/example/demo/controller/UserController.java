package com.example.demo.controller;

import cn.hutool.crypto.SecureUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


/**
 * Created by demo on 2019/8/3 - 9:27
 * version:1.0.0
 */
@Controller
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            log.info("用户登陆成功！！！");
            return "success";
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                log.info("用户名不存在！！！");
                session.setAttribute("errorInfo", "用户名不存在");
                return "error";
            } else if (e instanceof IncorrectCredentialsException) {
                log.info("密码输入有误！！！");
                session.setAttribute("errorInfo", "输入密码错误");
                return "error";
            }
        }
        return null;
    }

    @RequestMapping("one")
    public String getOne() {
        return "one";
    }

    @RequestMapping("two")
    public String getTwo() {
        return "two";
    }

    @RequestMapping("permission")
    public String getPermission() {
        return "permission";
    }
}
