package com.luhuihahaha.community.controller;


import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class GreetingController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (cookies!=null)
        for(Cookie cookie : cookies){
           if(cookie.getName().equals("token")){
               String token = cookie.getValue();
               User user = userMapper.findByToken(token);
               if(user != null){
                   request.getSession().setAttribute("user",user);
               }
               break;
           }
        }


        return "index";
    }



}
