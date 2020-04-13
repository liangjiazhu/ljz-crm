package com.lljz.crm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {


    @PostMapping("/doLogin")  //获取参数格式：{"empName":"admin","pwd":"dada"}
    public Map login(@RequestBody Map<String, String> map){
        Map<String, String> result = new HashMap<String, String>();
        result.put("code","true");
        //拿到当前主体对象，用作验证   （从安全工具类中拿到subject）
        Subject subject = SecurityUtils.getSubject();
        //将用户名和密码包装到token中  （加密密码）
        UsernamePasswordToken token = new UsernamePasswordToken(map.get("eName"),map.get("pass"));
        //通过捕获异常来判断用户是否是否可以登录
        try{
            subject.login(token);
        }catch (UnknownAccountException uae){
            result.put("code","false");
        }catch (IncorrectCredentialsException ice){
            result.put("code","false");
        }
        return result;
    }
}
