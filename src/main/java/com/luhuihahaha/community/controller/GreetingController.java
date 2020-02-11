package com.luhuihahaha.community.controller;


import com.luhuihahaha.community.dto.QuestionDTO;
import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.model.User;
import com.luhuihahaha.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class GreetingController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){

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

        List<QuestionDTO> questionDTOS = questionService.getQuestion();
        model.addAttribute("questions",questionDTOS);
        return "index";
    }



}
