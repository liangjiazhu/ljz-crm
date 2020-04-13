package com.lljz.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 返回视图的控制器
 */
@Controller
public class PagesController {

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
