package com.luhuihahaha.community.controller;

import com.luhuihahaha.community.dto.QuestionDTO;
import com.luhuihahaha.community.model.User;
import com.luhuihahaha.community.service.QuestionService;
import com.luhuihahaha.community.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LoginUtil loginUtil;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, HttpServletRequest request, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question",questionDTO);

        User user = loginUtil.checksLogin(request);
        model.addAttribute("user",user);

        return "question";
    }

}
