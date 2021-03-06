package com.luhuihahaha.community.controller;


import com.luhuihahaha.community.advice.CustomizeExceptionHandler;
import com.luhuihahaha.community.dto.PageDTO;
import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.service.QuestionService;
import com.luhuihahaha.community.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class GreetingController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private CustomizeExceptionHandler customizeExceptionHandler;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

//        loginUtil.checkLogin(request);

        PageDTO pageDto = questionService.getQuestion(page,size);
        model.addAttribute("pageDTo", pageDto);
        return "index";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
                         @RequestParam(name = "searchStr")String searchStr, Model model,
                         @RequestParam(name = "size", defaultValue = "5") Integer size,
                         HttpServletRequest request){
        PageDTO pageDto = questionService.search(searchStr, page, size);
        if (pageDto.getPageCount()==0){
           request.getSession().setAttribute("errorMessage","哎呀，数据突然找不到了！！");
        }
        model.addAttribute("pageDTo", pageDto);
        model.addAttribute("searchStr",searchStr);
        return "index";
    }




}
