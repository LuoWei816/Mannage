package com.pushpm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("toLogin")
    public String reLogin(){
        System.out.println("0000000");
       // ModelAndView modelAndView = new ModelAndView("login");
         return  "login";
    }
    @RequestMapping("toIndex")
    public String reIndex(){
        System.out.println("1111");
        // ModelAndView modelAndView = new ModelAndView("login");
        return  "index";
    }
}
