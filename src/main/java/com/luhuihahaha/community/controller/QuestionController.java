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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LoginUtil loginUtil;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, HttpServletRequest request, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        if (questionDTO==null){
            request.getSession().setAttribute("errorMessage","帖子失踪了!!!");
            return null;
        }

        model.addAttribute("question",questionDTO);

        questionService.addView(id);

        User user = loginUtil.checksLogin(request);
        model.addAttribute("user",user);

        return "question";
    }


    @RequestMapping("/getQuestionInfo")
    @ResponseBody
    public HashMap getLoginInfo(HttpServletRequest request){
        HashMap<String,Object> data = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(user.toString());
        data.put("user",user);
        return data;

    }

}
